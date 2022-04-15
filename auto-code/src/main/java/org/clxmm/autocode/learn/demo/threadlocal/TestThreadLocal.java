package org.clxmm.autocode.learn.demo.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadLocal {


    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000; i++) {
                    TestClass t = new TestClass(i);
                    t.printId();
                    // 有内存溢出的问题
//                    t = null;
                    // 清除
                    t.threadLocal.remove();
                }
            }
        }).start();

    }


    static class TestClass {
        private int id;
        private int[] arr;
        private ThreadLocal<TestClass> threadLocal;

        TestClass(int id) {
            this.id = id;
            arr = new int[1_000_000];
            threadLocal = new ThreadLocal<>();
            threadLocal.set(this);
        }

        public void printId() {
            System.out.println(this.threadLocal.get().id);
        }
    }

}


class ThreadLocalHashCodeTest {
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.print(nextHashCode() & 15);
            System.out.print(" ");
        }
    }




}