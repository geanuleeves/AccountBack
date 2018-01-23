package com.wildb.account.service.impl;

import com.wildb.account.entity.Role;
import com.wildb.account.mapper.RoleMapper;
import com.wildb.account.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoles() {
        List<Role> allRole = this.roleMapper.getAllRole();
        return allRole;
    }

    @Override
    public Role findRoleById(Integer id) {
        Role role = this.roleMapper.selectRoleById(id);
        return role;
    }

    @Override
    public void deleteRoleById(Integer id) {
        this.roleMapper.deleteRoleById(id);
    }

    @Override
    public void updateRole(Role role) {
        this.roleMapper.updateRole(role);
    }

    @Override
    public void addRole(Role role) {
        this.roleMapper.insertRole(role);
    }
}
