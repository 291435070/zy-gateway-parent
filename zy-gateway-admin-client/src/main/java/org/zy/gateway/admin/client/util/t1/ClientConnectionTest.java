package org.zy.gateway.admin.client.util.t1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象池化
 */
@SuppressWarnings(value = "all")
public class ClientConnectionTest {

	private static final Logger LOG = LoggerFactory.getLogger(ClientConnectionTest.class);

	public static void main(String[] args) {
		GenericKeyedObjectPoolConfig<ClientConnection> config = new GenericKeyedObjectPoolConfig<>();
		config.setMaxIdlePerKey(5);
		config.setMaxTotalPerKey(15);
		config.setMaxTotal(15);
		config.setMinIdlePerKey(1);
		ClientConnectionFactory factory = new ClientConnectionFactory();
		GenericKeyedObjectPool<String, ClientConnection> objectPool = new GenericKeyedObjectPool<>(factory, config);
		ClientConnection client1 = null;
		ClientConnection client2 = null;
		try {
			client1 = objectPool.borrowObject("192.168.0.1");
			client2 = objectPool.borrowObject("192.168.0.2");
			LOG.info("{} -- {} --- {}", client1, client1.getActive(), client1.getUrl());
			LOG.info("{} -- {} --- {}", client2, client2.getActive(), client2.getUrl());
		} catch (Exception e) {
			LOG.error("{}", e);
		} finally {
			if (null != client1) {
				objectPool.returnObject(client1.getUrl(), client1);
			}
			if (null != client2) {
				objectPool.returnObject(client2.getUrl(), client2);
			}
			// objectPool.close();
		}

		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		try {
			for (int i = 0; i < 100; i++) {
				threadPool.execute(() -> {
					ClientConnection c = null;
					try {
						c = objectPool.borrowObject("192.168.0.1");
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					LOG.info("{} -- {} --- {}", c, c.getActive(), c.getUrl());
					if (null != c) {
						objectPool.returnObject(c.getUrl(), c);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
			// objectPool.close();
		}

	}

}