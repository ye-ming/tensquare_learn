package com.tensquare.sms.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
	
	//"【品优购】你的验证码是：1513，3分钟内有效！如果不是本人操作，请忽略本短信。"
	
	@Autowired
	private Environment env;
	
	public String sendSms(String mobile,String smsCode) {
		    String host = "https://dxyzm.market.alicloudapi.com";
		    String path = "/chuangxin/dxjk";
		    String method = "POST";
		    String appcode = env.getProperty("appcode");
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("content", "【品优购】你的验证码是："+smsCode+"，3分钟内有效！如果不是本人操作，请忽略本短信。");
		    querys.put("mobile", mobile);
		    Map<String, String> bodys = new HashMap<String, String>();


		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
		    	System.out.println(response.toString());
		    	
		 
		    	
		    	return response.toString();
		    	//获取response的body
		    	//System.out.println(EntityUtils.toString(response.getEntity()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	return "短信发送失败";
		    }
	}
	
	
	
}
