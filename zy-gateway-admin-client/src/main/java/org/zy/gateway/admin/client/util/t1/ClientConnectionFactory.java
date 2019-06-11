package org.zy.gateway.admin.client.util.t1;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ClientConnectionFactory implements KeyedPooledObjectFactory<String, ClientConnection> {

	@Override
	public PooledObject<ClientConnection> makeObject(String key) throws Exception {
		ClientConnection client = new ClientConnection();
		client.setUrl(key);
		return new DefaultPooledObject<>(client);
	}

	@Override
	public void destroyObject(String key, PooledObject<ClientConnection> p) throws Exception {
		p.getObject().setActive(false);
	}

	@Override
	public boolean validateObject(String key, PooledObject<ClientConnection> p) {
		return p.getObject().getActive();
	}

	@Override
	public void activateObject(String key, PooledObject<ClientConnection> p) throws Exception {
		p.getObject().setActive(true);
	}

	@Override
	public void passivateObject(String key, PooledObject<ClientConnection> p) throws Exception {
	}

}