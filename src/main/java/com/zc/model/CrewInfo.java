package com.zc.model;


import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class CrewInfo {

    @XStreamImplicit(itemFieldName="PolitInfo")
    private List<PolitInfo> politInfos;

    public List<PolitInfo> getPolitInfos() {
        return politInfos;
    }

    public void setPolitInfos(List<PolitInfo> politInfos) {
        this.politInfos = politInfos;
    }

    @Override
    public String toString() {
        return "CrewInfo{" +
                "politInfos=" + politInfos +
                '}';
    }
}
