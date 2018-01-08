package com.wildb.account.mapper;

import com.wildb.account.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    /**
     * 根据用户ID获取用户角色集合
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Integer userId);


    /**
     * 根据权限ID获取用户角色实体
     * @param pid
     * @return
     */
    Role selectRoleByPermissionIdAndUserId(Integer pid,Integer userId);

    /**
     * 获取所有角色集合
     * @return
     */
    @Select("select * from role")
    List<Role> getAllRole();
}
