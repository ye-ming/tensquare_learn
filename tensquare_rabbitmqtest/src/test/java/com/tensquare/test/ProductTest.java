package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yeming
 * @date 2019/8/11 20:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("yeming","测试直接模式");
    }

    @Test
    public void sendMsg1(){
        rabbitTemplate.convertAndSend("yeming1","","叶铭1:分裂");
    }

    @Test
    public void sendMsg2(){
        rabbitTemplate.convertAndSend("yeming2","","叶铭2:分裂");
    }

    /**
     * 主题模式
     */
    @Test
    public void sendMsg3(){
        rabbitTemplate.convertAndSend("topic_test","yeming.abc","主题模式测试");
    }
}
