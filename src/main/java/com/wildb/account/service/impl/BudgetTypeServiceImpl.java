package com.wildb.account.service.impl;

import com.wildb.account.entity.BudgetType;
import com.wildb.account.mapper.BudgetTypeMapper;
import com.wildb.account.service.BudgetTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BudgetTypeServiceImpl implements BudgetTypeService {

    @Resource
    private BudgetTypeMapper budgetTypeMapper;

    @Override
    public List<BudgetType> getbudgetTypes(String keyword) {
        List<BudgetType> budgetTypeList = this.budgetTypeMapper.selectBudgetTypeLikeName(keyword);
        return budgetTypeList;
    }

    @Override
    public BudgetType findBudgetTypeById(String id) {
        return this.budgetTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteBudgetTypeById(String id) {
        this.budgetTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBudgetType(BudgetType budgetType) {
        this.budgetTypeMapper.updateByPrimaryKey(budgetType);
    }

    @Override
    public void addBudgetType(BudgetType budgetType) {
        this.budgetTypeMapper.insert(budgetType);
    }
}
