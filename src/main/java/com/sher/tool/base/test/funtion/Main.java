package com.sher.tool.base.test.funtion;

import com.sher.tool.model.User;

/**
 * Created by cloudsher on 8/28/2016.
 */
public class Main {

    public static void main(String arg[]){
        Funtion funtion = ()->{
            System.out.println("void args apply");
        };

        funtion.apply();
        funtion.apply();
    }


}
