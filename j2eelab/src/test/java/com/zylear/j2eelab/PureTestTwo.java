package com.zylear.j2eelab;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
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

    @Test
    public void split() {
        String s = "1";
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            String[] split = s.split("-");
        }
        System.out.println(System.currentTimeMillis() - l);

        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            String[] split = StringUtils.split(s, "-");
        }
        System.out.println(System.currentTimeMillis() - time);


//        System.out.println(Arrays.asList(split));
    }

}
