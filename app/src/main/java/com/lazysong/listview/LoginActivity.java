package com.lazysong.listview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lazysong.listview.db.DataManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUserId;
    private EditText etPasswd;
    private Button btnLogin;
    private String userId;
    private String userPasswd;
    private TextView toRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.hide();

        etUserId = (EditText) findViewById(R.id.ed_userid);
        etPasswd = (EditText) findViewById(R.id.ed_passwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        toRegister = (TextView) findViewById(R.id.to_register);

        btnLogin.setOnClickListener(this);
        toRegister.setOnClickListener(this);
    }
    private boolean identifyVertification(String userId, String userPasswd) {
        DataManager dataManager = new DataManager(LoginActivity.this);
        return dataManager.matchUser(userId, userPasswd);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.to_register:
                Intent intent = new Intent();
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                doLogin();
                break;
            default:
        }

    }

    private void doLogin() {
        userId = etUserId.getText().toString();
        userPasswd = etPasswd.getText().toString();
        boolean success = identifyVertification(userId, userPasswd);
        if (success) {
            //记住登陆状态
            SharedPreferences sp = getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("logined", true);
            editor.putString("userId", userId);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            etPasswd.setText("");
            Toast.makeText(LoginActivity.this, "似乎出了点问题，请重试", Toast.LENGTH_SHORT).show();
        }
    }
}
