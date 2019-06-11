package org.zy.gateway.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class CloudGatewayAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayAdminServerApplication.class, args);
	}
}