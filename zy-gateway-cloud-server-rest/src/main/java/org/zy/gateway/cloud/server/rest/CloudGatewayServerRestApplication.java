package org.zy.gateway.cloud.server.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGatewayServerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayServerRestApplication.class, args);
	}
}