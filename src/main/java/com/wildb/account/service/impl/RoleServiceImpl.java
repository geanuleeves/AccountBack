package com.wildb.account.service.impl;

import com.wildb.account.entity.Role;
import com.wildb.account.mapper.RoleMapper;
import com.wildb.account.service.RoleService;
import org.springframework.stereotype.Service;

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
}
