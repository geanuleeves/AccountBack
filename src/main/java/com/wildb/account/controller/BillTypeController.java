package com.wildb.account.controller;

import com.github.pagehelper.PageInfo;
import com.wildb.account.common.beans.ConstantUtil;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.common.utils.MD5Util;
import com.wildb.account.entity.BillType;
import com.wildb.account.service.BillTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/bill_type/")
public class BillTypeController {

    @Resource
    private BillTypeService billTypeService;

    @GetMapping(value = "all")
    public String getAll(PageReq pageReq, Model model){
        PageInfo<BillType> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.BILL_TYPE_PAGE_BILL_TYPE_SECTION;
    }


    @DeleteMapping(value = "delete")
    public String deleteById(PageReq pageReq,Model model,String id){
        BillType billType = this.billTypeService.findById(id);
        Assert.notNull(billType,ConstantUtil.BILL_TYPE_NOT_EXIST);
        this.billTypeService.deleteById(billType.getId());

        PageInfo<BillType> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.BILL_TYPE_PAGE_BILL_TYPE_SECTION;
    }


    @PostMapping(value = "update")
    public String update(PageReq pageReq,Model model,BillType billType){
        if(!StringUtils.isEmpty(billType) && !StringUtils.isEmpty(billType.getId())){
            this.billTypeService.update(billType);
        }else {
            billType.setId(MD5Util.getUUID());
            this.billTypeService.save(billType);
        }
        return ConstantUtil.REDIRECT_ADMIN_BILL_TYPE;
    }

    @GetMapping(value = "get")
    @ResponseBody
    public ResultBean<BillType> getById(Model model, String id){
        BillType billType = this.billTypeService.findById(id);
        Assert.notNull(billType,ConstantUtil.BILL_TYPE_NOT_EXIST);
        ResultBean<BillType> result = new ResultBean<>(billType);
        model.addAttribute("billType",billType);
        return result;
    }

    private PageInfo<BillType> getAll(PageReq pageReq){
        List<BillType> billTypes = this.billTypeService.findLikeName(pageReq);
        return new PageInfo<>(billTypes);
    }
}
