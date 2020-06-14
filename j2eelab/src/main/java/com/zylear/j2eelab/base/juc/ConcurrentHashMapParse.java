package com.zylear.j2eelab.base.juc;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapParse {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        System.out.println(map);

        Hashtable<String, String> ht = new Hashtable<>();
        ht.get("s");
    }




}
