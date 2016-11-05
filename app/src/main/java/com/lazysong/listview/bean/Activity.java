package com.lazysong.listview.bean;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by lazysong on 2016/11/2.
 */
/*
字段名	ACTIVITY_NO	   SUBJECT	   PRESENTER	TIME	PRESENTER_INFO
类型	int	      String	      String	  string	string

字段名	   PLACE	HOLD_INSTITUTE	   MAIN_TAG	   MARK_COUNT
类型	string	      int	          int	      int
* */
public class Activity implements Serializable{
    private int activityNo;
    private String subject;
    private String presenter;
    private Date time;
    private String presenterInfo;
    private String place;
    private Institute holdInstitute;
    private Tag mainTag;
    private int markCount;

    public Activity(int activityNo, String subject, String presenter, Date time, String presenterInfo, String place, Institute holdInstitute, Tag mainTag, int markCount) {
        this.activityNo = activityNo;
        this.subject = subject;
        this.presenter = presenter;
        this.time = time;
        this.presenterInfo = presenterInfo;
        this.place = place;
        this.holdInstitute = holdInstitute;
        this.mainTag = mainTag;
        this.markCount = markCount;
    }
    public Activity() {

    }

    public int getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(int activityNo) {
        this.activityNo = activityNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPresenterInfo() {
        return presenterInfo;
    }

    public void setPresenterInfo(String presenterInfo) {
        this.presenterInfo = presenterInfo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Institute getHoldInstitute() {
        return holdInstitute;
    }

    public void setHoldInstitute(Institute holdInstitute) {
        this.holdInstitute = holdInstitute;
    }

    public Tag getMainTag() {
        return mainTag;
    }

    public void setMainTag(Tag mainTag) {
        this.mainTag = mainTag;
    }

    public int getMarkCount() {
        return markCount;
    }

    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }
}
