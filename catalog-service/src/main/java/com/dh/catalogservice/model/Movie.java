package com.dh.catalogservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Document
@Setter
@Getter
@RequiredArgsConstructor
public class Movie {

	@MongoId
	private String id;

	private String name;

	private String genre;

	private String urlStream;
}
