package cn.hlyc.jms.consumer.impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.stereotype.Component;

import cn.hlyc.jms.consumer.IMessageSender;
import cn.hlyc.utils.HttpClientUtil;

//发送短信的具体操作类
@Component("msmSender")
public class MSMMessageSender implements IMessageSender {

	// 用户名
	private static String Uid = "hahaha2";

	// 接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";

	@Override
	public void sendMsg(MapMessage message) {
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		System.out.println(message);

		// UTF发送
		int result = 0;
		try {
			result = client.sendMsgUtf8(Uid, Key, message.getString("content"), message.getString("number"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result > 0) {
			System.out.println("UTF8成功发送条数==" + result);
		} else {
			System.out.println(client.getErrorMsg(result));
		}
		

	}

}
