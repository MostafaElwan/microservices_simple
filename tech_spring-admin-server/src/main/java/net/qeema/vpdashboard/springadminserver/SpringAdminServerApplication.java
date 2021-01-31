package com.elwan.microservices.springadminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class SpringAdminServerApplication {

	public static void main(String[] args) {
//		new SpringApplicationBuilder(SpringAdminServerApplication.class)
//		.web(WebApplicationType.REACTIVE)
//		.run(args);
		SpringApplication.run(SpringAdminServerApplication.class, args);
	}
	
}
