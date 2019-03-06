package com.zylear.j2eelab.manager;

import com.zylear.j2eelab.dao.StudentMapper;
import com.zylear.j2eelab.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2019/2/18.
 */
@Component
public class TxTwoManager {

    @Autowired
    private StudentMapper mapper;


    public void test(Student student1) {
        mapper.insert(student1);
    }

}
