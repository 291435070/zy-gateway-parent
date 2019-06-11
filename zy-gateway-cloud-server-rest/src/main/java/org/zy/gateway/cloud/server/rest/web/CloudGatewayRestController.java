package org.zy.gateway.cloud.server.rest.web;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class CloudGatewayRestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${server.port}")
	private String port;

	@GetMapping("get")
	public Object get(@RequestParam(value = "name", defaultValue = "James") String name) {
		logger.info(port);
		return String.format("[%s] %s : %s", name, port, LocalDateTime.now());
	}

}