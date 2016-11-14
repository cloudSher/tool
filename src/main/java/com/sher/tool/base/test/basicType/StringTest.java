package com.sher.tool.base.test.basicType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/7.
 */
public class StringTest {


    @Test
    public void test(){
        Assert.assertEquals(new String(new char[]{'1','4','7'}).intern() == "147",true);
    }
}
