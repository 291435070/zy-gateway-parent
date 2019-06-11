package org.zy.gateway.admin.client.dao;

import java.util.List;

public interface BaseDao<T> {

	Integer insert(T t);

	Integer delete(T t);

	Integer update(T t);

	T select(T t);

	List<T> list(T t);
}