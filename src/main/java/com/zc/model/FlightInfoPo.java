package com.zc.model;

import java.util.Date;

public class FlightInfoPo {

    private String flightNo;
    private Date stdUtc;
    private Date staUtc;
    private String planOrigAirportCd;
    private String planDestAirportCd;
    private String internationalFlag;
    private String aircraftTailNo;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getStdUtc() {
        return stdUtc;
    }

    public void setStdUtc(Date stdUtc) {
        this.stdUtc = stdUtc;
    }

    public Date getStaUtc() {
        return staUtc;
    }

    public void setStaUtc(Date staUtc) {
        this.staUtc = staUtc;
    }

    public String getPlanOrigAirportCd() {
        return planOrigAirportCd;
    }

    public void setPlanOrigAirportCd(String planOrigAirportCd) {
        this.planOrigAirportCd = planOrigAirportCd;
    }

    public String getPlanDestAirportCd() {
        return planDestAirportCd;
    }

    public void setPlanDestAirportCd(String planDestAirportCd) {
        this.planDestAirportCd = planDestAirportCd;
    }

    public String getInternationalFlag() {
        return internationalFlag;
    }

    public void setInternationalFlag(String internationalFlag) {
        this.internationalFlag = internationalFlag;
    }

    public String getAircraftTailNo() {
        return aircraftTailNo;
    }

    public void setAircraftTailNo(String aircraftTailNo) {
        this.aircraftTailNo = aircraftTailNo;
    }

    @Override
    public String toString() {
        return "FlightInfoPo{" +
                "flightNo='" + flightNo + '\'' +
                ", stdUtc=" + stdUtc +
                ", staUtc=" + staUtc +
                ", planOrigAirportCd='" + planOrigAirportCd + '\'' +
                ", planDestAirportCd='" + planDestAirportCd + '\'' +
                ", internationalFlag='" + internationalFlag + '\'' +
                ", aircraftTailNo='" + aircraftTailNo + '\'' +
                '}';
    }
}
