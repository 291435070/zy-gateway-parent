package org.zy.gateway.admin.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zy.gateway.admin.client.dao.master.UserInfoDao;
import org.zy.gateway.admin.client.model.UserInfo;
import org.zy.gateway.admin.client.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	@Transactional
	public Integer insert(UserInfo t) {
		Integer r = userInfoDao.insert(t);
		int i = 1/0;
		System.out.println(i);
		return r;
	}

	@Override
	public Integer delete(UserInfo t) {
		return userInfoDao.delete(t);
	}

	@Override
	public Integer update(UserInfo t) {
		return userInfoDao.update(t);
	}

	@Override
	public UserInfo select(UserInfo t) {
		return userInfoDao.select(t);
	}

	@Override
	public List<UserInfo> list(UserInfo t) {
		return userInfoDao.list(t);
	}

}