package com.zylear.j2eelab.rabbitmq.mqpackage;


import com.zylear.j2eelab.rabbitmq.bean.AbstractRabbitMessageBody;
import com.zylear.j2eelab.rabbitmq.bean.RabbitMessageBody;
import com.zylear.j2eelab.rabbitmq.bean.RabbitMessageEntity;
import com.zylear.j2eelab.rabbitmq.bean.RabbitMessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public abstract class RabbitMQMsgConvert implements MessageConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQMsgConvert.class);
	protected static final String CHATSET = "UTF-8";
	
	@Override
	public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
		RabbitMessageBody body = (RabbitMessageBody)object;
		RabbitMessageHeader header = formMsgHeader(body.getVersion());
		RabbitMessageEntity entity = new RabbitMessageEntity(header, body);
		byte[] bytes = null;
		try {
			bytes ="df".getBytes();// JsonUtil.getJsonFromObject(entity).getBytes(CHATSET);
		} catch (Exception e) {
			throw new MessageConversionException("failed to convert to program msg content", e);
		}
		messageProperties.setMessageId(header.getMessage_id());
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding(CHATSET);
		messageProperties.setContentLength(bytes.length);
		messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		if(object instanceof AbstractRabbitMessageBody) {
			AbstractRabbitMessageBody abstractRabbitMessageBody = (AbstractRabbitMessageBody)object;
			Long expiration = abstractRabbitMessageBody.getExpirationSecond();
			if(expiration != null && expiration > 0) {
				expiration *= 1000;
				messageProperties.setExpiration(expiration.toString());
			}
		}

		Message message = new Message(bytes, messageProperties);
		
		if(LOGGER.isTraceEnabled()) {
//			LOGGER.trace("convert msg success! msg:" + JsonUtil.getJsonFromObject(entity));
		}
		
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws MessageConversionException {
		if(LOGGER.isTraceEnabled()) {
			LOGGER.trace("from msg sucess!");
		}
		throw new UnsupportedOperationException("not support from operation");
	}
	
	abstract protected RabbitMessageHeader formMsgHeader(String version);
}
