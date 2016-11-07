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

import com.lazysong.listview.bean.Institute;
import com.lazysong.listview.db.DataManager;

import java.util.List;

public class CheckActivityByInstitute extends AppCompatActivity {
    private ListView listviewActivityInstitute;
    private Institute institute;
    private DataManager manager;
    private TextView tvdescription;
    private String description;
    private ImageView close;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//listview_activity_institute
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_activity_by_institute);

        listviewActivityInstitute = (ListView) findViewById(R.id.listview_activity_institute);
        institute = (Institute) getIntent().getSerializableExtra("institute");
        tvdescription = (TextView) findViewById(R.id.institute_description);
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
        tvTitle.setText(institute.getInstituteName());
    }
    private void updateListview() {
        manager = new DataManager(this);
        Cursor cursor = manager.getActivityByInstitute(institute.getInstituteNo());
        listviewActivityInstitute.setAdapter(new MyCursorAdapter(this, cursor, true));
        listviewActivityInstitute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(CheckActivityByInstitute.this, CheckActivityInfoActivity.class);
                intent.putExtra("activity", (com.lazysong.listview.bean.Activity)view.getTag(R.id.activity));
                startActivity(intent);
            }
        });
        description = institute.getDescription();
        if(description.length() > 70)
            tvdescription.setText(institute.getDescription().substring(0, 71) + "......");
        else
            tvdescription.setText(institute.getDescription());
        tvdescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CheckActivityByInstitute.this, InstituteInfoActivity.class);
                intent.putExtra("description", description);
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
