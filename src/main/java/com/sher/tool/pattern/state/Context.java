package com.sher.tool.pattern.state;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Context {

    State state;
    private State currentState;
    private List<State> list = new ArrayList<>();


    public void start(){
        state = new OpenState();
        currentState = state;
        state.valid(this);
    }


    public static void main(String args[]){
        Context context = new Context();
        context.start();
    }



}
