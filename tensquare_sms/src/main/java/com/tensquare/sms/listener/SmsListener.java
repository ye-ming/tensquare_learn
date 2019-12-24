package com.tensquare.sms.listener;

import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yeming
 * @date 2019/8/17 21:27
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtill;

    @RabbitHandler
    public void sendSms(Map<String,String> map){
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");
        //发送短信
        smsUtill.sendSms(mobile,checkcode);

        System.out.println("手机号:"+map.get("mobile"));
        System.out.println("验证码:"+map.get("checkcode"));
    }
}
