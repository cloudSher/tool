package com.sher.tool.pattern.filter;

import com.sher.tool.base.test.System.SystemUtil;

/**
 * Created by Administrator on 2016/10/13.
 */
public class CFilter implements Filter {


    @Override
    public void doFilter(String info, FilterChain chain) {
        SystemUtil.println("c filter do : " + info);
        chain.doFilter(info);
    }
}
