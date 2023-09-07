package com.dh.catalogservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dh.catalogservice.model.Serie;

@Repository
public interface SeriesRepository extends MongoRepository<Serie, Long> {

}
