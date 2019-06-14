package com.zc.streaming;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class CountWindowAverage {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.fromElements(Tuple2.of(1L,3L),Tuple2.of(1L,5L),Tuple2.of(1L,7L),Tuple2.of(1L,9L),Tuple2.of(1L,11L))
			.keyBy(0)
			.flatMap(new RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long,Long>>() {

				private transient ValueState<Tuple2<Long,Long>> sum;

				@Override
				public void open(Configuration parameters) throws Exception {
					super.open(parameters);
					ValueStateDescriptor descriptor = new ValueStateDescriptor(
							"avearge",
							TypeInformation.of(new TypeHint<Tuple2<Long,Long>>() {}),
							Tuple2.of(0L,0L)
							);
					sum = getRuntimeContext().getState(descriptor);
				}

				@Override
				public void flatMap(Tuple2<Long, Long> input, Collector<Tuple2<Long, Long>> output) throws Exception {
					//获取当前状态值
					Tuple2<Long,Long> currentSum = sum.value();

					//
					currentSum.f0 += 1;
					currentSum.f1 += input.f1;

					sum.update(currentSum);

					if( currentSum.f0 >= 2 ){
						output.collect( Tuple2.of(input.f0,currentSum.f1 / currentSum.f0));
						sum.clear();
					}

				}
			})
				.print();

		env.execute("CountWindowAverage");
	}


}
