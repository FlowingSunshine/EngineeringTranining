package com.lazysong.listview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lazysong.listview.db.DataManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUserId;
    private EditText etUserPasswd;
    private EditText etUserName;
    private Button btnRegister;
    private TextView tvToLogin;

    private String userId;
    private String passwd;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etUserId = (EditText) findViewById(R.id.register_userid);
        etUserPasswd = (EditText) findViewById(R.id.register_passwd);
        etUserName = (EditText) findViewById(R.id.register_username);
        btnRegister = (Button) findViewById(R.id.btn_register);
        tvToLogin = (TextView) findViewById(R.id.to_login);

        btnRegister.setOnClickListener(this);
        tvToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_register:
                doRegister();
                break;
            case R.id.to_login:
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void doRegister() {
        DataManager manager = new DataManager(this);
        userId = etUserId.getText().toString();
        passwd = etUserPasswd.getText().toString();
        userName = etUserName.getText().toString();
        boolean userExisted = manager.userExists(userId);
        if(!localVertification())
            return;
        if(userExisted) {
            Toast.makeText(this, "该账号已存在，请直接登陆", Toast.LENGTH_SHORT).show();
        }
        else {
            boolean success = manager.addUser(userId, passwd, userName);
            if(success) {
                SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("logined", true);
                editor.putString("userId", userId);
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(this, "似乎出了点问题，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean localVertification() {
        if(userId == null || userId.length() == 0) {
            Toast.makeText(this, "用户ID不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(userName == null || userName.length() == 0) {
            Toast.makeText(this, "用户昵称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(passwd == null || passwd.length() == 0) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
//        return false;
    }
}
