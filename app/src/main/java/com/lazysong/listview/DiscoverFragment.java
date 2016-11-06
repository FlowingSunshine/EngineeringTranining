package com.lazysong.listview;

import android.app.ActionBar;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment implements View.OnClickListener{
    private ViewPager viewPager;
    private List<View> viewContainer = new ArrayList<View>();
    private String[] tableTitles = new String[]{"热门", "高校", "类型", "收藏"};
    private TabLayout mTabLayout;
    private ListView listviewPopular;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        setHasOptionsMenu(true);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_discover);
 
        LayoutInflater layoutInflater = getLayoutInflater(null);
        View v0 = layoutInflater.inflate(R.layout.layout_popular, null);
        initPopular(v0);
        View v1 = layoutInflater.inflate(R.layout.layout_type, null);
        View v2 = layoutInflater.inflate(R.layout.layout_university, null);
        View v3 = layoutInflater.inflate(R.layout.layout_mark, null);
        if(viewContainer.isEmpty()) {
            viewContainer.add(0, v0);
            viewContainer.add(1, v1);
            viewContainer.add(2, v2);
            viewContainer.add(3, v3);
        }
        viewPager.setAdapter(pagerAdapter);

        return view;
    }

    private void initPopular(View v) {
        listviewPopular = (ListView) v.findViewById(R.id.listview_popular);
        
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

   /* @Override
   //创建OptionMenu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_discover, menu);
        return;
    }*/
}
