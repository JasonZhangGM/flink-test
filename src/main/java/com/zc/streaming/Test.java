package com.zc.streaming;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

public class Test {



    public static final String SYSTEMCODE = "MU";

    public static void main(String[] args) throws Exception {

        // 读取配置信息
//        ParameterTool params = ParameterTool.fromArgs(args);
//        String local = "flink-crew-assign/src/main/resources/config.properties";
//        String remote = "config/config.properties";
//        ParameterTool config = ParameterTool.fromPropertiesFile(params.get("config", local));
//        Class.forName(config.get("db.driver"));

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final RMQConnectionConfig rmqConf = new RMQConnectionConfig.Builder()
                .setHost("10.18.20.13")
                .setPort(Integer.valueOf("5672"))
                .setVirtualHost("flight")
                .setUserName("flt.flink.output")
                .setPassword("CREWASSIGNOUT")
                .build();
        DataStreamSource<String> reqStream = env.addSource(new RMQSource(rmqConf,
                "two.crew.mdflight",
                true,
                new SimpleStringSchema()));
        org.apache.flink.streaming.api.functions.source.SourceFunction e;
        reqStream.print();

        env.execute("test");
    }
}
