package com.wildb.account.service;

import com.wildb.account.entity.User;

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
}
