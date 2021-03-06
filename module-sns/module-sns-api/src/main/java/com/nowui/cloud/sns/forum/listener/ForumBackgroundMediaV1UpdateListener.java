package com.nowui.cloud.sns.forum.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.forum.service.ForumBackgroundMediaService;
import com.nowui.cloud.sns.forum.router.ForumBackgroundMediaRouter;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 论坛背景新增消息队列
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
//@Configuration
public class ForumBackgroundMediaV1UpdateListener {

    private final String queueName = "forum_background_media_v1_update";

    @Autowired
    private ForumBackgroundMediaService forumBackgroundMediaService;

    @Bean
    Queue ForumBackgroundMediaV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ForumBackgroundMediaV1UpdateQueueBindingExchange(Queue ForumBackgroundMediaV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ForumBackgroundMediaV1UpdateQueue).to(exchange).with(ForumBackgroundMediaRouter.FORUM_BACKGROUND_MEDIA_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ForumBackgroundMediaV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ForumBackgroundMediaV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ForumBackgroundMediaV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ForumBackgroundMediaView forumBackgroundMediaView = JSON.parseObject(message, ForumBackgroundMediaView.class);

                forumBackgroundMediaService.update(forumBackgroundMediaView);
            }

        };
    }

}
