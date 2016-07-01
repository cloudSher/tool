package com.sher.tool.base.test.corba.service;

import com.sher.tool.base.test.corba.helloapp.HelloPOA;
import org.omg.CORBA.ORB;

/**
 * Created by cloudsher on 2016/6/28.
 */
public class HelloImpl extends HelloPOA {

    private ORB orb;

    public void setORB(ORB orb){
        this.orb = orb;
    }

    @Override
    public String sayHello() {
        return "hello,danni, this is a good world!!";
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
}
