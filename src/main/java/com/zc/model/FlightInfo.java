package com.zc.model;

public class FlightInfo {

    private String Flight_Id;
    private String Mm_Leg_Id;
    private String Flight_Date;
    private String Carrier;
    private String Flight_No;
    private String Departure_Airport;
    private String Arrival_Airport;
    private String Crew_Link_Line;
    private String Ac_Type;
    private String Flight_Flag;
    private String Op_Time;
    private String Weather_Standard;

    public String getFlight_Id() {
        return Flight_Id;
    }

    public void setFlight_Id(String flight_Id) {
        Flight_Id = flight_Id;
    }

    public String getMm_Leg_Id() {
        return Mm_Leg_Id;
    }

    public void setMm_Leg_Id(String mm_Leg_Id) {
        Mm_Leg_Id = mm_Leg_Id;
    }

    public String getFlight_Date() {
        return Flight_Date;
    }

    public void setFlight_Date(String flight_Date) {
        Flight_Date = flight_Date;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public String getFlight_No() {
        return Flight_No;
    }

    public void setFlight_No(String flight_No) {
        Flight_No = flight_No;
    }

    public String getDeparture_Airport() {
        return Departure_Airport;
    }

    public void setDeparture_Airport(String departure_Airport) {
        Departure_Airport = departure_Airport;
    }

    public String getArrival_Airport() {
        return Arrival_Airport;
    }

    public void setArrival_Airport(String arrival_Airport) {
        Arrival_Airport = arrival_Airport;
    }

    public String getCrew_Link_Line() {
        return Crew_Link_Line;
    }

    public void setCrew_Link_Line(String crew_Link_Line) {
        Crew_Link_Line = crew_Link_Line;
    }

    public String getAc_Type() {
        return Ac_Type;
    }

    public void setAc_Type(String ac_Type) {
        Ac_Type = ac_Type;
    }

    public String getFlight_Flag() {
        return Flight_Flag;
    }

    public void setFlight_Flag(String flight_Flag) {
        Flight_Flag = flight_Flag;
    }

    public String getOp_Time() {
        return Op_Time;
    }

    public void setOp_Time(String op_Time) {
        Op_Time = op_Time;
    }

    public String getWeather_Standard() {
        return Weather_Standard;
    }

    public void setWeather_Standard(String weather_Standard) {
        Weather_Standard = weather_Standard;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "Flight_Id='" + Flight_Id + '\'' +
                ", Mm_Leg_Id='" + Mm_Leg_Id + '\'' +
                ", Flight_Date='" + Flight_Date + '\'' +
                ", Carrier='" + Carrier + '\'' +
                ", Flight_No='" + Flight_No + '\'' +
                ", Departure_Airport='" + Departure_Airport + '\'' +
                ", Arrival_Airport='" + Arrival_Airport + '\'' +
                ", Crew_Link_Line='" + Crew_Link_Line + '\'' +
                ", Ac_Type='" + Ac_Type + '\'' +
                ", Flight_Flag='" + Flight_Flag + '\'' +
                ", Op_Time='" + Op_Time + '\'' +
                ", Weather_Standard='" + Weather_Standard + '\'' +
                '}';
    }
}
