package org.clxmm.autocode.config.redis;

import org.clxmm.autocode.config.stomp.InjectServiceUtil;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

// 在redisconfig中绑定这个监听器的主题
public class RedisListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("步骤1.监听到需要进行负载转发的消息：" + message.toString());
        InjectServiceUtil.getInstance().pushMessage().send(message.toString());
        // pushMessage 进行websocket推送




    }


}
