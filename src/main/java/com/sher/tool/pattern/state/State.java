package com.sher.tool.pattern.state;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface State {


    void valid(Context pm);
    void first(Context pm);
    void businessLine(Context pm);
    void district(Context pm);


}
