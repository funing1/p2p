package cn.hlyc.jms.consumer;

import javax.jms.MapMessage;

public interface IMessageSender {

	public void sendMsg(MapMessage message);
}
