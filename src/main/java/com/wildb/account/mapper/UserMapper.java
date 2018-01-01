package com.wildb.account.mapper;



import com.wildb.account.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	List<User> getByMap(Map<String, Object> map);
	List<User> getByRoleId(Map<String, Object> map);
	User getById(Integer id);
	Integer create(User user);
	int update(User user);
	User getByUserName(String userName);
}