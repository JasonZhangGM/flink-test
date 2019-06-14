package com.zc.streaming;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StreamWindowWatermark {

	public static void main(String[] args) throws Exception {

		final int port = 9000;
		final String hostname = "192.168.26.128";
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		final DataStreamSource<String> text = env.socketTextStream(hostname, port, "\n");

		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.setParallelism(1);

		DataStream<Tuple2<String, Long>> map = text.map(new MapFunction<String, Tuple2<String, Long>>() {
			@Override
			public Tuple2<String, Long> map(String s) throws Exception {
				final String[] arr = s.split(",");
				return new Tuple2<>(arr[0], Long.parseLong(arr[1]));
			}
		});

		final DataStream<Tuple2<String, Long>> watermarkStream = map.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Tuple2<String, Long>>() {
			long currentMaxTimestamp = 0l;
			final long maxOutOfOrderness = 10000l;

			@Nullable
			@Override
			public Watermark getCurrentWatermark() {
				return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
			}

			@Override
			public long extractTimestamp(Tuple2<String, Long> element, long l) {
				long timestamp = element.f1;
				currentMaxTimestamp = Math.max(currentMaxTimestamp, timestamp);
				System.out.println("key:" + element.f0 + ",eventtime:[" + element.f1 + "|" + sdf.format(element.f1) + "], currentMaxTimestamp:[" + currentMaxTimestamp + "|" +
						sdf.format(currentMaxTimestamp) + "],watermark:[" + getCurrentWatermark().getTimestamp() + "|" + sdf.format(getCurrentWatermark().getTimestamp()) + "]");
				return timestamp;
			}
		});


		 DataStream<Object> ds = watermarkStream.keyBy(0)
				.timeWindow(Time.seconds(3))
				.apply(new WindowFunction<Tuple2<String, Long>, Object, Tuple, TimeWindow>() {
					@Override
					public void apply(Tuple tuple, TimeWindow timeWindow, Iterable<Tuple2<String, Long>> input, Collector<Object> out) throws Exception {
						String key = tuple.toString();
						List<Long> arrayList = new ArrayList<Long>();
						final Iterator<Tuple2<String, Long>> iterator = input.iterator();
						while (iterator.hasNext()) {
							final Tuple2<String, Long> next = iterator.next();
							arrayList.add(next.f1);
						}
						Collections.sort(arrayList);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						String result = key + "," + arrayList.size() + "," +
								sdf.format(arrayList.get(0)) + "," + sdf.format(arrayList.get(arrayList.size() - 1))
								+ "," + sdf.format(timeWindow.getStart()) + "," +
								sdf.format(timeWindow.getEnd());
						out.collect(result);

					}
				});
			ds.print();
			env.execute("StreamWIndowWatermark");
	}

}
