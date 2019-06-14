package com.zc.test;

import com.zc.Pojo.MsgHeader;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Test {
	public static void main(String[] args) throws Exception {

		System.out.println("1234567890".substring(0,3));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(new Date(1538359897000l)));

		MsgHeader header = new MsgHeader("123",new Date());
		ObjectMapper MAPPER = new ObjectMapper();
		String json = MAPPER.writeValueAsString(header);

		//json转对象
		MsgHeader msg = MAPPER.readValue(json, MsgHeader.class);


		List<String> list = new ArrayList<String>();
		String s = list.get(0);

		System.out.println(msg);


        Collections.singletonList(new ArrayList<>());
	}
}
