package com.wildb.account.service;

import com.wildb.account.entity.BudgetType;

import java.util.List;

public interface BudgetTypeService {

    /**
     * 获取预算分类集合
     */
    List<BudgetType> getbudgetTypes(String keyword);

    /**
     * 根据预算分类ID获取预算分类信息
     * @param id
     * @return
     */
    BudgetType findBudgetTypeById(String id);

    /**
     * 根据预算分类ID删除预算分类信息
     * @param id
     */
    void deleteBudgetTypeById(String id);

    /**
     * 根据预算分类ID更新预算分类信息
     */
    void updateBudgetType(BudgetType budgetType);


    /**
     * 新增预算分类
     */
    void addBudgetType(BudgetType budgetType);
}
