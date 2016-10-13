package com.sher.tool.pattern.filter;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface Filter {

    /**
     * info 承载信息体，
     * @param info
     * @param chain
     * @return
     */
    void doFilter(String info,FilterChain chain);
}
