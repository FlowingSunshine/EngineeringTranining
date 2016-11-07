package com.lazysong.listview;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lazysong.listview.bean.Activity;
import com.lazysong.listview.db.DataManager;

import java.text.SimpleDateFormat;

public class CheckActivityInfoActivity extends AppCompatActivity {
    private Activity activity;
    private TextView subject;
    private TextView info;
    private ActionBar actionBar;
    private ImageView close;
    private boolean activityIsMarked = false;
    private  DataManager manager;
    private int activityId;
    private String userId;

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

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.detail);
        //自定义ActionBar
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.show();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_activity_info);
        close = (ImageView) actionBar.getCustomView().findViewById(R.id.close_activity_info);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        manager  = new DataManager(this);
    }

    private void updateMarkStatus(Menu menu) {
        activityId = activity.getActivityNo();
        SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        activityIsMarked = manager.activityIsMarked(userId, activityId);
        if(activityIsMarked) {
            menu.findItem(R.id.mark).setIcon(R.drawable.ic_marked);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_menu, menu);
        updateMarkStatus(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mark:
                doMark(item);
                break;
            case R.id.hide:
                doHide(item);
                break;
        }
        return true;
    }
 
    private void doHide(MenuItem item) {
        Toast.makeText(this, "隐藏成功", Toast.LENGTH_SHORT).show();
    }

    private void doMark(MenuItem item) {
        if(activityIsMarked) {//取消收藏
            manager.unmarkActivity(userId, activityId);
            item.setIcon(R.drawable.ic_unmarked);
            activityIsMarked = false;
        }
        else {//收藏
            manager.markActivity(userId, activityId);
            item.setIcon(R.drawable.ic_marked);
            activityIsMarked = true;
        }
//        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.closeDB();
    }
}
