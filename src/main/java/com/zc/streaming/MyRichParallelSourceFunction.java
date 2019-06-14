package com.zc.streaming;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 * RichParallelSourceFunction 比ParallelSourceFunction多两个方法
 * Open
 * Close
 * 这两个方法只执行一次，一般用作建立连接和关闭连接
 */
public class MyRichParallelSourceFunction  extends RichParallelSourceFunction {
	@Override
	public void run(SourceContext sourceContext) throws Exception {

	}

	@Override
	public void cancel() {

	}

	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
	}

	@Override
	public void close() throws Exception {
		super.close();
	}
}
