package org.clxmm.autocode.learn.demo.threadlocal.weakr;


import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.lang.ref.WeakReference;

public class TestWeakReference {


    public static void main(String[] args) {
        ProProduct productA = new ProProduct();
        productA.setI(100);
        // 弱引用对象
        WeakReference<ProProduct> weakProductA = new WeakReference<>(productA);


        ProProduct proProduct = weakProductA.get();
        System.out.println(productA.i);
    }

}




@Data
class ProProduct {
    int i;
}