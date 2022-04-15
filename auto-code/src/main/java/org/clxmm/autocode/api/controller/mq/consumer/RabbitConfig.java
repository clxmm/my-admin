package org.clxmm.autocode.api.controller.mq.consumer;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息确认回调函数
 */
@Configuration
public class RabbitConfig {

    /**
     * 消息推送到server，但是在server里找不到交换机
     * 消息推送到server，找到交换机了，但是没找到队列
     * 消息推送到sever，交换机和队列啥都没找到
     * 消息推送成功
     * @param connectionFactory
     * @return
     */

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
            System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
            System.out.println("ConfirmCallback:     " + "原因：" + cause);
        });


        rabbitTemplate.setReturnsCallback((e) -> {
            System.out.println("ReturnCallback:     " + "消息：" + e.getMessage());
            System.out.println("ReturnCallback:     " + "回应码：" + e.getReplyCode());
            System.out.println("ReturnCallback:     " + "回应信息：" + e.getReplyText());
            System.out.println("ReturnCallback:     " + "交换机：" + e.getExchange());
            System.out.println("ReturnCallback:     " + "路由键：" + e.getRoutingKey());
        });


        return rabbitTemplate;
    }


}
