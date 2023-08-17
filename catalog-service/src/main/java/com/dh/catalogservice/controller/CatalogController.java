package com.dh.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dh.catalogservice.cliente.IMovieClient;
import com.dh.catalogservice.model.Movie;

@RestController
public class CatalogController {

  @Autowired
  private IMovieClient iMovieClient;

  @GetMapping("catalog/{genre}")
  public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {
    return iMovieClient.getMovieByGenre(genre);
  }

  @PostMapping("catalog/saveMovie")
  public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
    return iMovieClient.saveMovie(movie);
  }
}