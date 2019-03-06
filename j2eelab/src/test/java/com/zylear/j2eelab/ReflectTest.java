package com.zylear.j2eelab;

import com.zylear.j2eelab.domain.Student;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xiezongyu on 2017/10/19.
 */
public class ReflectTest {



    @Test
    public void test(){
        Class studentClass = null;
        try {
            studentClass=Class.forName("com.zylear.j2eelab.domain.Student");
            Student student = (Student) studentClass.newInstance();

            Method[]  methods=studentClass.getMethods();

            methods[2].invoke(student, "sdfsdfsfsdfw");
            methods[3].invoke(student, 1234);
            System.out.println(methods[1].invoke(student));
            System.out.println(methods[0].invoke(student));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}

