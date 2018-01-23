package com.wildb.account.mapper;

import com.wildb.account.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    @Select("select * from role where id = #{id}")
    Role selectRoleById(Integer id);

    @Delete("delete from role where id = #{id}")
    void deleteRoleById(Integer id);


    @Update("UPDATE role r set r.`name` = #{name} ,r.description = #{description},r.role_level = #{roleLevel} where r.id = #{id}")
    void updateRole(Role role);

    @Insert("INSERT INTO role(id,name,description,role_level) VALUES(#{id},#{name},#{description},#{roleLevel})")
    void insertRole(Role role);
}
