package com.lazysong.listview;

import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lazysong.listview.bean.User;
import com.lazysong.listview.db.DataManager;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogout;
    private User user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);

        loadUserInfo();
    }

    private void loadUserInfo() {
        SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        DataManager manager = new DataManager(this);
        Cursor cursor = manager.getUserCursor(userId);
        cursor.moveToNext();
        Toast.makeText(this, "loadUserInfo " + cursor.getString(cursor.getColumnIndex("USER_ID")), Toast.LENGTH_SHORT).show();
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_logout:
                SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("logined", false);
                editor.putString("userid", "");
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(UserInfoActivity.this, LaunchScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);  ;
                startActivity(intent);
                finish();
        }
    }
}
