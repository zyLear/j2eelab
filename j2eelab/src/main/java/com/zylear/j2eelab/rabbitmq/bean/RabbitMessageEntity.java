package com.zylear.j2eelab.rabbitmq.bean;

public class RabbitMessageEntity {

	private RabbitMessageHeader header;
	private RabbitMessageBody body;
	
	public RabbitMessageEntity() {	
	}
	
	public RabbitMessageEntity(RabbitMessageHeader header, RabbitMessageBody body) {
		this.header = header;
		this.body = body;
	}
	
	public RabbitMessageHeader getHeader() {
		return header;
	}
	public void setHeader(RabbitMessageHeader header) {
		this.header = header;
	}
	public RabbitMessageBody getBody() {
		return body;
	}
	public void setBody(RabbitMessageBody body) {
		this.body = body;
	}
	
}
