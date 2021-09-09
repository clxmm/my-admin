package org.clxmm.myadminjava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MyadminJavaApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Map<String,String> map = new HashMap<>();
        map.put("1","2");
        map.put("3","2");
        map.put("4","2");
        List<String> strings = Arrays.asList("1", "2", "3");
        redisTemplate.opsForValue().set("test134","2");
    }



}
