package org.clxmm.autocode.config.stomp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * EnableWebSocketMessageBroker-注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用@MessageMapping
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static long HEART_BEAT = 10000;


//    @Autowired
//    GetHeaderParamInterceptor getHeaderParamInterceptor;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //topic用来广播，user用来实现点对点,单机
        registry.enableSimpleBroker("/stomp/topic", "/user/");

  /*      ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
        te.setPoolSize(1);
        te.setThreadNamePrefix("wss-heartbeat-thread-");
        te.initialize();
        registry.enableSimpleBroker("/user/", "/stomp/topic").setHeartbeatValue(new long[]{HEART_BEAT, HEART_BEAT}).setTaskScheduler(te);
   */
        // 使用RabbitMQ做为消息代理，替换默认的Simple Broker
        //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀,@SendTo(XXX) 也可以重定向
//        registry.setUserDestinationPrefix("/user");   //这是给sendToUser使用,前端订阅需要加上/user
//
//        registry.setApplicationDestinationPrefixes("/stomp/app"); //这是给客户端推送消息到服务器使用 ，推送的接口加上 /stomp/app

        // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
//        registry.enableStompBrokerRelay("/exchange", "/topic", "/queue", "/amq/queue")
////                .setVirtualHost("JCChost") //对应自己rabbitmq里的虚拟host
//                .setRelayHost("localhost")
//                .setClientLogin("guest")
//                .setClientPasscode("guest")
//                .setSystemLogin("guest")
//                .setSystemPasscode("guest")
//                .setSystemHeartbeatSendInterval(5000)
//                .setSystemHeartbeatReceiveInterval(4000);


    }


    /**
     * 开放节点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册两个STOMP的endpoint，分别用于广播和点对点
        //广播
        registry.addEndpoint("/stomp/publicServer").withSockJS();
//        registry.addEndpoint("/stomp/publicServer").setAllowedOrigins("*").withSockJS();

        //点对点
        registry.addEndpoint("/stomp/privateServer").withSockJS();
//        registry.addEndpoint("/stomp/privateServer").setAllowedOrigins("*").withSockJS();

        registry.addEndpoint("/stomp/ws").withSockJS();
    }


//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//        registration.setMessageSizeLimit(500 * 1024 * 1024);
//        registration.setSendBufferSizeLimit(1024 * 1024 * 1024);
//        registration.setSendTimeLimit(200000);
//    }

    /**
     * 采用自定义拦截器，获取connect时候传递的参数
     *
     * @param registration
     */
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(getHeaderParamInterceptor);
//    }
}
