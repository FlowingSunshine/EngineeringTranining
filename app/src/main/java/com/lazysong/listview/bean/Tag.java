package com.lazysong.listview.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

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
public class Tag implements Serializable{
    private int tagNo;
    private String tagName;
    private Bitmap img;

    public Tag() {

    }

    public Tag(int tagNo, String tagName, Bitmap img) {
        this.tagNo = tagNo;
        this.tagName = tagName;
        this.img = img;
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

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
