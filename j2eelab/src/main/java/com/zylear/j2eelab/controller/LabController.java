package com.zylear.j2eelab.controller;

import com.zylear.j2eelab.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiezongyu on 2019/3/1.
 */
@Controller
@RequestMapping("/lab")
public class LabController {

    @RequestMapping("/third")
    @ResponseBody
    public String third() {
        return "this is third time";
    }


    @RequestMapping("/session")
    @ResponseBody
    public String cu(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    System.out.println("JSESSIONID: " + cookie.getValue());

                }
            }
        }
        System.out.println("mid: " + request.getSession().getAttribute("mid"));

        request.getSession().setAttribute("mid", System.currentTimeMillis());

        return "this is third time";
    }

    @RequestMapping("student")
    @ResponseBody
    public Student s() {
        return new Student();
    }
}