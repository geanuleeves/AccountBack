package com.wildb.account.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    private static final String ADMIN = "admin/";
    private static final String COMMON = "common/common";

    @RequestMapping(value = "/admin/{page}")
    public ModelAndView page(@PathVariable(value = "page") String page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADMIN + page);
        return mv;
    }

    @RequestMapping(value = "/admin/common/common")
    public ModelAndView common() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADMIN + COMMON);
        return mv;
    }
}
