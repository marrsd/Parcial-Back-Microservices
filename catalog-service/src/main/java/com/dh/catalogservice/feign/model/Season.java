package com.dh.catalogservice.feign.model;

import java.util.List;

public record Season(Integer seasonNumber, List<Chapter> chapters) {

}
