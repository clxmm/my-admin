package org.clxmm.autocode.api.controller.stomp;

import com.alibaba.fastjson.JSON;
import org.clxmm.autocode.api.vo.stomp.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("stomp")
public class StompTestController {

    @Autowired
    public SimpMessagingTemplate template;

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisTemplate redisTemplate;


    @RequestMapping("publicExample")
    public String publicExample() {
        System.out.println("publicExample");
        return "publicExample.html";
    }

    /**
     * 广播
     *
     * @param msg
     */
    @ResponseBody
    @PostMapping("/pushToAll")
    public void pushToAll(@RequestBody(required = false) Message message) {

        System.out.println(message);
        Message msg = new Message();
        msg.setForm("c测");
        msg.setContent("测试");

        template.convertAndSend("/stomp/topic/all", msg.getContent());
    }


    @GetMapping("privateExample")
    public String privateExample () {
        return "privateExample.html";
    }

    /**
     * 点对点
     */
    @ResponseBody
    @PostMapping("/pushToOne")
    public void pushToOne(@RequestBody Message msg) {
        System.out.println("进入方法");
        /*使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
        "/user/" + 用户Id + "/message",其中"/user"是固定的*/
        System.out.println(msg);
        template.convertAndSendToUser(msg.getTo(), "/stomp/message", msg.getContent());

    }


    @ResponseBody
    @PostMapping("/pushToOneTest")
    public String pushToOneTest(@RequestBody Message message) {
        String messageJson = JSON.toJSONString(message);
        System.out.println("！！！系统准备做消息推送！！！");
//        stringRedisTemplate.convertAndSend("webSocketMsgPush",messageJson);
        redisTemplate.convertAndSend("webSocketMsgPush",message);
        return "发送成功";
    }


}
