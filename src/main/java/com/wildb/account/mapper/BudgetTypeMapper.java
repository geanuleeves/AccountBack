package com.wildb.account.mapper;

import com.wildb.account.common.utils.MyMapper;
import com.wildb.account.entity.BudgetType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BudgetTypeMapper extends MyMapper<BudgetType> {

    /**
     * 根据分类预算名称获取分类预算集合
     * @param name
     * @return
     */
    @Select("select * from budget_type where name like CONCAT('%',#{keyword},'%') order by name ")
    List<BudgetType> selectBudgetTypeLikeName(String name);
}