package com.lazysong.listview.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazysong.listview.R;
import com.lazysong.listview.bean.Activity;
import com.lazysong.listview.bean.Institute;
import com.lazysong.listview.bean.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by lazysong on 2016/11/6.
 */
public class InstituteCusroAdapter extends CursorAdapter {
    private LayoutInflater layoutInflater;
    private Institute institute;

    public InstituteCusroAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return getCursor().getCount();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.list_item_institute, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = new ViewHolder();
        Tag tag = new Tag();
        Institute institute = new Institute();
         /*
    *   字段名	INSTITUTE_NO	INSTITUTE_NAME	INSTITUTE_TYPE	IMG	DESCRIPTION
        字段含义	机构编号	机构名称	机构类别	图片	描述信息
        类型	INTEGER	VARCHAR(100)	INT	BLOB	TEXT
        约束	primary key	unique	NOT NULL	NULL	NULL
        举例	1	“中国科学技术大学”	1（表示高校）		“中国科学技术大学是中国科学院所属的…”
*/
        institute = new Institute();
        institute.setInstituteNo(cursor.getInt(cursor.getColumnIndex("INSTITUTE_NO")));
        institute.setInstituteName(cursor.getString(cursor.getColumnIndex("INSTITUTE_NAME")));
        institute.setInstituteType(cursor.getInt(cursor.getColumnIndex("INSTITUTE_TYPE")));
//        institute.setInstituteType(cursor.getInt(cursor.getColumnIndex("IMG")));
        institute.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));


        /*更新list_item界面*/
        holder.img = (ImageView) view.findViewById(R.id.institute_img);
        holder.instituteName = (TextView) view.findViewById(R.id.institute_name);

//        holder.img.setImageBitmap(institute.getImg());
        holder.instituteName.setText(institute.getInstituteName());
        view.setTag(R.id.institute, institute);
    }

    class ViewHolder {
        public ImageView img;
        public TextView instituteName;
        public TextView description;
    }
}

