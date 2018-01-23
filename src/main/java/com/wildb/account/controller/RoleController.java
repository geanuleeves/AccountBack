package com.wildb.account.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wildb.account.common.beans.ConstantUtil;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.common.utils.MD5Util;
import com.wildb.account.entity.Role;
import com.wildb.account.service.RoleService;
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
@RequestMapping(value = "/admin/role/")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping(value = "getRoles")
    public String getRoles(PageReq pageReq,Model model){
        PageInfo<Role> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.ROLE_PAGE_ROLES_SECTION;
    }

    @GetMapping(value = "deleteById")
    public String deleteById(PageReq pageReq,Model model,Integer id){
        Role role = this.roleService.findRoleById(id);
        Assert.notNull(role,ConstantUtil.ROLE_NOT_EXIST);
        this.roleService.deleteRoleById(role.getId());

        PageInfo<Role> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.ROLE_PAGE_ROLES_SECTION;
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(PageReq pageReq,Model model,Role role){
        if(!StringUtils.isEmpty(role) && !StringUtils.isEmpty(role.getId())){
            this.roleService.updateRole(role);
        }else {
            role.setId(MD5Util.getIntegerUUID());
            this.roleService.addRole(role);
        }
        return ConstantUtil.REDIRECT_ADMIN_ROLE;
    }

    @GetMapping(value = "getById")
    @ResponseBody
    public ResultBean<Role> getById(Model model,Integer id){
        Role role = this.roleService.findRoleById(id);
        Assert.notNull(role,ConstantUtil.ROLE_NOT_EXIST);
        ResultBean<Role> result = new ResultBean<>(role);
        model.addAttribute("role",role);
        return result;
    }

    private PageInfo<Role> getAll(PageReq pageReq){
        PageHelper.startPage(pageReq.getPage(),pageReq.getPagesize());
        List<Role> roles = this.roleService.getRoles(pageReq.getKeyword());
        return new PageInfo<>(roles);
    }

}
