package com.sher.tool.base.test.bytecode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Table {

    @Value(value="111")
    private String field;
}
