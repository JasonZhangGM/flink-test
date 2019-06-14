package com.zc.batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class FileWordCount {

	public static void main(String[] args) throws Exception {

		String path = "G:/file";
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		DataSet counts = env.readTextFile(path)
				.flatMap(new Tokenizer())
				.groupBy(0)
				.sum(1);
		counts.writeAsCsv("G:/output","\n"," ").setParallelism(1);
		env.execute("file outputs");
	}

//	static class Tokenizer implements FlatMapFunction{
//
//		@Override
//		public void flatMap(String s, Collector<Tuple2<String,Integer>> out) throws Exception {
//			String tokens[] = s.toLowerCase().split("\\s");
//			for(String token : tokens){
//				out.collect(new Tuple2<String, Integer>(token,1));
//			}
//
//		}
//	}

	static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {

		@Override
		public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
			String[] tokens = value.toLowerCase().split("\\W+");
			for (String token : tokens) {
				if (token.length() > 0) {
					out.collect(new Tuple2<String, Integer>(token, 1));
				}
			}
		}

	}

}
