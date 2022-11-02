package com.example.bookstorebg;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class BookstoreBgApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreBgApplication.class, args);
//		System.out.println( SpringBootVersion.getVersion());
	}

}
