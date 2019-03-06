package com.zylear.j2eelab.proxy;

/**
 * Created by xiezongyu on 2018/11/7.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void show() {
        System.out.println("user service show");
    }
}
