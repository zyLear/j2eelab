package com.zylear.j2eelab.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2019/3/8.
 */
@Controller
@RequestMapping("/view")
public class PageController {

    @RequestMapping("/view1")
    public ModelAndView string() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("first");
        return modelAndView;
    }

}
