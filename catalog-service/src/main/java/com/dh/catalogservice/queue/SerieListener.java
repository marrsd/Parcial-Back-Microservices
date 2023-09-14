package com.dh.catalogservice.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;

@Component
public class SerieListener {
  private final CatalogService catalogService;

  public SerieListener(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @RabbitListener(queues = { "${queue.series.name}" })
  public void receive(@Payload Serie serie) {
    catalogService.saveCatalog(serie);
  }

}
