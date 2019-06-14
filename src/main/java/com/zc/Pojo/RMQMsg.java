package com.zc.Pojo;

import com.rabbitmq.client.BasicProperties;

public class RMQMsg {
	BasicProperties properties;

	public BasicProperties getProperties() {
		return properties;
	}

	public void setProperties(BasicProperties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "RMQMsg{" +
				"properties=" + properties +
				'}';
	}
}
