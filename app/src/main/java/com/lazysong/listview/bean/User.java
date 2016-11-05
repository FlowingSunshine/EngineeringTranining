package com.lazysong.listview.bean;

import android.graphics.Bitmap;
import android.widget.SectionIndexer;

import java.io.Serializable;

/**
 * Created by lazysong on 2016/11/2.
 */

/*
字段名	USER_ID	USER_NAME	SEX	PASSWORD
字段含义	用户ID	用户名	性别	登陆密码
类型	VARCHAR(40)	VARCHAR(100)	INTEGER	VARCHAR(40)
约束	PRIMARY KEY	NOT NULL	IN (1, 2)	NOT NULL
举例	“18363738888”	“233”	1（1表示男，2表示女）	“12345678”

字段名	USER_IMG	PHONE	EMAIL
字段含义	用户头像	手机号码	邮箱
类型	BLOB	VARCHAR(20)	VARCHAR(40)
约束	NULL	NULL	NULL
举例		“18363738888”	“233@qq.com”
* */
public class User implements Serializable{
    private String userID;
    private String userName;
    private int sex;
    private String password;
    private Bitmap userImg;
    private String phone;
    private String email;

    public User(String userID, String userName, int sex, String password, Bitmap userImg, String phone, String email) {
        this.userID = userID;
        this.userName = userName;
        this.sex = sex;
        this.password = password;
        this.userImg = userImg;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getUserImg() {
        return userImg;
    }

    public void setUserImg(Bitmap userImg) {
        this.userImg = userImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
