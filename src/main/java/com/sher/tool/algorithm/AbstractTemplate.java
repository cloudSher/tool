package com.sher.tool.algorithm;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by sher on 11/6/16.
 */
public abstract class AbstractTemplate{

    protected long start;

    @Test
    public abstract void test();

    @Before
    public void before(){
        start = System.currentTimeMillis();
    }

    @After
    public void after(){
        System.out.println("time is :" + (System.currentTimeMillis() - start) + "ms");
    }




}
