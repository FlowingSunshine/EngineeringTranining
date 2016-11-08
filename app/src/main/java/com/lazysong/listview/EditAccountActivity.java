package com.lazysong.listview;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazysong.listview.bean.User;
import com.lazysong.listview.db.DataManager;

public class EditAccountActivity extends AppCompatActivity {

    private ImageView close;
    private TextView tvTitle;
    private ImageView userImg;
    private EditText userName;
    private EditText description;
    private EditText phone;
    private EditText email;
    private Button save;

    private User user;
    private String userId;
    private byte[] img;
    private DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        userId = getIntent().getStringExtra("userId");
        manager = new DataManager(this);

        initActionBar();
        loadUserInfo();
        save = (Button) findViewById(R.id.edit_userinfo_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
                finish();
            }
        });
    }

    private void saveUserInfo() {
        manager.updateUser(userId, userName.getText().toString(), null, phone.getText().toString(), email.getText().toString(), description.getText().toString());
    }

    private void loadUserInfo() {
        Cursor cursor = manager.getUserCursor(userId);
        cursor.moveToNext();
        user = new User();
        user.setUserID(userId);
        user.setUserName(cursor.getString(cursor.getColumnIndex("USER_NAME")));
        user.setPassword(cursor.getString(cursor.getColumnIndex("PASSWORD")));
        user.setSex(cursor.getInt(cursor.getColumnIndex("SEX")));
        user.setPhone(cursor.getString(cursor.getColumnIndex("PHONE")));
        user.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
        byte[] array = cursor.getBlob(cursor.getColumnIndex("USER_IMG"));
        user.setUserImg(BitmapFactory.decodeByteArray(array, 0, array.length, null));
        user.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        cursor.close();

        userImg = (ImageView) findViewById(R.id.edit_usrinfo_img);
        userName = (EditText) findViewById(R.id.edit_userinfo_username);
        description = (EditText) findViewById(R.id.edit_userinfo_description);
        phone = (EditText) findViewById(R.id.edit_userinfo_phone);
        email = (EditText) findViewById(R.id.edit_userinfo_email);

        userImg.setImageBitmap(user.getUserImg());
        userName.setText(user.getUserName());
        description.setText(user.getDescription());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_activity_info);
        close = (ImageView) actionBar.getCustomView().findViewById(R.id.close_activity_info);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle = (TextView) findViewById(R.id.title_actionbar);
        tvTitle.setText("编辑账户信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.closeDB();
    }
}
