package com.zc.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class FlightCrewMsg {

   private String SYSTEMCODE;
   private String SEQUENCE;
   private Date SENDTIME;
   private Date STD_UTC;
   private String STAFF_CODE;
   private String PLAN_DEST_AIRPORT_CD;
   private String FLIGHT_DATE;
   private String FLIGHT_NO;
   private Date STA_UTC;
   private String INTERNATIONAL_FLAG;
   private String ORG_CODE;
   private String EMPLOYEE_NUMBER;
   private String PLAN_ORIG_AIRPORT_CD;
   private String AIRCRAFT_REGISTER_ID;
   private Date LAST_MODIFIED_TIME;

   @JSONField(name="SYSTEMCODE")
    public String getSYSTEMCODE() {
        return SYSTEMCODE;
    }

    public void setSYSTEMCODE(String SYSTEMCODE) {
        this.SYSTEMCODE = SYSTEMCODE;
    }

    @JSONField(name="SEQUENCE")
    public String getSEQUENCE() {
        return SEQUENCE;
    }

    public void setSEQUENCE(String SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }

    @JSONField(name="SENDTIME",format = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date getSENDTIME() {
        return SENDTIME;
    }

    public void setSENDTIME(Date SENDTIME) {
        this.SENDTIME = SENDTIME;
    }

    @JSONField(name="STD_UTC",format = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date getSTD_UTC() {
        return STD_UTC;
    }

    public void setSTD_UTC(Date STD_UTC) {
        this.STD_UTC = STD_UTC;
    }

    @JSONField(name="STAFF_CODE")
    public String getSTAFF_CODE() {
        return STAFF_CODE;
    }

    public void setSTAFF_CODE(String STAFF_CODE) {
        this.STAFF_CODE = STAFF_CODE;
    }

    @JSONField(name="PLAN_DEST_AIRPORT_CD")
    public String getPLAN_DEST_AIRPORT_CD() {
        return PLAN_DEST_AIRPORT_CD;
    }

    public void setPLAN_DEST_AIRPORT_CD(String PLAN_DEST_AIRPORT_CD) {
        this.PLAN_DEST_AIRPORT_CD = PLAN_DEST_AIRPORT_CD;
    }

    @JSONField(name="FLIGHT_DATE")
    public String getFLIGHT_DATE() {
        return FLIGHT_DATE;
    }

    public void setFLIGHT_DATE(String FLIGHT_DATE) {
        this.FLIGHT_DATE = FLIGHT_DATE;
    }

    @JSONField(name="FLIGHT_NO")
    public String getFLIGHT_NO() {
        return FLIGHT_NO;
    }

    public void setFLIGHT_NO(String FLIGHT_NO) {
        this.FLIGHT_NO = FLIGHT_NO;
    }

    @JSONField(name="STA_UTC",format = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date getSTA_UTC() {
        return STA_UTC;
    }

    public void setSTA_UTC(Date STA_UTC) {
        this.STA_UTC = STA_UTC;
    }

    @JSONField(name="INTERNATIONAL_FLAG")
    public String getINTERNATIONAL_FLAG() {
        return INTERNATIONAL_FLAG;
    }

    public void setINTERNATIONAL_FLAG(String INTERNATIONAL_FLAG) {
        this.INTERNATIONAL_FLAG = INTERNATIONAL_FLAG;
    }

    @JSONField(name="ORG_CODE")
    public String getORG_CODE() {
        return ORG_CODE;
    }

    public void setORG_CODE(String ORG_CODE) {
        this.ORG_CODE = ORG_CODE;
    }

    @JSONField(name="EMPLOYEE_NUMBER")
    public String getEMPLOYEE_NUMBER() {
        return EMPLOYEE_NUMBER;
    }

    public void setEMPLOYEE_NUMBER(String EMPLOYEE_NUMBER) {
        this.EMPLOYEE_NUMBER = EMPLOYEE_NUMBER;
    }

    @JSONField(name="PLAN_ORIG_AIRPORT_CD")
    public String getPLAN_ORIG_AIRPORT_CD() {
        return PLAN_ORIG_AIRPORT_CD;
    }

    public void setPLAN_ORIG_AIRPORT_CD(String PLAN_ORIG_AIRPORT_CD) {
        this.PLAN_ORIG_AIRPORT_CD = PLAN_ORIG_AIRPORT_CD;
    }

    @JSONField(name="AIRCRAFT_REGISTER_ID")
    public String getAIRCRAFT_REGISTER_ID() {
        return AIRCRAFT_REGISTER_ID;
    }

    public void setAIRCRAFT_REGISTER_ID(String AIRCRAFT_REGISTER_ID) {
        this.AIRCRAFT_REGISTER_ID = AIRCRAFT_REGISTER_ID;
    }

    @JSONField(name="LAST_MODIFIED_TIME",format = "yyyy-MM-dd HH:mm:ss")
    public Date getLAST_MODIFIED_TIME() {
        return LAST_MODIFIED_TIME;
    }

    public void setLAST_MODIFIED_TIME(Date LAST_MODIFIED_TIME) {
        this.LAST_MODIFIED_TIME = LAST_MODIFIED_TIME;
    }

    @Override
    public String toString() {
        return "FlightCrewMsg{" +
                "SYSTEMCODE='" + SYSTEMCODE + '\'' +
                ", SEQUENCE='" + SEQUENCE + '\'' +
                ", SENDTIME=" + SENDTIME +
                ", STD_UTC=" + STD_UTC +
                ", STAFF_CODE='" + STAFF_CODE + '\'' +
                ", PLAN_DEST_AIRPORT_CD='" + PLAN_DEST_AIRPORT_CD + '\'' +
                ", FLIGHT_DATE='" + FLIGHT_DATE + '\'' +
                ", FLIGHT_NO='" + FLIGHT_NO + '\'' +
                ", STA_UTC=" + STA_UTC +
                ", INTERNATIONAL_FLAG='" + INTERNATIONAL_FLAG + '\'' +
                ", ORG_CODE='" + ORG_CODE + '\'' +
                ", EMPLOYEE_NUMBER='" + EMPLOYEE_NUMBER + '\'' +
                ", PLAN_ORIG_AIRPORT_CD='" + PLAN_ORIG_AIRPORT_CD + '\'' +
                ", AIRCRAFT_REGISTER_ID='" + AIRCRAFT_REGISTER_ID + '\'' +
                ", LAST_MODIFIED_TIME=" + LAST_MODIFIED_TIME +
                '}';
    }
}
