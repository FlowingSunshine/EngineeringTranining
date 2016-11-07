package com.lazysong.listview;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lazysong.listview.adapter.TagCursorAdapter;
import com.lazysong.listview.bean.Institute;
import com.lazysong.listview.bean.Tag;
import com.lazysong.listview.db.DataManager;

public class CheckActivityByTag extends AppCompatActivity {
    private ListView listviewActivityTag;
    private Tag tag;
    private DataManager manager;
    private ImageView close;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_activity_by_tag);


        listviewActivityTag = (ListView) findViewById(R.id.listview_activity_tag);
        tag = (Tag) getIntent().getSerializableExtra("tag");
        manager = new DataManager(this);
        updateListview();
        initActionBar();
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
        tvTitle.setText("分类  " + tag.getTagName());
    }

    private void updateListview() {
        Cursor cursor = manager.getActivityByTag(tag.getTagNo());
        listviewActivityTag.setAdapter(new MyCursorAdapter(this, cursor, true));
        listviewActivityTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(CheckActivityByTag.this, CheckActivityInfoActivity.class);
                intent.putExtra("activity", (com.lazysong.listview.bean.Activity)view.getTag(R.id.activity));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.closeDB();
    }
}
