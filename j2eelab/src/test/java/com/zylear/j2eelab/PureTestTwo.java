package com.zylear.j2eelab;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by xiezongyu on 2019/5/5.
 */
public class PureTestTwo {


    @Test
    public void testTwo() throws Exception {
        List<String> strings = FileUtils.readLines(new File("D:\\nutstore\\Nutstore\\work\\code.txt"), "utf-8");
        System.out.println("select spread_source_code,spread_source_name from t_spread_source where spread_source_code in (");
        for (String string : strings) {
            System.out.print("'" + string + "',");
        }
        System.out.print(")");
    }

    @Test
    public void testTwo1() throws Exception {
        List<String> strings = FileUtils.readLines(new File("D:\\nutstore\\Nutstore\\work\\phone.txt"), "utf-8");
        System.out.println("select * from t_fresh_student_record where phone_number in (");
        for (String string : strings) {
            System.out.print("'" + string + "',");
        }
        System.out.print(")");
    }

}
