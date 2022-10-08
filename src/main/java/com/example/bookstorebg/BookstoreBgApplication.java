package com.example.bookstorebg;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class BookstoreBgApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreBgApplication.class, args);
//		System.out.println( SpringBootVersion.getVersion());
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

		String aaa = name + name;
		System.out.println(aaa);
		return String.format("Hello %s!", name);
	}

//	@Bean
//	public NewTopic topic() {
//		return TopicBuilder.name("topic1")
//				.partitions(10)
//				.replicas(1)
//				.build();
//	}
}
