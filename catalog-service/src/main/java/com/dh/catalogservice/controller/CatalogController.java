package com.dh.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dh.catalogservice.cliente.IMovieClient;
import com.dh.catalogservice.cliente.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.MovieListener;
import com.dh.catalogservice.queue.SerieListener;
import com.dh.catalogservice.service.CatalogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

  @Autowired
  private IMovieClient iMovieClient;

  @Autowired
  private ISerieClient iSerieClient;

  private final SerieListener serieListener;
  private final MovieListener movieListener;

  private final CatalogService catalogService;

  @PostMapping
  public ResponseEntity<String> saveCatalog(@RequestBody Object obj) {
    if (obj instanceof Serie serie) {
      serieListener.receive(serie);
      log.info("Guardando objeto de tipo Serie, con id: " + serie.getId() + ", nombre: " + serie.getName());
    } else if (obj instanceof Movie movie) {
      movieListener.receive(movie);
      log.info("Guardando objeto de tipo Movie, nombre: " + movie.getName());
    }
    return ResponseEntity.status(HttpStatus.OK).body("Dato Guardado");
  }

  @GetMapping("/{genre}")
  public ResponseEntity<Genre> getCatalogByGenre(@PathVariable String genre) {
    log.info("Se obtienen objetos de género: " + genre);
    return ResponseEntity.ok().body(catalogService.getCatalogByGenre(genre));
  }

  @PostMapping("/movie/save")
  public String saveMovie(@RequestBody Movie movie) {

    log.info("Se guarda movie: " + movie.getName());

    return catalogService.saveMovie(movie);
  }

  @PostMapping("/serie/save")
  public String saveSerie(@RequestBody Serie serie) {

    log.info("Se guarda serie: " + serie.getName());

    return catalogService.saveSerie(serie);
  }

  @GetMapping("/movie/{genre}")
  public ResponseEntity<List<Movie>> getCatalogByGenreMovie(@PathVariable String genre) {
    ResponseEntity<List<Movie>> response = iMovieClient.getMovieByGenre(genre);

    System.out.println("Instancia movie con id: " +
        response.getHeaders().get("idrandom"));
    return response;
  }

  @GetMapping("/serie/{genre}")
  public ResponseEntity<List<Serie>> getCatalogByGenreSerie(@PathVariable String genre) {
    ResponseEntity<List<Serie>> response = iSerieClient.getSerieByGenre(genre);

    System.out.println("Instancia serie con id: " +
        response.getHeaders().get("idrandom"));
    return response;
  }

}
