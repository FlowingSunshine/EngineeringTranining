package com.lazysong.listview.bean;

import android.graphics.Bitmap;

/**
 * Created by lazysong on 2016/11/2.
 */

/*
字段名	INSTITUTE_NO	INSTITUTE_NAME	INSTITUTE_TYPE	IMG	DESCRIPTION
类型	INTEGER	VARCHAR(100)	INT	BLOB	TEXT
* */
public class Institute {
    private int instituteNo;
    String instituteName;
    int instituteType;
    Bitmap blob;
    String description;

    public Institute(int instituteNo, String instituteName, int instituteType, Bitmap blob, String description) {
        this.instituteNo = instituteNo;
        this.instituteName = instituteName;
        this.instituteType = instituteType;
        this.blob = blob;
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

    public Bitmap getBlob() {
        return blob;
    }

    public void setBlob(Bitmap blob) {
        this.blob = blob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}