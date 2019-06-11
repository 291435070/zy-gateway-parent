package org.zy.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class TokenFilter implements GlobalFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public int getOrder() {
		return -100;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("{} : {}", exchange.getRequest().getURI().getRawPath(), exchange.getRequest().getQueryParams());
		String token = exchange.getRequest().getQueryParams().getFirst("token");
		if (StringUtils.isEmpty(token)) {
			logger.warn("token is empty...");
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

}