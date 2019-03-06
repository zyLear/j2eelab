package com.zylear.j2eelab.manager;

import com.zylear.j2eelab.annotation.GenericRequest;
import com.zylear.j2eelab.dao.StudentMapper;
import com.zylear.j2eelab.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiezongyu on 2018/8/30.
 */
@Component
public class TxManager {

    @Autowired
    private StudentMapper mapper;
    @Autowired
    private TxTwoManager txTwoManager;


    public void testt(Student student, Student student1) {
        insert(student,student1);
    }

    @Transactional
    @GenericRequest
    public void insert(Student student, Student student1) {
        mapper.insert(student);
        System.out.println("insert 1");
//        student1.setId(300);
//        mapper.insert(student1);
//        txTwoManager.test(student1);


//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
//
//            @Override
//            public void beforeCommit(boolean readOnly) {
//                System.out.println("before commit ");
//                throw new RuntimeException("ee");
//            }
//        });

//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
//            @Override
//            public void afterCommit() {
//                System.out.println("afete commit");
//                throw new RuntimeException("ee");
//            }
//        });
//        test(student1);
    }

    public void test(Student student1) {
        mapper.insert(student1);
    }


}
