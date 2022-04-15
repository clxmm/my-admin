package org.clxmm.autocode.learn.demo.prox.dynamicproxy;

import org.clxmm.autocode.learn.demo.prox.staticprox.Landlord;
import org.clxmm.autocode.learn.demo.prox.staticprox.LandlordImpl;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {

        Landlord landlord = new LandlordImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(landlord);
        // 这一步是关键
    /*    Landlord landlordProxy = (Landlord) Proxy.newProxyInstance(
                landlord.getClass().getClassLoader(),
                landlord.getClass().getInterfaces(),
                dynamicProxy
        );*/
        Landlord landlordProxy = dynamicProxy.getProxy();

        landlordProxy.rental();



    }
}
