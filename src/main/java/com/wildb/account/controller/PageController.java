package com.wildb.account.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    private static final String ADMIN = "admin/";
    private static final String COMMON = "common/common";

    @RequestMapping(value = "/admin/{page}")
    public ModelAndView index(@PathVariable(value = "page")String page){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADMIN+page);
        return mv;
    }

    @RequestMapping(value = "/admin/common/common")
    public ModelAndView common(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADMIN+COMMON);
        return mv;
    }
}
