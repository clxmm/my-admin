package org.clxmm.autocode.learn.demo.prox.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy  implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(System.currentTimeMillis() + " 进入了方法");
        // 中间是这个方法
        Object result = method.invoke(target, args);
        System.out.println(System.currentTimeMillis() + " 方法执行完毕");
        return result;
    }


    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

}
