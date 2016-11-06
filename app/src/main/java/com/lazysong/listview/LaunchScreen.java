package com.lazysong.listview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lazysong.listview.bean.Activity;

public class LaunchScreen extends AppCompatActivity {
    private boolean logined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        //Toast.makeText(LaunchScreen.this, "sp contains logined", Toast.LENGTH_SHORT).show();

        logined = sp.getBoolean("logined", false);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                if(logined) {
                    Intent mainIntent = new Intent(LaunchScreen.this, MainActivity.class);
                    startActivity(mainIntent);
                    LaunchScreen.this.finish();
                }
                else {
                    Intent mainIntent = new Intent(LaunchScreen.this, LoginActivity.class);
                    startActivity(mainIntent);
                    LaunchScreen.this.finish();
                }
            }
        }, 3000);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.hide();

        //检查登陆状态
    }
}
