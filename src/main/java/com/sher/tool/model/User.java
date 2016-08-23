package com.sher.tool.model;

import com.sher.tool.base.test.annotation.Serialize;

/**
 * Created by Administrator on 2016/8/16.
 */
@Serialize(values = "user")
public class User {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
