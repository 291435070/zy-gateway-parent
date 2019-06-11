package org.zy.gateway.filter;

import java.time.Instant;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class RequestTimeFilter implements GatewayFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final String REQUEST_TIME_BEGIN = "requestTimeBegin";

	@Override
	public int getOrder() {
		logger.info("{} : {}", LocalDateTime.now(), "RequestTimeFilter.getOrder");
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("{} : {}", LocalDateTime.now(), "RequestTimeFilter.filter");
		exchange.getAttributes().put(REQUEST_TIME_BEGIN, Instant.now().toEpochMilli());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			Long start = exchange.getAttribute(REQUEST_TIME_BEGIN);
			if (null != start) {
				logger.info("{} : {} ms", exchange.getRequest().getURI().getRawPath(), Instant.now().toEpochMilli() - start);
			}
		}));
	}

}