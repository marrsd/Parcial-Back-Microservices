package com.dh.catalogservice.feign.model;

import java.util.List;

public record Serie(String id, String name, String genre, List<Season> seasons) {

}
