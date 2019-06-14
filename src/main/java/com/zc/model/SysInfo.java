package com.zc.model;

public class SysInfo {


    private String MessageSequenceID;
    private String ServiceType;
    private String MessageType;
    private String SendDateTime;
    private String CreateDateTime;

    public String getMessageSequenceID() {
        return MessageSequenceID;
    }

    public void setMessageSequenceID(String messageSequenceID) {
        MessageSequenceID = messageSequenceID;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public String getSendDateTime() {
        return SendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        SendDateTime = sendDateTime;
    }

    public String getCreateDateTime() {
        return CreateDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        CreateDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "SysInfo{" +
                "MessageSequenceID='" + MessageSequenceID + '\'' +
                ", ServiceType='" + ServiceType + '\'' +
                ", MessageType='" + MessageType + '\'' +
                ", SendDateTime='" + SendDateTime + '\'' +
                ", CreateDateTime='" + CreateDateTime + '\'' +
                '}';
    }
}
