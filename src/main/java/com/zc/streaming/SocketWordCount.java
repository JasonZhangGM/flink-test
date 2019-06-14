package com.zc.streaming;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;


public class SocketWordCount {

	public static void main(String[] args) throws Exception {
		final int port = 9000;
		final String hostname = "192.168.26.128";
//		final ParameterTool params = ParameterTool.fromArgs(args);
//		port = params.getInt("port");

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		DataStream<String> text = env.socketTextStream(hostname, port,"\n");
		DataStream<WordCount> wordcounts = text.flatMap(new FlatMapFunction<String, WordCount>() {

			@Override
			public void flatMap(String value, Collector<WordCount> out) throws Exception {
				final String[] split = value.split("\\s");
				for (String s : split) {
//					System.out.println("string:" + s);
					out.collect(new WordCount(s, 1));
				}
			}
		}).keyBy("word")
				.timeWindow(Time.seconds(5), Time.seconds(1))
				.sum("count");

		wordcounts.print().setParallelism(1);

		env.execute("word count");
	}

	public static class WordCount{
		public String word;
		public int count;

		public WordCount() {
		}

		public WordCount(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return word + ":" +count;
		}
	}
}
