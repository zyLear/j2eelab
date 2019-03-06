package com.zylear.j2eelab.controller;

import com.zylear.j2eelab.annotation.GenericRequest;
import com.zylear.j2eelab.manager.StudentManager;
import com.zylear.j2eelab.manager.TeacherManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiezongyu on 2017/9/18.
 */

@RestController
public class FirstController {

    @Autowired
    private StudentManager studentManager;
    @Autowired
    private TeacherManager teacherManager;

    @RequestMapping("/first")
    @GenericRequest
    public  synchronized String first(){
        try {
            System.out.println("kaishi");
            Thread.sleep(30000);
            System.out.println("endd d s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this is first time!";
    }

    @RequestMapping("/second")
    public String second(){
        return "this if second time!";
    }


    @RequestMapping("/third")
    public String third(){
        return "this is third time";
    }

    @RequestMapping("/view")
    public ModelAndView myView(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("my_view");
        mav.addObject("object", "this is my view");
        return mav;
    }

    @RequestMapping("/addStudent")
    public String addStudent(HttpServletRequest request){
        studentManager.addStudent(Integer.parseInt(request.getParameter("id")),request.getParameter("name"));
        return "OK";
    }


    @RequestMapping("/show")
    public String show(){
        teacherManager.show();
        return "OK";
    }

}
