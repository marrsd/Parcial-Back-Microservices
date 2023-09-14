package com.dh.catalogservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Document
@RequiredArgsConstructor
public class Serie {

	@MongoId
	private String id;
	private String name;
	private String genre;
	private List<Season> seasons = new ArrayList<>();

	@Data
	@Getter
	@Setter
	@RequiredArgsConstructor
	public static class Season {
		private Integer seasonNumber;
		private List<Chapter> chapters = new ArrayList<>();
	}

	@Data
	@Getter
	@Setter
	@RequiredArgsConstructor
	public static class Chapter {
		private String name;
		private Integer number;
		private String urlStream;
	}
}
