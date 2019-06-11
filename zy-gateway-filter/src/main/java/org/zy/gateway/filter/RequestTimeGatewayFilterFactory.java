package org.zy.gateway.filter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import reactor.core.publisher.Mono;

public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final String REQUEST_TIME_BEGIN = "requestTimeBegin";
	private final String KEY = "withParams";

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList(KEY);
	}

	public RequestTimeGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			exchange.getAttributes().put(REQUEST_TIME_BEGIN, Instant.now().toEpochMilli());
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				Long start = exchange.getAttribute(REQUEST_TIME_BEGIN);
				if (null != start) {
					logger.info("{} : {} ms, params:{}", exchange.getRequest().getURI().getRawPath(),
							Instant.now().toEpochMilli() - start, exchange.getRequest().getQueryParams());
				}
			}));
		};
	}

	public static class Config {
		private boolean withParams;

		public boolean isWithParams() {
			return withParams;
		}

		public void setWithParams(boolean withParams) {
			this.withParams = withParams;
		}
	}

}