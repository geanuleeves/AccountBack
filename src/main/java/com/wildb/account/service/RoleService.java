package com.wildb.account.service;

import com.wildb.account.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 获取用户角色集合
     */
    List<Role> getRoles(String keyword);

    /**
     * 根据角色ID获取角色信息
     * @param id
     * @return
     */
    Role findRoleById(Integer id);

    /**
     * 根据角色ID删除角色信息
     * @param id
     */
    void deleteRoleById(Integer id);

    /**
     * 根据角色ID更新角色信息
     * @param role
     */
    void updateRole(Role role);


    /**
     * 新增角色
     * @param role
     */
    void addRole(Role role);
}
