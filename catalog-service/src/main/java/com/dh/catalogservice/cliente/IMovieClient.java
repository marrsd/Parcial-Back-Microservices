package com.dh.catalogservice.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dh.catalogservice.model.Movie;

@FeignClient(name = "movie-service")
public interface IMovieClient {

  @GetMapping("/api/v1/movies/{genre}")
  ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre);

  @PostMapping("/api/v1/movies/save")
  ResponseEntity<Movie> saveMovie(@RequestBody Movie movie);
}
