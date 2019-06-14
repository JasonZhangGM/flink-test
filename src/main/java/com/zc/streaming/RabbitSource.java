package com.zc.streaming;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

public class RabbitSource {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
				.setHost("localhost")
				.setPort(5672)
				.setVirtualHost("/")
				.setUserName("guest")
				.setPassword("guest")
   				.build();

		final DataStream<String> stream = env
				.addSource(new RMQSource<String>(
						connectionConfig,            // config for the RabbitMQ connection
						"flink",                 // name of the RabbitMQ queue to consume
						false,                        // use correlation ids; can be false if only at-least-once is required
						new SimpleStringSchema()));		// deserialization schema to turn messages into Java objects
		stream.print()
				.setParallelism(1);              // non-parallel source is only required for exactly-once

		env.execute("rabbitmq execute");


	}

}
