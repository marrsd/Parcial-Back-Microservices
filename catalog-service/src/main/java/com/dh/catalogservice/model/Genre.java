package com.dh.catalogservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {
	
	private List<Movie> movies;
	private List<Serie> series;
}
