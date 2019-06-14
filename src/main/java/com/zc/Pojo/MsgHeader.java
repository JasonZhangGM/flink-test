package com.zc.Pojo;

import java.util.Date;

public class MsgHeader {

	private String msgId;
	private Date msgEventTime;

	public MsgHeader() {
	}

	public MsgHeader(String msgId, Date msgEventTime) {
		this.msgId = msgId;
		this.msgEventTime = msgEventTime;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Date getMsgEventTime() {
		return msgEventTime;
	}

	public void setMsgEventTime(Date msgEventTime) {
		this.msgEventTime = msgEventTime;
	}

	@Override
	public String toString() {
		return "MsgHeader{" +
				"msgId='" + msgId + '\'' +
				", msgEventTime=" + msgEventTime +
				'}';
	}

	public static MsgHeader fromString(String s){
		String headers[] = s.split(",");
		MsgHeader msg = new MsgHeader();
		msg.msgId = headers[0];

		return msg;
	}
}
