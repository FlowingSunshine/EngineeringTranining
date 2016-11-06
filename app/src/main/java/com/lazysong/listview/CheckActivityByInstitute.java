package com.lazysong.listview;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lazysong.listview.bean.Institute;
import com.lazysong.listview.db.DataManager;

import java.util.List;

public class CheckActivityByInstitute extends AppCompatActivity {
    private ListView listviewActivityInstitute;
    private Institute institute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//listview_activity_institute
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_activity_by_institute);

        listviewActivityInstitute = (ListView) findViewById(R.id.listview_activity_institute);
        institute = (Institute) getIntent().getSerializableExtra("institute");
        updateListview();
    }

    private void updateListview() {
        DataManager manager = new DataManager(this);
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
    }
}
