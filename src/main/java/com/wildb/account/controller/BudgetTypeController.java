package com.wildb.account.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wildb.account.common.beans.ConstantUtil;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.entity.BudgetType;
import com.wildb.account.service.BudgetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/budgetType/")
public class BudgetTypeController {

    @Resource
    private BudgetTypeService budgetTypeService;

    @GetMapping(value = "getAll")
    public String getAll(PageReq pageReq, Model model){
        PageInfo<BudgetType> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.BUDGET_TYPE_PAGE_SECTION;
    }


    @GetMapping(value = "deleteById")
    public String deleteById(PageReq pageReq,Model model,String id){
        BudgetType budgetType = this.budgetTypeService.findBudgetTypeById(id);
        Assert.notNull(budgetType,ConstantUtil.BudgetType_NOT_EXIST);
        this.budgetTypeService.deleteBudgetTypeById(id);

        PageInfo<BudgetType> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.BUDGET_TYPE_PAGE_SECTION;
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(BudgetType BudgetType){
        if(!StringUtils.isEmpty(BudgetType) && !StringUtils.isEmpty(BudgetType.getId())){
            this.budgetTypeService.updateBudgetType(BudgetType);
        }else {
            this.budgetTypeService.addBudgetType(BudgetType);
        }
        return ConstantUtil.REDIRECT_ADMIN_BUDGET_TYPE;
    }

    @GetMapping(value = "getById")
    @ResponseBody
    public ResultBean<BudgetType> getById(Model model, String id){
        BudgetType budgetType = this.budgetTypeService.findBudgetTypeById(id);
        Assert.notNull(budgetType,ConstantUtil.BudgetType_NOT_EXIST);
        ResultBean<BudgetType> result = new ResultBean<>(budgetType);
        model.addAttribute("budgetType",budgetType);
        return result;
    }

    private PageInfo<BudgetType> getAll(PageReq pageReq){
        PageHelper.startPage(pageReq.getPage(),pageReq.getPagesize());
        List<BudgetType> roles = this.budgetTypeService.getbudgetTypes(pageReq.getKeyword());
        return new PageInfo<>(roles);
    }

}
