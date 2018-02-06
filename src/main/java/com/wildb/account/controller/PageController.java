package com.wildb.account.controller;


import com.wildb.account.common.beans.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {


    @RequestMapping(value = "/admin/{page}")
    public ModelAndView page(@PathVariable(value = "page") String page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantUtil.ADMIN + page);
        return mv;
    }

    @RequestMapping(value = "/error/{page}")
    public ModelAndView error(@PathVariable(value = "page") String page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantUtil.ERROR + page);
        return mv;
    }

}
