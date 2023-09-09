package com.dh.movieservice.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;

public class RabbitMQSenderConfig {
	
	@Value("${queue.movie.name}")
	private String movieQueue;
	
	public Queue queue() { return new Queue(this.movieQueue, true); }
}
