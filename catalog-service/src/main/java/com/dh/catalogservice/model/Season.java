package com.dh.catalogservice.model;

import java.util.List;

public record Season(Integer seasonNumber, List<Chapter> chapters) {

}
