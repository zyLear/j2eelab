package com.zylear.j2eelab.manager;

import com.zylear.j2eelab.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2017/11/1.
 */
@Component
public class TeacherManager {


    Teacher teacher;

    public void show() {
        System.out.println(teacher.toString());
    }




    @Autowired
    public void setTeacher(@Qualifier("testTemplateTeacher") Teacher teacher) {
        this.teacher = teacher;
    }
}
