package com.wildb.account.service;

import com.wildb.account.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 获取用户角色集合
     */
    List<Role> getRoles(String keyword);

    Role findRoleById(Integer id);

    void deleteRoleById(Integer id);

    void updateRole(Role role);

    void addRole(Role role);
}
