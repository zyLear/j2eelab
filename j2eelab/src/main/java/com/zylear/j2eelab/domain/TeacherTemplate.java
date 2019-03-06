package com.zylear.j2eelab.domain;

import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2017/11/1.
 */

@Component
public class TeacherTemplate {

    private Teacher teacher;

    TeacherTemplate() {
        Teacher teacher = new Teacher();
        teacher.setId(100);
        teacher.setName("teacherTemplate");
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
