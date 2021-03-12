package com.infosys.project.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication

public class OrderApplication {
	
	String message;
	@KafkaListener(topics="TestTopic",groupId="group_id",containerFactory = "concurrentKafkaListenerContainerFactory")
	public String consume(String data) {
		message=data;
		return message;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
