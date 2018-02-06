package com.wildb.account.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wildb.account.common.beans.ConstantUtil;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.common.utils.MD5Util;
import com.wildb.account.entity.Permission;
import com.wildb.account.entity.Role;
import com.wildb.account.service.PermissionService;
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
@RequestMapping(value = "/admin/permission/")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @GetMapping(value = "getAll")
    public String getAll(PageReq pageReq, Model model){
        PageInfo<Permission> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.PERMISSION_PAGE_PERMISSION_SECTION;
    }


    @GetMapping(value = "deleteById")
    public String deleteById(PageReq pageReq,Model model,Integer id){
        Permission permission = this.permissionService.findPermissionById(id);
        Assert.notNull(permission,ConstantUtil.PERMISSION_NOT_EXIST);
        this.permissionService.deletePermissionById(permission.getId());

        PageInfo<Permission> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.PERMISSION_PAGE_PERMISSION_SECTION;
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(PageReq pageReq,Model model,Permission permission){
        if(!StringUtils.isEmpty(permission) && !StringUtils.isEmpty(permission.getId())){
            this.permissionService.updatePermission(permission);
        }else {
            permission.setId(MD5Util.getIntegerUUID());
            this.permissionService.addPermission(permission);
        }
        return ConstantUtil.REDIRECT_ADMIN_PERMISSION;
    }

    @GetMapping(value = "getPermissionById")
    @ResponseBody
    public ResultBean<Permission> getById(Model model, Integer id){
        Permission permission = this.permissionService.findPermissionById(id);
        Assert.notNull(permission,ConstantUtil.PERMISSION_NOT_EXIST);
        ResultBean<Permission> result = new ResultBean<>(permission);
        model.addAttribute("permission",permission);
        return result;
    }

    private PageInfo<Permission> getAll(PageReq pageReq){
        PageHelper.startPage(pageReq.getPage(),pageReq.getPagesize());
        List<Permission> roles = this.permissionService.getPermissions(pageReq.getKeyword());
        return new PageInfo<>(roles);
    }

}
