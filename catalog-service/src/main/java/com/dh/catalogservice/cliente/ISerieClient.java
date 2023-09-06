package com.dh.catalogservice.cliente;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dh.catalogservice.FeignConfiguration;
import com.dh.catalogservice.model.Serie;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = FeignConfiguration.class)
public interface ISerieClient {
	
	  @GetMapping("/api/v1/series/{genre}")
	  ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre);
	
	  @PostMapping("/api/v1/series/save")
	  ResponseEntity<Serie> saveSerie(@RequestBody Serie serie);
}
