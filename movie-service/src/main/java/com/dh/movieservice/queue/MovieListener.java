package com.dh.movieservice.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;

@Component
public class MovieListener {
	
	private final MovieService service;

	public MovieListener(MovieService service) {
		this.service = service;
	}
	
	@RabbitListener(queues = {"${queue.persona.name}"})
	public void receive(@Payload Movie movie) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.save(movie);
	}
	
}
