package com.dh.catalogservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dh.catalogservice.cliente.IMovieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MoviesRepository;
import com.dh.catalogservice.repository.SeriesRepository;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {

  private final SeriesRepository seriesRepository;
  private final MoviesRepository moviesRepository;

  private final IMovieClient iMovieClient;

  public void saveCatalog(Object obj) {
    if (obj instanceof Serie) {
      seriesRepository.save((Serie) obj);
    }
  }

  public Genre getCatalogByGenre(String genre) {
    List<Serie> series = seriesRepository.findAllByGenre(genre);
    List<Movie> movies = moviesRepository.findAllByGenre(genre);

    Genre data = new Genre(movies, series);

    return data;
  }

  @CircuitBreaker(name = "catalog", fallbackMethod = "saveMovieError")
  @Retry(name = "catalog")
  public ResponseEntity<Movie> saveMovie(Movie movie) {
    return iMovieClient.saveMovie(movie);
  }

  private ResponseEntity<Movie> saveMovieError(CallNotPermittedException exception) {
    Movie movie = new Movie();
    movie.setName("Error");
    movie.setUrlStream("Error");

    System.out.println("Se ejecuta metodo fallback circuit breaker");
    return ResponseEntity.ok(movie);
  }

}