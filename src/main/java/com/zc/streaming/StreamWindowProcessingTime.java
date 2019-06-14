package com.zc.streaming;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StreamWindowProcessingTime {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		final RMQConnectionConfig rmqConf = new RMQConnectionConfig.Builder()
				.setHost("10.18.20.13")
				.setPort(5672)
				.setVirtualHost("flight")
				.setUserName("camel")
				.setPassword("camel123")
				.build();
		final DataStreamSource<String> inputStream = env.addSource(new RMQSource<String>(
				rmqConf,
				"two.flight.test",
				false,
				new SimpleStringSchema()
		)).setParallelism(1);

		inputStream.map(new MapFunction<String, Tuple2<String,Long>>() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			@Override
			public Tuple2 map(String s) throws Exception {
				final String[] msg = s.split(",");
				System.out.println(s);
				System.out.println("message is:" + s + "," + sdf.format(new Date(Long.parseLong(msg[1]))));
				return new Tuple2(msg[0],Long.parseLong(msg[1]));
			}
		}).keyBy(0)
				.timeWindow(Time.seconds(9),Time.seconds(3))
				.reduce(new ReduceFunction<Tuple2<String, Long>>() {
					@Override
					public Tuple2<String, Long> reduce(Tuple2<String, Long> t1, Tuple2<String, Long> t2) throws Exception {
						return new Tuple2<>(t1.f0,Math.abs(t2.f1-t1.f1));
					}
				})
				.print()
				.setParallelism(1);

		env.execute("StreamWindowProcessingTime");

	}
}
