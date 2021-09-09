package com.sphinx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.sphinx")
public class SphinxWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SphinxWebApplication.class, args);
	}
	
}
