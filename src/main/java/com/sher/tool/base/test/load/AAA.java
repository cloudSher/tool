package com.sher.tool.base.test.load;

/**
 * Created by Administrator on 2016/11/29.
 */
public class AAA {

    private AAB aab;

    AAA(){
        if(aab == null)
            aab = new AAB();
    }


    public void clear(){
        aab = null;
    }


}
