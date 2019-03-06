package com.zylear.j2eelab.proxy.cglib;

import com.zylear.j2eelab.proxy.Enhance;
import com.zylear.j2eelab.proxy.UserService;
import com.zylear.j2eelab.proxy.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiezongyu on 2018/11/7.
 */
public class CglibProxyFactory {

    private static Enhance enhance = new Enhance();

    public static Object createProxy(final Object param) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(param.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                enhance.before();
                Object object = method.invoke(param, args);
                enhance.after();
                return object;
            }
        });

        return enhancer.create();
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService proxy = (UserService) CglibProxyFactory.createProxy(userService);
        proxy.show();
    }


}
