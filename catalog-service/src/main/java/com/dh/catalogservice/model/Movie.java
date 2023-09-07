package com.dh.catalogservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Document
@RequiredArgsConstructor
public class Movie {
	
	@MongoId
	private Long id;
	
	private String name;
	
	private String genre;
	
	private String urlStream;
}
