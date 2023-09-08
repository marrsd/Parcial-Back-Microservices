package com.dh.catalogservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dh.catalogservice.model.Serie;

@Repository
public interface SeriesRepository extends MongoRepository<Serie, Long> {
  List<Serie> findAllByGenre(String genre);
}
