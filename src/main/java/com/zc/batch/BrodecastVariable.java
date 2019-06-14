package com.zc.batch;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.SingleInputUdfOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrodecastVariable {
	public static void main(String[] args) throws Exception {

		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		List<Tuple2<String,Integer>> list = new ArrayList<>();
		list.add(new Tuple2<>("zhangsan", 23));
		list.add(new Tuple2<>("lisi", 21));
		list.add(new Tuple2<>("wangwu", 34));
		final DataSource<Tuple2<String, Integer>> tupleData = env.fromCollection(list);
		DataSet<HashMap<String,Integer>> toBrodecate = tupleData.map((MapFunction<Tuple2<String, Integer>, HashMap<String, Integer>>) stringIntegerTuple2 -> {
			HashMap<String,Integer> map = new HashMap<>();
			map.put(stringIntegerTuple2.f0,stringIntegerTuple2.f1);
			return map;
		});


		final DataSource dataSource = env.fromElements("zhangsan","lisi","wangwu");
		final SingleInputUdfOperator udfOperator = dataSource.map(new RichMapFunction<String, String>() {

//			List<HashMap<String, Integer>> listHashmap = new ArrayList<>();
			HashMap<String, Integer> allMap = new HashMap<>();

			@Override
			public void open(Configuration parameters) throws Exception {
				super.open(parameters);
				final List<HashMap<String, Integer>> brodecast = getRuntimeContext().getBroadcastVariable("brodecastMap");
				for (HashMap<String,Integer> map : brodecast) {
					allMap.putAll(map);
				}
			}

			@Override
			public String map(String value) {
				return value + ":" + allMap.get(value);
			}
		}).withBroadcastSet(toBrodecate, "brodecastMap");

		udfOperator.print();
	}
}
