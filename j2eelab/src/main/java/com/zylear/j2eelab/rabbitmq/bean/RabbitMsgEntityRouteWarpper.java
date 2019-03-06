package com.zylear.j2eelab.rabbitmq.bean;


public class RabbitMsgEntityRouteWarpper {
	
	private static final String EMPTY_ROUTE_KEY = "";

	private String routeKey;
	private RabbitMessageBody msgBody;
	
	public RabbitMsgEntityRouteWarpper() {	
	}
	
	public RabbitMsgEntityRouteWarpper(RabbitMessageBody msgBody) {
		this(EMPTY_ROUTE_KEY, msgBody);
	}
	
	public RabbitMsgEntityRouteWarpper(String routeKey, RabbitMessageBody msgBody) {
		this.routeKey = routeKey;
		this.msgBody = msgBody;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public RabbitMessageBody getEntity() {
		return msgBody;
	}

	public void setMsgBody(RabbitMessageBody entity) {
		this.msgBody = entity;
	}

}
