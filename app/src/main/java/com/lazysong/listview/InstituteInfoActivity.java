package com.lazysong.listview;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InstituteInfoActivity extends AppCompatActivity {
    private TextView tvInstituteInfo;
    private String description;
    private View close;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_info);

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
        tvTitle.setText("机构信息");

        tvInstituteInfo = (TextView) findViewById(R.id.tv_instituteinfo);
        description = getIntent().getStringExtra("description");
        tvInstituteInfo.setText(description);
    }
}
