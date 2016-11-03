package com.lazysong.listview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
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

    private ListView listViewHome;

    private LocationClient mLocationClient = null;
    private MyLocationListenner myListener = new MyLocationListenner();
    private String cityname;
    private DataManager dataManager;
    private Cursor cursor;

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
                Toast.makeText(getContext(), "btn_home is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tvLocation = (TextView) actionBar.getCustomView().findViewById(R.id.tv_location);
        initLocation();
        dataManager = new DataManager(getContext());
        initData();
        listViewHome = (ListView) getActivity().findViewById(R.id.listview_home);
        listViewHome.setAdapter(new MyCursorAdapter(getContext(), cursor, true));
    }

    private void initData() {
//        dataManager.loadDatabase();
        cursor = dataManager.getActivityCursor();
        /*while (cursor.moveToNext()) {
            Log.v("mysqlite", cursor.getString(cursor.getColumnIndex("SUBJECT")) + " " + cursor.getString(cursor.getColumnIndex("PRESENTER")) );
        }*/
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

    @Override
    public void onResume() {
//        Log.v("fragment", "onResume() is called in HomeFragment");
        super.onResume();
//        if(!mLocationClient.isStarted())
//            mLocationClient.start();
    }

    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation arg0) {
            // TODO Auto-generated method stub
//            String cityname = arg0.getProvince() + arg0.getCity() + arg0.getStreet();
            Log.v("fragment", "onReceiveLocation() is called");
            cityname = arg0.getCity();
            Toast.makeText(getContext(), cityname, Toast.LENGTH_SHORT).show();
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
