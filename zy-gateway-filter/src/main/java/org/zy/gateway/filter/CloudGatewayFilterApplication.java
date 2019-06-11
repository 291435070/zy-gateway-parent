package org.zy.gateway.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayFilterApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/customer/**")
						.filters(f -> f.filter(new RequestTimeFilter()).addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
						.uri("http://httpbin.org:80/get")
						.order(0)
						.id("customer_filter_router"))
				.build();
	}

	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}

	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

}