package com.dh.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dh.catalogservice.cliente.IMovieClient;
import com.dh.catalogservice.cliente.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

  @Autowired
  private IMovieClient iMovieClient;
  
  @Autowired
  private ISerieClient iSerieClient;

  @GetMapping("/movie/{genre}")
  public ResponseEntity<List<Movie>> getCatalogByGenreMovie(@PathVariable String genre) {
    ResponseEntity<List<Movie>> response = iMovieClient.getMovieByGenre(genre);
    
    System.out.println("Instancia movie con id: " + response.getHeaders().get("idrandom"));
    return response;
  }
  
  @GetMapping("/serie/{genre}")
  public ResponseEntity<List<Serie>> getCatalogByGenreSerie(@PathVariable String genre) {
    ResponseEntity<List<Serie>> response = iSerieClient.getSerieByGenre(genre);
    
    System.out.println("Instancia serie con id: " + response.getHeaders().get("idrandom"));
    return response;
  }

  @PostMapping("/saveMovie")
  public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
    return iMovieClient.saveMovie(movie);
  }
  
  @PostMapping("/saveSerie")
  public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
    return iSerieClient.saveSerie(serie);
  }
}
