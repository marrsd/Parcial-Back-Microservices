package com.dh.catalogservice.cliente;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dh.catalogservice.FeignConfiguration;
import com.dh.catalogservice.model.Serie;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = FeignConfiguration.class)
public interface ISerieClient {
	
	  @PostMapping("/api/v1/series/save")
	  ResponseEntity<Serie> saveSerie(@RequestBody Serie serie);
}
