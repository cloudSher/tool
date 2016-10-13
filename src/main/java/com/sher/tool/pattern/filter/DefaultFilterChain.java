package com.sher.tool.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 *
 *
 */
public class DefaultFilterChain implements FilterChain {

    private List<Filter> chain = new ArrayList<>();
    private Filter next;
    private int modCount;
    private int index;

    public DefaultFilterChain(){
        this.index = 0 ;
        this.modCount = 0;
    }

    @Override
    public void doFilter(String info) {
        if(chain == null || chain.size() == 0){
            throw new IllegalArgumentException("filter chain must not be null");
        }
        if(index < chain.size()){
            Filter filter = chain.get(index);
            if(filter != null){
                index++;
                filter.doFilter(info,this);

            }
        }

    }

    public void addFilter(Filter filter){
        chain.add(filter);
    }







}
