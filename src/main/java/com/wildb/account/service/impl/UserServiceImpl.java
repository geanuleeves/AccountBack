package com.wildb.account.service.impl;

import com.wildb.account.entity.User;
import com.wildb.account.mapper.UserMapper;
import com.wildb.account.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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
	public List<User> getUsers(String keyword) {
		return this.userMapper.getUsersLikeName(keyword);
	}

	@Override
	public User findUserById(Integer id) {
		return this.userMapper.getById(id);
	}

	@Override
	public void deleteUserById(Integer id) {
		this.userMapper.deleteUserById(id);
	}

	@Override
	public void updateUser(User user) {
		this.userMapper.update(user);
	}

	@Override
	public void addUser(User user) {
		this.userMapper.create(user);
	}


	@Override
	public int renewUser(User user) {
		return this.userMapper.update(user);
	}

}