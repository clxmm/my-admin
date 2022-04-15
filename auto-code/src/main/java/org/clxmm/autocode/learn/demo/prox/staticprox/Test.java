package org.clxmm.autocode.learn.demo.prox.staticprox;

public class Test {

    public static void main(String[] args) {
        new LandlordProxy(new LandlordImpl()).rental();
    }
}
