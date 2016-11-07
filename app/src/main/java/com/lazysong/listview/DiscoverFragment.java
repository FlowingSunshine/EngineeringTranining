package com.lazysong.listview;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.lazysong.listview.adapter.InstituteCusroAdapter;
import com.lazysong.listview.adapter.TagCursorAdapter;
import com.lazysong.listview.bean.Activity;
import com.lazysong.listview.bean.Institute;
import com.lazysong.listview.bean.Tag;
import com.lazysong.listview.db.DataManager;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment implements View.OnClickListener{
    private ViewPager viewPager;
    private List<View> viewContainer = new ArrayList<View>();
    private String[] tableTitles = new String[]{"热门", "高校", "类型", "收藏"};
    private TabLayout mTabLayout;
    private ListView listviewPopular;
    private ListView listviewInstitute;
    private ListView listviewType;
    private ListView listviewMark;
    private View v0;
    private View v1;
    private View v2;
    private View v3;
    private DataManager manager;
    private String userId;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        setHasOptionsMenu(true);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_discover);

        manager = new DataManager(getContext());
        context = getContext();
        LayoutInflater layoutInflater = getLayoutInflater(null);
        v0 = layoutInflater.inflate(R.layout.layout_popular, null);
        v1 = layoutInflater.inflate(R.layout.layout_university, null);
        v2 = layoutInflater.inflate(R.layout.layout_type, null);
        v3 = layoutInflater.inflate(R.layout.layout_mark, null);
        if(viewContainer.isEmpty()) {
            viewContainer.add(0, v0);
            viewContainer.add(1, v1);
            viewContainer.add(2, v2);
            viewContainer.add(3, v3);
        }
//        initPopularTab(v0);
//        initInstituteTab(v1);
//        initTypeTab(v2);
//        initMark(v3);
        viewPager.setAdapter(pagerAdapter);
        return view;
    }

    private void initPopularTab(View v) {
        listviewPopular = (ListView) v.findViewById(R.id.listview_popular);
        Cursor cursor = manager.getPopularActivity();
        listviewPopular.setAdapter(new MyCursorAdapter(getContext(), cursor, true));
        listviewPopular.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity activity = (Activity) view.getTag(R.id.activity);
                Intent intent = new Intent();
                intent.setClass(getContext(), CheckActivityInfoActivity.class);
                intent.putExtra("activity", activity);
                startActivity(intent);
            }
        });
    }

    private void initInstituteTab(View v) {
        listviewInstitute = (ListView) v.findViewById(R.id.listview_university);
        Cursor cursor = manager.getAllInstitute();
        listviewInstitute.setAdapter(new InstituteCusroAdapter(getContext(), cursor, true));
        listviewInstitute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Institute institute = (Institute) view.getTag(R.id.institute);
                Intent intent = new Intent();
                intent.setClass(getContext(), CheckActivityByInstitute.class);
                intent.putExtra("institute", institute);
                startActivity(intent);
            }
        });
    }

    private void initTypeTab(View v) {
        listviewType = (ListView) v.findViewById(R.id.listview_type);
        Cursor cursor = manager.getAllTags();
        listviewType.setAdapter(new TagCursorAdapter(getContext(), cursor, true));
        listviewType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag tag = (Tag) view.getTag(R.id.tag);
                Intent intent = new Intent();
                intent.setClass(getContext(), CheckActivityByTag.class);
                intent.putExtra("tag", tag);
                startActivity(intent);
            }
        });
    }

    private void initMark(View v) {
        listviewMark = (ListView) v.findViewById(R.id.listview_mark);
        SharedPreferences sp = getActivity().getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        Cursor cursor = manager.getMarkedActivity(userId);
        MyCursorAdapter adapter = new MyCursorAdapter(getContext(), cursor, true);
        /*adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                cursor.requery();
                Toast.makeText(getContext(), "onChanged() is called", Toast.LENGTH_SHORT).show();
            }
        });*/
        listviewMark.setAdapter(adapter);
        listviewMark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity activity = (Activity) view.getTag(R.id.activity);
                Intent intent = new Intent();
                intent.setClass(getContext(), CheckActivityInfoActivity.class);
                intent.putExtra("activity", activity);
                startActivity(intent);
            }
        });
    }

    private void updateMark() {
        Cursor cursor = manager.getMarkedActivity(userId);
        MyCursorAdapter adapter = new MyCursorAdapter(getContext(), cursor, true);
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {
        //viewpager中的组件数量
        @Override
        public int getCount() {
            return viewContainer.size();
        }
        //滑动切换的时候销毁当前的组件
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            ((ViewPager) container).removeView(viewContainer.get(position));
        }
        //每次滑动的时候生成的组件
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(viewContainer.get(position));
            return viewContainer.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tableTitles[position];
        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //消除actionBar的显示/隐藏动画，并且隐藏actionBar
        final android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.hide();

        mTabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("0"), true);
        for(int i = 1; i < viewContainer.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        //将TabLayout和ViewPager关联起来
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(viewPager);
            }
        });
        //给Tabs设置适配器
        mTabLayout.setTabsFromPagerAdapter(pagerAdapter);
    }

    public static DiscoverFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.relayout_popular:
                viewPager.setCurrentItem(0);
                break;
            case R.id.relayout_university:
                viewPager.setCurrentItem(1);
                break;
            case R.id.relayout_type:
                viewPager.setCurrentItem(2);
                break;
            case R.id.relayout_mark:
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.closeDB();
    }

    @Override
    public void onResume() {
        super.onResume();
        initPopularTab(v0);
        initInstituteTab(v1);
        initTypeTab(v2);
        initMark(v3);
    }
}
