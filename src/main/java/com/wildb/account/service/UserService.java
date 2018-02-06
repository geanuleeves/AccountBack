package com.wildb.account.service;

import com.wildb.account.entity.User;

import java.util.List;

public interface UserService{

    /**
     * 更新系统用户信息
     * @param user
     * @return
     */
    int renewUser(User user);

    /**
     * 根据用户ID获取用户信息
     */
    User getById(Integer id);

    /**
     * 获取用户集合
     */
    List<User> getUsers(String keyword);

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 根据ID删除用户信息
     * @param id
     */
    void deleteUserById(Integer id);

    /**
     * 根据ID更新用户信息
     */
    void updateUser(User User);


    /**
     * 新增用户
     */
    void addUser(User User);
}
