package org.zy.gateway.admin.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zy.gateway.admin.client.dao.slaver.NewsDao;
import org.zy.gateway.admin.client.model.News;
import org.zy.gateway.admin.client.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

	@Override
	public Integer insert(News t) {
		return newsDao.insert(t);
	}

	@Override
	public Integer delete(News t) {
		return newsDao.delete(t);
	}

	@Override
	public Integer update(News t) {
		return newsDao.update(t);
	}

	@Override
	public News select(News t) {
		return newsDao.select(t);
	}

	@Override
	public List<News> list(News t) {
		return newsDao.list(t);
	}

}