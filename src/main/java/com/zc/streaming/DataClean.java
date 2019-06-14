package com.zc.streaming;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataClean {

    public static void main(String args[]){
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text1 = env.fromElements("", "");

        text1.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return false;
            }
        });
    }

}
