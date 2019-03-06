package com.zylear.j2eelab.manager;

import com.zylear.j2eelab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2017/9/22.
 */

@Component
public class StudentManager {

    @Autowired
    StudentService studentService;

    public void addStudent(int id, String name) {
        studentService.addStudent(id, name);
    }
}
