package com.wildb.account.service;

import com.wildb.account.entity.User;
import com.wildb.account.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserService {
//	@Resource
//	private SessionRegistry sessionRegistry;

	@Resource
	private UserMapper userMapper;

	public User getById(Integer id) {
		User user = userMapper.getById(id);
		return user;
	}
	

}