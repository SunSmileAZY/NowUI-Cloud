package com.nowui.cloud.base.user.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.router.UserRouter;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;

/**
 * 用户新增消息队列
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Configuration
public class UserV1UpdateListener {

    private final String queueName = "user_v1_update";

    @Bean
    Queue UserV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserV1UpdateQueueBindingExchange(Queue UserV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserV1UpdateQueue).to(exchange).with(UserRouter.USER_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserView userView = JSON.parseObject(message, UserView.class);

                System.out.println(userView.toJSONString());
            }

        };
    }

}