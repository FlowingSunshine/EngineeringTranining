package com.lazysong.listview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


public class SysNotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_notify);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setCustomView(R.layout.actionbar_notify);
//        actionBar.setTitle(R.string.app_name);
    }

}
