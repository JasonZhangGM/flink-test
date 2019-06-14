package com.zc.streaming;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

public class TestMyNoParaleSource {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		final DataStreamSource<Long> text = env.addSource(new MyNoParaleSource());
		final SingleOutputStreamOperator<Long> map = text.map(new MapFunction<Long, Long>() {

			@Override
			public Long map(Long aLong) throws Exception {
				System.out.println(aLong);
				return aLong;
			}
		});

		final SingleOutputStreamOperator<Long> sum = map.timeWindowAll(Time.seconds(2)).sum(0);
		sum.print().setParallelism(1);
		String simpleName = TestMyNoParaleSource.class.getSimpleName();
		env.execute(simpleName);

	}
}
