package com.dh.catalogservice.model;

import java.util.List;

public record Serie(String id, String name, String genre, List<Season> seasons) {

}
