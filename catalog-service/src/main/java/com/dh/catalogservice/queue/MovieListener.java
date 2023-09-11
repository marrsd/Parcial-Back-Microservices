package com.dh.catalogservice.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.service.CatalogService;
/*
@Component
public class MovieListener {
	  private final CatalogService catalogService;

	  public MovieListener(CatalogService catalogService) {
	    this.catalogService = catalogService;
	  }
	
	  @RabbitListener(queues = { "${queue.movie.name}" })
	  public void receive(@Payload Movie movie) {
	    catalogService.saveCatalog(movie);
	  }
}
*/