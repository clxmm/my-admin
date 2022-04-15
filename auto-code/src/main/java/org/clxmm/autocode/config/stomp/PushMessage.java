package org.clxmm.autocode.config.stomp;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.clxmm.autocode.api.vo.stomp.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


// https://blog.csdn.net/qq_35387940/article/details/120068362

@Service
public class PushMessage {

    @Autowired
    private SimpMessagingTemplate template;

    public void send(String msgJson) {

        System.out.println("步骤2.即将推送给websocket server：");

        JSONObject object = JSONObject.parseObject(msgJson);


        Message message = JSON.parseObject(msgJson, Message.class);



        System.out.println("步骤2.1 消息发给：" + message.getTo());
        System.out.println("步骤2.2 发送内容是：" + message.getContent());
        template.convertAndSendToUser(message.getTo(), "/stomp/message", message.getContent());
        System.out.println("----------消息发送完毕----------");

        //广播
        // template.convertAndSend("/topic/public",chatMessage);
    }



}
