package com.wildb.account.service.impl;

import com.wildb.account.entity.Permission;
import com.wildb.account.entity.Role;
import com.wildb.account.mapper.PermissionMapper;
import com.wildb.account.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Resource
    private PermissionMapper permissionMapper;


    @Transactional(readOnly = true)
    @Override
    public List<Permission> getPermissions(String keyword) {
        List<Permission> allPermission = this.permissionMapper.getAllPermission(keyword);
        return allPermission;
    }

    @Transactional(readOnly = true)
    @Override
    public Permission findPermissionById(Integer id) {
        Permission permission = this.permissionMapper.getById(id);
        return permission;
    }

    @Transactional
    @Override
    public void deletePermissionById(Integer id) {
        this.permissionMapper.deletePermissionById(id);
    }

    @Transactional
    @Override
    public void updatePermission(Permission permission) {
        this.permissionMapper.update(permission);
    }

    @Transactional
    @Override
    public void addPermission(Permission permission) {
        this.permissionMapper.create(permission);
    }
}
