package com.zylear.j2eelab.proxy.jdk;

import com.zylear.j2eelab.proxy.Enhance;
import com.zylear.j2eelab.proxy.UserService;
import com.zylear.j2eelab.proxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiezongyu on 2018/11/7.
 */
public class JdkProxyFactory {

    public static Enhance enhance = new Enhance();


    public static Object createProxy(final Object param) {
        Object object = Proxy.newProxyInstance(param.getClass().getClassLoader(), param.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        enhance.before();
                        Object object = method.invoke(param, args);
                        enhance.after();
                        return object;
                    }
                });
        return object;
    };


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService proxy = (UserService) JdkProxyFactory.createProxy(userService);
        proxy.show();
    }
}

//class _$edf{
//
//}