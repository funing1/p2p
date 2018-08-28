package cn.hlyc.jms.consumer.impl;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import cn.hlyc.jms.consumer.IMessageSender;

//发送邮件的具体操作类
@Component("emailSender") // 类似于@Service
public class EmailMessageSender implements IMessageSender {
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	@Override
	public void sendMsg(MapMessage message) {
		
		MimeMessage mailmessage = javaMailSenderImpl.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mailmessage);
			helper.setSubject("这是一封p2p的用户认证邮件");
			helper.setText(message.getString("content"),true);
			helper.setFrom("18230467655@163.com");
			helper.setTo(message.getString("to"));
			
			javaMailSenderImpl.send(mailmessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
