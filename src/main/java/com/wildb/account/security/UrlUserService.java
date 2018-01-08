package com.wildb.account.security;

import com.wildb.account.entity.Permission;
import com.wildb.account.entity.Role;
import com.wildb.account.entity.User;
import com.wildb.account.mapper.PermissionMapper;
import com.wildb.account.mapper.RoleMapper;
import com.wildb.account.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyibo on 17/2/7.
 */
@Service
@Slf4j
public class UrlUserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        User user = this.userMapper.getByUserName(userName);
        List<Role> roles = null;
        if (user != null) {
            roles = this.roleMapper.selectRolesByUserId(user.getId());
            Assert.notEmpty(roles,"当前用户账号为："+user.getUsername() + " ，用户角色为空");

            List<Permission> permissions = this.permissionMapper.getByUserId(user.getId());
            Assert.notEmpty(permissions,"当前用户账号为："+user.getUsername() + "，用户权限为空");

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                    GrantedAuthority grantedAuthority = new UrlGrantedAuthority(permission.getName(),permission.getPermissionUrl(),permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }

            user.setPermissions(permissions);
            user.setRoles(roles);
            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
        }
    }
}