package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yeming
 * @date 2019/8/11 20:30
 */
@Component
@RabbitListener(queues = "yeming1")
public class Customer2 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("yeming1:消费消息:"+msg);
    }
}
