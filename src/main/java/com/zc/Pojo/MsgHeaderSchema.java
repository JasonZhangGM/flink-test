package com.zc.Pojo;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MsgHeaderSchema implements DeserializationSchema<MsgHeader>, SerializationSchema<MsgHeader> {

	private ObjectMapper oMapper = new ObjectMapper();

	@Override
	public MsgHeader deserialize(byte[] bytes) throws IOException {
		return   oMapper.readValue(new String(bytes), MsgHeader.class);
	}

	@Override
	public boolean isEndOfStream(MsgHeader msgHeader) {
		return false;
	}

	@Override
	public byte[] serialize(MsgHeader msgHeader) {
		System.out.println("---------");
		return msgHeader.toString().getBytes();
	}

	@Override
	public TypeInformation<MsgHeader> getProducedType() {
		return TypeExtractor.getForClass(MsgHeader.class);
	}
}
