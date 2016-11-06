package com.lazysong.listview;

import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lazysong.listview.bean.User;
import com.lazysong.listview.db.DataManager;

import java.util.Map;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btnLogout;
    private User user;
    private String userId;
    private ImageView userinfoImg;
    private TextView userinfoUserame;
    private TextView userinfoOther;
    private TextView userinfoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        userinfoImg = (ImageView) findViewById(R.id.userinfo_img);
        userinfoUserame = (TextView) findViewById(R.id.userinfo_username);
        userinfoOther = (TextView) findViewById(R.id.userinfo_other);
        btnLogout = (TextView) findViewById(R.id.btn_logout);
        userinfoDescription = (TextView) findViewById(R.id.userinfo_description);
        btnLogout.setOnClickListener(this);

        loadUserInfo();
        loadView();
    }

    private void loadUserInfo() {
        SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        DataManager manager = new DataManager(this);
        Cursor cursor = manager.getUserCursor(userId);
        cursor.moveToNext();
//        Toast.makeText(this, "loadUserInfo " + cursor.getString(cursor.getColumnIndex("USER_ID")), Toast.LENGTH_SHORT).show();
        user = new User();
        user.setUserID(cursor.getString(cursor.getColumnIndex("USER_ID")));
        user.setUserName(cursor.getString(cursor.getColumnIndex("USER_NAME")));
        user.setPassword(cursor.getString(cursor.getColumnIndex("PASSWORD")));
        user.setSex(cursor.getInt(cursor.getColumnIndex("SEX")));
        user.setPhone(cursor.getString(cursor.getColumnIndex("PHONE")));
        user.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
        byte[] array = cursor.getBlob(cursor.getColumnIndex("USER_IMG"));
        user.setUserImg(BitmapFactory.decodeByteArray(array, 0, array.length, null));
        user.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        //将字节数组转化为位图

        cursor.close();
    }

    private void loadView() {
//        userinfoImg.setBackground();
        userinfoUserame.setText(user.getUserName());
        userinfoDescription.setText(user.getDescription());
        userinfoImg.setImageBitmap(user.getUserImg());
        String infoOther = "";
        infoOther += "用户ID：" + user.getUserID();
        infoOther += "\n性别：" + (user.getSex() == 1 ?"男":"女");
        infoOther += "\n邮箱：" + user.getEmail();
        infoOther += "\n手机：" + user.getPhone();
        userinfoOther.setText(infoOther);
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
                break;
        }
    }
}
