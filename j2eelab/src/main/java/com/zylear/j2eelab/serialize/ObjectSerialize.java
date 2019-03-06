package com.zylear.j2eelab.serialize;

import com.google.common.primitives.Bytes;

import java.io.ByteArrayOutputStream;

/**
 * Created by xiezongyu on 2018/11/8.
 */
public class ObjectSerialize {


    public static void writtObject(Object object) {

        System.out.println(Integer.toBinaryString(14));


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write(Integer.MAX_VALUE-153);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        System.out.println(Bytes.asList(bytes));


    }


    public static void main(String[] args) {
        writtObject(new Object());
    }

}
