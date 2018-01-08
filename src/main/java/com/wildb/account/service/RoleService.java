package com.wildb.account.service;

import com.wildb.account.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 获取用户角色集合
     */
    public List<Role> getRoles();
}
