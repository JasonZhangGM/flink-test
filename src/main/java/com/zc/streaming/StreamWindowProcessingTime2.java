package com.zc.streaming;

import com.zc.Pojo.MsgHeader;
import com.zc.Pojo.MsgHeaderSchema;
import com.zc.component.MyRMQSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

import java.text.SimpleDateFormat;

public class StreamWindowProcessingTime2 {

	public static void main(String[] args) throws Exception {

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		final RMQConnectionConfig rmqConf = new RMQConnectionConfig.Builder()
				.setHost("10.18.20.13")
				.setPort(5672)
				.setVirtualHost("airport.mudata")
				.setUserName("camel")
				.setPassword("camel123")
				.build();

		final DataStreamSource<MsgHeader> inputStream2 = env.addSource(new MyRMQSource<MsgHeader>(
				rmqConf,
				"two.bb.in",
				false,
				new MsgHeaderSchema()
		)).setParallelism(1);

		final DataStreamSource<MsgHeader> inputStream1 = env.addSource(new MyRMQSource<MsgHeader>(
				rmqConf,
				"two.cc.in",
				false,
				new MsgHeaderSchema()
		)).setParallelism(1);

		final DataStream<MsgHeader> input = inputStream1.union(inputStream2);
		input.print();

		input.map(new MapFunction<MsgHeader, Tuple2<String,Long>>() {
			@Override
			public Tuple2<String, Long> map(MsgHeader msgHeader) throws Exception {
				return new Tuple2<>(msgHeader.getMsgId(),msgHeader.getMsgEventTime().getTime());
			}
		}).keyBy(0)
				.timeWindow(Time.seconds(9),Time.seconds(3))
				.reduce(new ReduceFunction<Tuple2<String, Long>>() {
					@Override
					public Tuple2<String, Long> reduce(Tuple2<String, Long> t1, Tuple2<String, Long> t2) throws Exception {
						return new Tuple2<>(t1.f0,Math.abs(t1.f1 - t2.f1));
					}
				}).print();

//		input.map(new MapFunction<String, String>() {
//
//			@Override
//			public String map(String msgHeader) throws Exception {
//				return msgHeader;
//			}
//		})
//				.keyBy(0)
//				.timeWindow(Time.seconds(9), Time.seconds(3))
//				.reduce(new ReduceFunction<Tuple2<String, Long>>() {
//					@Override
//					public Tuple2<String, Long> reduce(Tuple2<String, Long> t1, Tuple2<String, Long> t2) throws Exception {
//						return new Tuple2<>(t1.f0,Math.abs(t2.f1-t1.f1));
//					}
//				})
//				.print()
//				.setParallelism(1);

		env.execute("StreamWindowProcessingTime2");

	}
}
