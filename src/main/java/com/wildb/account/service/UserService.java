package com.wildb.account.service;

import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.entity.User;

public interface UserService{

    /**
     * 更新系统用户信息
     * @param user
     * @return
     */
    public int renewUser(User user);

    /**
     * 根据用户ID获取用户信息
     */
    public User getById(Integer id);
}
