package com.lazysong.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout layoutUserInfo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_logined, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initActionBar();
        layoutUserInfo = (RelativeLayout) getActivity().findViewById(R.id.layout_usr_info);
        layoutUserInfo.setOnClickListener(this);
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
}
