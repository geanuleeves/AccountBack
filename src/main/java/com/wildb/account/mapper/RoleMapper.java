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
     * 获取所有角色集合
     * @return
     */
    @Select("select * from role where name like CONCAT('%',#{keyword},'%')   order by role_level")
    List<Role> getAllRole(String keyword);


    /**
     * 根据角色ID获取角色信息
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    Role selectRoleById(Integer id);


    /**
     * 根据角色ID删除角色
     * @param id
     */
    @Delete("delete from role where id = #{id}")
    void deleteRoleById(Integer id);


    /**
     * 更新角色
     * @param role
     */
    @Update("UPDATE role r set r.`name` = #{name} ,r.description = #{description},r.role_level = #{roleLevel} where r.id = #{id}")
    void updateRole(Role role);

    /**
     * 新增角色
     * @param role
     */
    @Insert("INSERT INTO role(id,name,description,role_level) VALUES(#{id},#{name},#{description},#{roleLevel})")
    void insertRole(Role role);
}
