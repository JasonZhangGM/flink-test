package com.zc.streaming;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;
import java.util.regex.Pattern;

public class KafkaConsumer {
	public static void main(String[] args) throws Exception {

		final LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
		Properties propertie = new Properties();
		propertie.setProperty("bootstrap.servers","localhost:9092");
//		propertie.setProperty("")

		FlinkKafkaConsumer011 cons = new FlinkKafkaConsumer011(
				//topic支持正则
				Pattern.compile("flight[0-9]"),
//				"flight",
				new SimpleStringSchema(),
				propertie
				);

		final DataStreamSource kafkaSource = env.addSource(cons);

		kafkaSource.print();
		env.execute("kafka consumer");

	}
}
