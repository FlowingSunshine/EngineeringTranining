package com.lazysong.listview.bean;

import android.graphics.Bitmap;

/**
 * Created by lazysong on 2016/11/2.
 */
/*
字段名	ACTIVITY_NO	   SUBJECT	   PRESENTER	TIME	PRESENTER_INFO
类型	int	      String	      String	  string	string

字段名	   PLACE	HOLD_INSTITUTE	   MAIN_TAG	   MARK_COUNT
类型	string	      int	          int	      int
* */
public class Activity {
    private int activityNo;
    private String subject;
    private String presenter;
    private String time;
    private String presenterInfo;
    private String place;
    private int holdInstitute;
    private int mainTag;
    private int markCount;

    public Activity(int activityNo, String subject, String presenter, String time, String presenterInfo, String place, int holdInstitute, int mainTag, int markCount) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public int getHoldInstitute() {
        return holdInstitute;
    }

    public void setHoldInstitute(int holdInstitute) {
        this.holdInstitute = holdInstitute;
    }

    public int getMainTag() {
        return mainTag;
    }

    public void setMainTag(int mainTag) {
        this.mainTag = mainTag;
    }

    public int getMarkCount() {
        return markCount;
    }

    public void setMarkCount(int markCount) {
        this.markCount = markCount;
    }
}
