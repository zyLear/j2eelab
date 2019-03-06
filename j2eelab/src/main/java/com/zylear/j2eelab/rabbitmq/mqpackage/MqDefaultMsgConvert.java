package com.zylear.j2eelab.rabbitmq.mqpackage;


import com.zylear.j2eelab.rabbitmq.bean.RabbitMessageHeader;
import com.zylear.j2eelab.rabbitmq.bean.RabbitMsgHeaderBase;

import java.util.UUID;

public class MqDefaultMsgConvert extends RabbitMQMsgConvert {
	private String typePrefix;

	public MqDefaultMsgConvert(String typePrefix) {
		this.typePrefix = typePrefix;
	}

	public String getTypePrefix() {
		return typePrefix;
	}

	@Override
	protected RabbitMessageHeader formMsgHeader(String version) {
		RabbitMsgHeaderBase header = new RabbitMsgHeaderBase();
		header.setCreate_time(System.currentTimeMillis());
		header.setMessage_id(UUID.randomUUID().toString());
		header.setType(typePrefix + version);
		return header;
	}
}
