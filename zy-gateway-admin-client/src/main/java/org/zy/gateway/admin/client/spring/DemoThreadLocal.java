package org.zy.gateway.admin.client.spring;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zy.gateway.admin.client.model.News;

public class DemoThreadLocal {

	private static final Logger logger = LoggerFactory.getLogger(DemoThreadLocal.class);

	private static ThreadLocal<News> local = ThreadLocal.withInitial(() -> new News());

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			pool.execute(() -> {
				logger.info("{}", local);
				logger.info("{}", local.get());
			});
		}
		pool.shutdown();
	}

}