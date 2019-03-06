package com.zylear.j2eelab.rabbitmq.bean;

public class RabbitMsgHeaderBase implements RabbitMessageHeader {
	
	private Long create_time;
	private String message_id;
	private String type;
	
	@Override
	public Long getCreate_time() {
		return create_time;
	}
	@Override
	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
	@Override
	public String getMessage_id() {
		return message_id;
	}
	@Override
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public void setType(String type) {
		this.type = type;
	}

}
