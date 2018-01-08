package com.wildb.account.service.impl;

import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.entity.User;
import com.wildb.account.mapper.UserMapper;
import com.wildb.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;


	@Override
	public User getById(Integer id) {
		User user = userMapper.getById(id);
		return user;
	}


	@Override
	public int renewUser(User user) {
		return this.userMapper.update(user);
	}

}