package com.dh.movieservice.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.dh.movieservice.model.Movie;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovieSender {
	
	private final RabbitTemplate rabbitTemplate;
	
	private final Queue movieQueue;
	
	public void send(Movie movie) {this.rabbitTemplate.convertAndSend(this.movieQueue.getName(), movie);}
	
}
