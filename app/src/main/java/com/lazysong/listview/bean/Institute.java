package com.lazysong.listview.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by lazysong on 2016/11/2.
 */

/*
字段名	INSTITUTE_NO	INSTITUTE_NAME	INSTITUTE_TYPE	IMG	DESCRIPTION
类型	INTEGER	VARCHAR(100)	INT	BLOB	TEXT
* */
public class Institute implements Serializable{
    private int instituteNo;
    String instituteName;
    int instituteType;
    Bitmap img;
    String description;

    public Institute() {

    }

    public Institute(int instituteNo, String instituteName, int instituteType, Bitmap img, String description) {
        this.instituteNo = instituteNo;
        this.instituteName = instituteName;
        this.instituteType = instituteType;
        this.img = img;
        this.description = description;
    }

    public int getInstituteNo() {
        return instituteNo;
    }

    public void setInstituteNo(int instituteNo) {
        this.instituteNo = instituteNo;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public int getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(int instituteType) {
        this.instituteType = instituteType;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
