package com.sher.tool.pattern.filter;

/**
 * Created by Administrator on 2016/10/13.
 */
public class AFilter implements Filter {


    @Override
    public void doFilter(String info, FilterChain chain) {
        System.out.println("aFilter do :" +info);
        chain.doFilter(info);
    }


}
