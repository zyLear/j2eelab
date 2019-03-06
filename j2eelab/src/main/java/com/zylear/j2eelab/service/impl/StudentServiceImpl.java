package com.zylear.j2eelab.service.impl;

import com.zylear.j2eelab.dao.StudentMapper;
import com.zylear.j2eelab.domain.Student;
import com.zylear.j2eelab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xiezongyu on 2017/9/22.
 */


@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(int id, String name) {
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        studentMapper.insert(student);
    }

    @PostConstruct
    public void teste(){
        System.out.println("this is studentImpl  test ,postConstruct");
    }
}
