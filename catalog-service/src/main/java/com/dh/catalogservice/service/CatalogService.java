package com.dh.catalogservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dh.catalogservice.cliente.IMovieClient;
import com.dh.catalogservice.cliente.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MoviesRepository;
import com.dh.catalogservice.repository.SeriesRepository;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogService {

  private final SeriesRepository seriesRepository;
  private final MoviesRepository moviesRepository;

  private final IMovieClient iMovieClient;
  private final ISerieClient iSerieClient;

  public void saveCatalog(Object obj) {
    if (obj instanceof Serie serie) {
      seriesRepository.save(serie);
    } else if (obj instanceof Movie movie) {
      moviesRepository.save(movie);
    }
  }

  public Genre getCatalogByGenre(String genre) {
    List<Serie> series = seriesRepository.findAllByGenreIgnoreCase(genre);
    List<Movie> movies = moviesRepository.findAllByGenreIgnoreCase(genre);

    Genre data = new Genre(movies, series);

    return data;
  }

  @CircuitBreaker(name = "catalog", fallbackMethod = "saveMovieError")
  @Retry(name = "catalog")
  public String saveMovie(Movie movie) {

    log.info("Calling movie service ...");

    iMovieClient.saveMovie(movie);

    return "Pelicula guardada";
  }

  @CircuitBreaker(name = "serie", fallbackMethod = "saveSerieError")
  @Retry(name = "serie")
  public String saveSerie(Serie serie) {

    log.info("Calling serie service ...");

    iSerieClient.create(serie);

    return "Serie guardada";
  }

  private String saveMovieError(CallNotPermittedException exception) {
    return "No se pudo guardar la película";
  }

  private String saveSerieError(CallNotPermittedException exception) {
    return "No se guarda serie";
  }

}
