package org.zy.gateway.sight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class CloudGatewaySightApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewaySightApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String url = "http://httpbin.org:80";
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri(url))
				.route(p -> p
		                //.host("*.hystrix.com")
		                .path("/delay/3")
		                .filters(f -> f
		                    .hystrix(config -> config
		                        .setName("mycmd")
		                        .setFallbackUri("forward:/fallback")))
		                .uri(url))
				.build();
	}
	
	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}

}