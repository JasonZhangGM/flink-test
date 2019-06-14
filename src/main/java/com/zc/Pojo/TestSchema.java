package com.zc.Pojo;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

import java.io.IOException;

public class TestSchema extends AbstractDeserializationSchema<MsgHeader> {

	@Override
	public MsgHeader deserialize(byte[] bytes) throws IOException {

		return null;
	}
}
