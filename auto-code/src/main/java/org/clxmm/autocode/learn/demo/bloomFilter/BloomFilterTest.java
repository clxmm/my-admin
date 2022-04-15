package org.clxmm.autocode.learn.demo.bloomFilter;


import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * GoogleGuavalibrary中Google为我们提供了一个布隆过滤器的实现
 */
public class BloomFilterTest {


    private static int size = 1000000;//预计要插入多少数据

    private static double fpp = 0.01;//期望的误判


    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilterStr = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), size, fpp);
        for (int i = 0; i < size; i++) {
            bloomFilterStr.put("test" + i);
        }

        boolean containsValue1 = bloomFilterStr.mightContain("test1");
        boolean containsValue2 = bloomFilterStr.mightContain("test34");
        boolean containsValue3 = bloomFilterStr.mightContain("test1000001");
        System.out.println(containsValue1);
        System.out.println(containsValue2);
        System.out.println(containsValue3);

    }


}
