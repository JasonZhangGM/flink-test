package com.zc.streaming;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class TestProcessWindowFunction {
	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		DataStreamSource<String> text = env.socketTextStream("192.168.26.128", 9000, "\n");
		DataStream<Tuple2<Integer, Integer>> map = text.map(new MapFunction<String, Tuple2<Integer, Integer>>() {

			@Override
			public Tuple2<Integer, Integer> map(String value) throws Exception {
				return new Tuple2<>(1, Integer.parseInt(value));
			}
		});

		map.keyBy(0)
				.timeWindow(Time.seconds(5))
				.process(new ProcessWindowFunction<Tuple2<Integer, Integer>, Object, Tuple, TimeWindow>() {
					@Override
					public void process(Tuple tuple, Context context, Iterable<Tuple2<Integer, Integer>> iterable, Collector<Object> collector)
							throws Exception {
						System.out.println("into processWindowFunction");
						int count = 0;
						for(Tuple2 element : iterable){
							System.out.println(element);
							count++;
						}
						collector.collect(count);
					}
				})
				.print();


		env.execute("execute ProcessWindowFunction");


	}
}
