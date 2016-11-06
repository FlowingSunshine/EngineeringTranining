package com.lazysong.listview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lazysong.listview.db.DataManager;

public class HomeFragment extends Fragment {
    private TextView tvLocation;
    private android.support.v7.app.ActionBar actionBar;
    private ImageView notifyButton;

    private ListView listViewHome;

    private LocationClient mLocationClient = null;
    private MyLocationListenner myListener = new MyLocationListenner();
    private String cityname;
    private DataManager dataManager;
    private Cursor cursor;
    private MyCursorAdapter cursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //自定义ActionBar
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.show();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_home);
        actionBar.getCustomView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "btn_home is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tvLocation = (TextView) actionBar.getCustomView().findViewById(R.id.tv_location);
        initLocation();
        dataManager = new DataManager(getContext());
        initData();
        listViewHome = (ListView) getActivity().findViewById(R.id.listview_home);
        cursorAdapter = new MyCursorAdapter(getContext(), cursor, true);
        listViewHome.setAdapter(cursorAdapter);
        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getContext(), CheckActivityInfoActivity.class);
                intent.putExtra("activity", (com.lazysong.listview.bean.Activity)view.getTag(R.id.activity));
                startActivity(intent);
            }
        });
        notifyButton = (ImageView) getActivity().findViewById(R.id.btn_notif);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), SysNotifyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        cursor = dataManager.getActivityCursor();
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getContext());
        mLocationClient.registerLocationListener(myListener);
        setLocationOption();
        mLocationClient.start();
        Log.v("fragment", "start() is called in initLocation()");
    }


    private void setLocationOption() {
        // TODO Auto-generated method stub
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); //打开gps
        option.setServiceName("com.baidu.location.service_v2.9");
//        option.setPoiExtraInfo(true);
        option.setAddrType("all");
        option.setPriority(LocationClientOption.NetWorkFirst);
        option.setPriority(LocationClientOption.GpsFirst);       //gps
//        option.set.setPoiNumber(10);
        option.disableCache(true);
//        option.setScanSpan(0);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation arg0) {
            // TODO Auto-generated method stub
            Log.v("fragment", "onReceiveLocation() is called");
            cityname = arg0.getCity();
            if (cityname != null)
                tvLocation.setText(cityname);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
