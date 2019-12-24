package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yeming
 * @date 2019/8/11 20:30
 */
@Component
@RabbitListener(queues = "yeming2")
public class Customer3 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("yeming2消费消息:"+msg);
    }
}
