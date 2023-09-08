package com.dh.catalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MoviesRepository;
import com.dh.catalogservice.repository.SeriesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {

  private final SeriesRepository seriesRepository;
  private final MoviesRepository moviesRepository;

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

}
