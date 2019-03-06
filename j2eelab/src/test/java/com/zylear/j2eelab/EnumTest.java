package com.zylear.j2eelab;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by xiezongyu on 2017/10/11.
 */
public class EnumTest {
    enum MyType {
        unknow(-1),
        auto(1),
        manual(2);

        private Integer value;

        MyType(Integer value) {
            this.value=value;
        }

        public Integer getValue() {
            return value;
        }
    }

    @Test
    public void test(){
        System.out.println(MyType.manual);

        System.out.println(MyType.auto.equals(MyType.auto));

        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
//        set.add(null);
        System.out.println(set.contains(null));

        Random random=new Random();
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
    }

    @Test
    public void testStringEnum(){
        System.out.println(MyType.auto.toString()+MyType.manual.toString());
    }
}
