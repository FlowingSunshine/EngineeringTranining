package com.lazysong.listview.bean;

/**
 * Created by lazysong on 2016/11/2.
 */
/*
字段名
TAG_NO
TAG_NAME
类型
INTEGER
VARCHAR(100)

* */
public class Tag {
    int tagNo;
    String tagName;

    public Tag(int tagNo, String tagName) {
        this.tagNo = tagNo;
        this.tagName = tagName;
    }

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
