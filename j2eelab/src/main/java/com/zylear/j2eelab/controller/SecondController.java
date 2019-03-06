package com.zylear.j2eelab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/1/23.
 */
@Controller
public class SecondController {


    @RequestMapping(value = "jsp")
    public ModelAndView test() {
        System.out.println("enter !!!!!!!!!");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("first");
        return modelAndView;
    }
}
