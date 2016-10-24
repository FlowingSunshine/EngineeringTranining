package com.lazysong.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DiscoverFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        tv.setText(getArguments().getString("ARGS"));

        final android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_discover);
        actionBar.getCustomView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "btn_home is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static DiscoverFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

   /* @Override
   //创建OptionMenu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_discover, menu);
        return;
    }*/
}
