package com.wildb.account.service;

import com.wildb.account.entity.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 获取用户权限集合
     */
    List<Permission> getPermissions(String keyword);

    /**
     * 根据权限ID获取权限信息
     * @param id
     * @return
     */
    Permission findPermissionById(Integer id);

    /**
     * 根据权限ID删除权限信息
     * @param id
     */
    void deletePermissionById(Integer id);

    /**
     * 根据权限ID更新权限信息
     */
    void updatePermission(Permission permission);


    /**
     * 新增权限
     */
    void addPermission(Permission permission);
}
