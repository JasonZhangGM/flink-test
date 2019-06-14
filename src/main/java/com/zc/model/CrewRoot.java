package com.zc.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("CrewRoot")
public class CrewRoot {

    @XStreamAlias("FlightInfo")
    private FlightInfo flightInfo;
    @XStreamAlias("CrewInfo")
    private CrewInfo crewInfo;
    @XStreamAlias("SysInfo")
    private SysInfo sysInfo;

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }

    public CrewInfo getCrewInfo() {
        return crewInfo;
    }

    public void setCrewInfo(CrewInfo crewInfo) {
        this.crewInfo = crewInfo;
    }

    public SysInfo getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(SysInfo sysInfo) {
        this.sysInfo = sysInfo;
    }

    @Override
    public String toString() {
        return "CrewRoot{" +
                "flightInfo=" + flightInfo +
                ", crewInfo=" + crewInfo +
                ", sysInfo=" + sysInfo +
                '}';
    }
}
