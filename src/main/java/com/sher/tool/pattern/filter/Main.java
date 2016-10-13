package com.sher.tool.pattern.filter;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Main {


    public static void main(String args[]){
        DefaultFilterChain chain = new DefaultFilterChain();
        chain.addFilter(new AFilter());
        chain.addFilter(new BFilter());
        chain.addFilter(new CFilter());
        chain.doFilter("chain");
    }
}
