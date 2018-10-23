package com.anynines.queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.anynines.config.RabbitMQConfiguration;

@Component
public class Sender {
	private final RabbitTemplate rabbitTemplate;

	public Sender(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedRate = 100)
	private void publish() {
		final String message = new String("message from publisher to subribers, sent at " + new SimpleDateFormat("dd-MM-yy:HH:mm:ss:SSS").format(new Date()));
		send(message);
	}

	public void send(final String message) {
		try {
			System.out.println("Sending message to rabbitMQ ...");
			rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName, "foo.bar.baz", message);
			System.out.println("message sent");
		} catch (AmqpException e) {
			System.out.println("error:" + e);
			throw e;
		}
	}
}
