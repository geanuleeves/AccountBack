package com.wildb.account.mapper;



import com.wildb.account.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {

    List<Permission> getByMap(Map<String, Object> map);

    Permission getById(Integer id);

    Integer create(Permission permission);

    int update(Permission permission);

    List<Permission> getByUserId(Integer userId);

    /**
     * 根据搜索关键字获取所有权限信息
     * @param keyword
     * @return
     */
    @Select("select * from permission where name like CONCAT('%',#{keyword},'%') order by name")
    List<Permission> getAllPermission(String keyword);

    /**
     * 根据权限ID删除权限信息
     * @param id
     */
    @Delete("delete from permission where id = #{id}")
    void deletePermissionById(Integer id);
}