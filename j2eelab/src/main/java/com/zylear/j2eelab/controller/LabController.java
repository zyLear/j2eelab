package com.zylear.j2eelab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiezongyu on 2019/3/1.
 */
@Controller
@RequestMapping("/lab")
public class LabController {

    @RequestMapping("/third")
    @ResponseBody
    public String third(){
        return "this is third time";
    }

}
