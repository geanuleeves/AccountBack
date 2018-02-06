package com.wildb.account.mapper;

import com.wildb.account.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	List<User> getByMap(Map<String, Object> map);
	List<User> getByRoleId(Map<String, Object> map);
	User getById(Integer id);
	Integer create(User user);
	int update(User user);
	User getByUserName(String userName);

	/**
	 * 根据用户名模糊搜索
	 * @param keyword
	 * @return
	 */
	@Select("select * from user where cnname like CONCAT('%',#{keyword},'%') order by username")
	List<User> getUsersLikeName(String keyword);

	/**
	 * 根据用户ID删除用户
	 * @param id
	 */
	@Delete("delete from user where id = #{id}")
	void deleteUserById(Integer id);
}