package com.zc.streaming;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class MyNoParaleSource implements SourceFunction<Long> {

	private Long num = 0l;
	private boolean flag = true;

	@Override
	public void run(SourceContext<Long> sourceContext) throws Exception {
		while(flag){
			sourceContext.collect(++num);
			Thread.sleep(1000);
		}
	}

	@Override
	public void cancel() {
		flag = false;
	}
}
