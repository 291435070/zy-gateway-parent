package org.zy.gateway.admin.client.util.t0;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象池化
 */
@SuppressWarnings(value = "all")
public class ClientConnectionTest {

	private static final Logger LOG = LoggerFactory.getLogger(ClientConnectionTest.class);

	public static void main(String[] args) {
		ClientConnectionFactory factory = new ClientConnectionFactory();
		GenericObjectPoolConfig<ClientConnection> config = new GenericObjectPoolConfig<>();
		config.setMaxIdle(5);
		config.setMaxTotal(15);
		config.setMinIdle(1);
		GenericObjectPool<ClientConnection> objectPool = new GenericObjectPool<>(factory, config);
		ClientConnection client = null;
		try {
			client = objectPool.borrowObject();
			LOG.info("{} -- {}", client, client.getActive());
		} catch (Exception e) {
			LOG.error("{}", e);
		} finally {
			if (null != client) {
				objectPool.returnObject(client);
			}
			// objectPool.close();
		}

		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		try {
			for (int i = 0; i < 100; i++) {
				threadPool.execute(() -> {
					ClientConnection c = null;
					try {
						c = objectPool.borrowObject();
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					LOG.info("{} -- {}", c, c.getActive());
					if (null != c) {
						objectPool.returnObject(c);
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