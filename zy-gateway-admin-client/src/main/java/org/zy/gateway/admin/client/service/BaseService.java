package org.zy.gateway.admin.client.service;

import java.util.List;

public interface BaseService<T> {

	Integer insert(T t);

	Integer delete(T t);

	Integer update(T t);

	T select(T t);

	List<T> list(T t);
}