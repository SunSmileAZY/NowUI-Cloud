package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.router.MemberRouter;
import com.nowui.cloud.member.member.view.MemberView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员	新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
//@Configuration
public class MemberV1DeleteListener {

    private final String queueName = "member_v1_delete";

    @Autowired
    private MemberService memberService;

    @Bean
    Queue MemberV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberV1DeleteQueueBindingExchange(Queue MemberV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberV1DeleteQueue).to(exchange).with(MemberRouter.MEMBER_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberView memberView = JSON.parseObject(message, MemberView.class);

                memberService.save(memberView);
            }

        };
    }

}
