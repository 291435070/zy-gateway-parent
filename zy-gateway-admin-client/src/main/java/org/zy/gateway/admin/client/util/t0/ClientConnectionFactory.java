package org.zy.gateway.admin.client.util.t0;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ClientConnectionFactory implements PooledObjectFactory<ClientConnection> {

	@Override
	public void activateObject(PooledObject<ClientConnection> p) throws Exception {
		p.getObject().setActive(true);
	}

	@Override
	public void destroyObject(PooledObject<ClientConnection> p) throws Exception {
		p.getObject().setActive(false);
	}

	@Override
	public PooledObject<ClientConnection> makeObject() throws Exception {
		ClientConnection client = new ClientConnection();
		return new DefaultPooledObject<>(client);
	}

	@Override
	public void passivateObject(PooledObject<ClientConnection> arg0) throws Exception {
	}

	@Override
	public boolean validateObject(PooledObject<ClientConnection> p) {
		return p.getObject().getActive();
	}

}