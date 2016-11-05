package com.lazysong.listview;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.lazysong.listview.bean.Activity;

import java.text.SimpleDateFormat;

public class CheckActivityInfoActivity extends AppCompatActivity {
    private Activity activity;
    private TextView subject;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_activity_info);

        subject = (TextView) findViewById(R.id.subject_detail);
        info = (TextView) findViewById(R.id.info_detail);

        activity = (Activity) getIntent().getSerializableExtra("activity");
        subject.setText(activity.getSubject());
        String infoText = "";
        if(activity.getPresenter() != null)
            infoText += "主讲人：" + activity.getPresenter();
        if(activity.getPresenterInfo() != null)
            infoText += "\n主讲人介绍：" + activity.getPresenterInfo();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(activity.getTime() != null)
            infoText += "\n时间：" + df.format(activity.getTime());
        if(activity.getPlace() != null)
            infoText += "\n地点：" + activity.getPlace();
        if(activity.getHoldInstitute().getInstituteName() != null)
            infoText += "\n举办单位：" + activity.getHoldInstitute().getInstituteName();
        //暂时只显示主标签
        if(activity.getMainTag() != null)
            infoText += "\n标签：" + activity.getMainTag().getTagName();
        info.setText(infoText);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }
}
