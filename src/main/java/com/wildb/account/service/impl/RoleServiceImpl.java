package com.wildb.account.service.impl;

import com.wildb.account.entity.Role;
import com.wildb.account.mapper.RoleMapper;
import com.wildb.account.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRoles(String keyword) {
        List<Role> allRole = this.roleMapper.getAllRole(keyword);
        return allRole;
    }

    @Transactional(readOnly = true)
    @Override
    public Role findRoleById(Integer id) {
        Role role = this.roleMapper.selectRoleById(id);
        return role;
    }

    @Transactional
    @Override
    public void deleteRoleById(Integer id) {
        this.roleMapper.deleteRoleById(id);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        this.roleMapper.updateRole(role);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        this.roleMapper.insertRole(role);
    }
}
