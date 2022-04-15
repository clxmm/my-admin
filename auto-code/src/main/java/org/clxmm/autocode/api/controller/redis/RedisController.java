package org.clxmm.autocode.api.controller.redis;


import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.util.redis.RedisDBChangeUtil;
import org.clxmm.autocode.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("redis")
@Slf4j
public class RedisController {


    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @GetMapping("sendRedisMessageTest")
    public String SendRedisMessage() {
        log.info("Sending message...");
        //第一个参数是，消息推送的主题名称；第二个参数是，要推送的消息信息
        //"chat"->主题
        //"我是一条消息"->要推送的消息
        stringRedisTemplate.convertAndSend("chat", "我是一条消息");
        return "Send Success";
    }

    @Autowired
    RedisDBChangeUtil redisDBChangeUtil;



    @Autowired
    private RedisUtils redisUtils;


    @GetMapping("test1")
    public void test1() {
        redisUtils.set("t1", "v1");
        redisDBChangeUtil.setDataBase(6);
        redisUtils.set("t1", "v1");
        redisDBChangeUtil.setDataBase(7);



    }









}
