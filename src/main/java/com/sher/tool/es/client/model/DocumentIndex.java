package com.sher.tool.es.client.model;

/**
 * Created by cloudsher on 2016/5/18.
 *
 *  �ĵ�������
 */
public class DocumentIndex {

    private String  index;

    private String type;

    private String id;

    private String source;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
