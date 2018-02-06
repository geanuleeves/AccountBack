package com.wildb.account.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wildb.account.common.beans.ConstantUtil;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.common.utils.MD5Util;
import com.wildb.account.entity.User;
import com.wildb.account.security.UrlUserService;
import com.wildb.account.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/admin/users/")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UrlUserService urlUserService;

    /**
     * 更新登录用户信息
     * @param user
     * @return
     */
    @PutMapping(value = "update")
    @ResponseBody
    public ResultBean updateUser(User user){
//        User user = JSON.parseObject(param, User.class);
        ResultBean resultBean = null;
        Assert.notNull(user,"用户为空");
        int result = this.userService.renewUser(user);
        if (result == 0){
            resultBean = new ResultBean("更新失败");
        }else {
            //重新加载SpringSecurity Principal
            User userDetails = (User) urlUserService.loadUserByUsername(user.getUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            resultBean = new ResultBean("更新成功");
        }
        return resultBean;
    }

    @GetMapping(value = "getAll")
    public String getAll(PageReq pageReq, Model model){
        PageInfo<User> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.USER_PAGE_USER_SECTION;
    }


    @DeleteMapping(value = "deleteById")
    public String deleteById(PageReq pageReq,Model model,Integer id){
        User User = this.userService.findUserById(id);
        Assert.notNull(User,ConstantUtil.USER_NOT_EXIST);
        this.userService.deleteUserById(User.getId());

        PageInfo<User> pageInfo = getAll(pageReq);
        model.addAttribute("result",pageInfo);
        return ConstantUtil.USER_PAGE_USER_SECTION;
    }


    @PostMapping(value = "renew")
    public String update(User user){
        if(!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getId())){
            this.userService.updateUser(user);
        }else {
            user.setId(MD5Util.getIntegerUUID());
            user.setPassword(MD5Util.encode(user.getPassword()));
            this.userService.addUser(user);
        }
        return ConstantUtil.REDIRECT_ADMIN_USER;
    }

    @GetMapping(value = "getUserById")
    @ResponseBody
    public ResultBean<User> getById(Model model, Integer id){
        User user = this.userService.findUserById(id);
        Assert.notNull(user,ConstantUtil.USER_NOT_EXIST);
        ResultBean<User> result = new ResultBean<>(user);
        model.addAttribute("user",user);
        return result;
    }

    private PageInfo<User> getAll(PageReq pageReq){
        PageHelper.startPage(pageReq.getPage(),pageReq.getPagesize());
        List<User> roles = this.userService.getUsers(pageReq.getKeyword());
        return new PageInfo<>(roles);
    }

}