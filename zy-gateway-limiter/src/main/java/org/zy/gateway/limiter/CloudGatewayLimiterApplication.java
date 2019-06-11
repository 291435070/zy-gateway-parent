package org.zy.gateway.limiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayLimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayLimiterApplication.class, args);
	}

//	@Bean
//	public HostAddrKeyResolver hostAddrKeyResolver() {
//		return new HostAddrKeyResolver();
//	}

//	@Bean
//	public UriKeyResolver uriKeyResolver() {
//		return new UriKeyResolver();
//	}
	
	@Bean
	public UserKeyResolver userKeyResolver() {
		return new UserKeyResolver();
	}

}