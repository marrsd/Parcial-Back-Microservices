package com.dh.catalogservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dh.catalogservice.model.Movie;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, Long> {
  List<Movie> findAllByGenre(String genre);
}
