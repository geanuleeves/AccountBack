package com.wildb.account.controller;

import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.entity.Role;
import com.wildb.account.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRoles(Model model){
        List<Role> roles = this.roleService.getRoles();
        model.addAttribute("roles",roles);
        return "admin/role";
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResultBean getAll(){
        List<Role> roles = this.roleService.getRoles();
        ResultBean resultBean = new ResultBean(roles);
        return resultBean;
    }
}
