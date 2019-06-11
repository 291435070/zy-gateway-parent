package org.zy.gateway.cloud.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudGatewayServerEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayServerEurekaApplication.class, args);
	}
}