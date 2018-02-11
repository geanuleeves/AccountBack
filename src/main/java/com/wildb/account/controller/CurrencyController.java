package com.wildb.account.controller;

import com.github.pagehelper.PageInfo;
import com.wildb.account.common.beans.ConstantUtil;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.common.utils.MD5Util;
import com.wildb.account.entity.Currency;
import com.wildb.account.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/currency")
public class CurrencyController {

    @Resource
    private CurrencyService currencyService;

    @GetMapping(value = "all")
    public String getAll(PageReq pageReq, Model model){
        PageInfo<Currency> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.CURRENCY_PAGE_CURRENCY_SECTION;
    }


    @DeleteMapping(value = "delete")
    public String deleteById(PageReq pageReq,Model model,String id){
        Currency currency = this.currencyService.findById(id);
        Assert.notNull(currency,ConstantUtil.CURRENCY_NOT_EXIST);
        this.currencyService.deleteById(currency.getId());

        PageInfo<Currency> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.CURRENCY_PAGE_CURRENCY_SECTION;
    }


    @PostMapping(value = "update")
    public String update(PageReq pageReq,Model model,Currency currency){
        if(!StringUtils.isEmpty(currency) && !StringUtils.isEmpty(currency.getId())){
            this.currencyService.update(currency);
        }else {
            currency.setId(MD5Util.getUUID());
            this.currencyService.save(currency);
        }
        return ConstantUtil.REDIRECT_ADMIN_CURRENCY;
    }

    @GetMapping(value = "get")
    @ResponseBody
    public ResultBean<Currency> getById(Model model, String id){
        Currency currency = this.currencyService.findById(id);
        Assert.notNull(currency,ConstantUtil.CURRENCY_NOT_EXIST);
        ResultBean<Currency> result = new ResultBean<>(currency);
        model.addAttribute("currency",currency);
        return result;
    }

    private PageInfo<Currency> getAll(PageReq pageReq){
        List<Currency> currencies = this.currencyService.findLikeName(pageReq);
        return new PageInfo<>(currencies);
    }
}
