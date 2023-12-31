package com.dh.catalogservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
