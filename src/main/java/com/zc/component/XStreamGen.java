package com.zc.component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zc.model.CrewRoot;

public class XStreamGen {

    private final static XStream INSTANCE ;

    static{
        INSTANCE = new XStream(new DomDriver());
        INSTANCE.processAnnotations(CrewRoot.class);
        INSTANCE.autodetectAnnotations(true);
    }

    private XStreamGen(){

    }

    public static XStream getInstance(){
        return INSTANCE;
    }

   
}
