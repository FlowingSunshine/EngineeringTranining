package com.lazysong.listview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lazysong.listview.bean.User;
import com.lazysong.listview.db.DataManager;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout layoutUserInfo;
    private ImageView userImg;
    private TextView tvUserName;
    private TextView tvDescription;
    private User user;
    private String userId;
    private DataManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_logined, container, false);
        manager = new DataManager(getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initActionBar();
        userImg = (ImageView) getActivity().findViewById(R.id.usr_img);
        tvUserName = (TextView) getActivity().findViewById(R.id.tv_user_name);
        tvDescription = (TextView) getActivity().findViewById(R.id.tv_description);
        loadUserInfo();
        updateView();
        layoutUserInfo = (RelativeLayout) getActivity().findViewById(R.id.layout_usr_info);
        layoutUserInfo.setOnClickListener(this);
    }

    private void loadUserInfo() {
        SharedPreferences sp = getActivity().getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        userId = sp.getString("userId", "");
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
    }

    private void updateView() {
        userImg.setImageBitmap(user.getUserImg());
        tvUserName.setText(user.getUserName());
        tvDescription.setText(user.getDescription());
    }

    private void initActionBar() {
        final android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.show();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_account);
    }

    public static AccountFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layout_usr_info:
                Intent intent = new Intent();
                intent.setClass(getContext(),UserInfoActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.closeDB();
    }
}
