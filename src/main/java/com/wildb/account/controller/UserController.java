package com.wildb.account.controller;

import com.wildb.account.common.beans.ResultBean;
import com.wildb.account.entity.User;
import com.wildb.account.security.UrlUserService;
import com.wildb.account.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/admin/users/")
@RestController
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
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean updateUser(User user){
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

}