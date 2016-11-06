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
 * Created by lazysong on 2016/11/2.
 */
public class MyCursorAdapter extends CursorAdapter {
    private LayoutInflater layoutInflater;
    private Activity activity;

    public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return getCursor().getCount();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.listview_item_home, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = new ViewHolder();
        Tag tag = new Tag();
        Institute institute = new Institute();
        holder.subject = (TextView) view.findViewById(R.id.subject_home);
        holder.presenter = (TextView) view.findViewById(R.id.presenter_home);
        holder.hold_institute = (TextView) view.findViewById(R.id.hosted_institute_home);
        holder.subject.setText(cursor.getString(cursor.getColumnIndex("SUBJECT")) + "");
        holder.presenter.setText(cursor.getString(cursor.getColumnIndex("PRESENTER")));
        holder.hold_institute.setText(cursor.getString(cursor.getColumnIndex("HOLD_INSTITUTE")) + "");

        activity = new Activity();
        activity.setActivityNo(cursor.getInt(cursor.getColumnIndex("ACTIVITY_ID")));
        activity.setSubject(cursor.getString(cursor.getColumnIndex("SUBJECT")));
        activity.setPresenter(cursor.getString(cursor.getColumnIndex("PRESENTER")));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            activity.setTime(df.parse(cursor.getString(cursor.getColumnIndex("TIME"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        activity.setPresenterInfo(cursor.getString(cursor.getColumnIndex("PRESENTER_INFO")));
        activity.setPlace(cursor.getString(cursor.getColumnIndex("PLACE")));
        activity.setMarkCount(cursor.getInt(cursor.getColumnIndex("MARK_COUNT")));
        tag.setTagNo(cursor.getInt(cursor.getColumnIndex("TAG_NO")));
        tag.setTagName(cursor.getString(cursor.getColumnIndex("TAG_NAME")));
        byte[] image = cursor.getBlob(cursor.getColumnIndex("IMG"));
        institute.setInstituteNo(cursor.getInt(cursor.getColumnIndex("INSTITUTE_NO")));
        institute.setInstituteName(cursor.getString(cursor.getColumnIndex("INSTITUTE_NAME")));
        institute.setInstituteType(cursor.getInt(cursor.getColumnIndex("INSTITUTE_TYPE")));
//        institute.setImg(BitmapFactory.decodeByteArray(image, 0, image.length, null));
        institute.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        /*institute = new Institute(
                cursor.getInt(cursor.getColumnIndex("INSTITUTE_NO")),
                cursor.getString(cursor.getColumnIndex("INSTITUTE_NAME")),
                cursor.getInt(cursor.getColumnIndex("INSTITUTE_TYPE")),
                BitmapFactory.decodeByteArray(image, 0, image.length, null),
                cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
        );*/
        activity.setMainTag(tag);
        activity.setHoldInstitute(institute);
        /*更新list_item界面*/
        holder.tagImg = (ImageView) view.findViewById(R.id.main_tag_img);
        holder.tagName = (TextView) view.findViewById(R.id.main_tag_name);
        holder.subject = (TextView) view.findViewById(R.id.subject_home);
        holder.presenter = (TextView) view.findViewById(R.id.presenter_home);
        holder.time = (TextView) view.findViewById(R.id.time_home);
        holder.place = (TextView) view.findViewById(R.id.place_home);
        holder.hold_institute = (TextView) view.findViewById(R.id.hosted_institute_home);
        holder.markCount = (TextView) view.findViewById(R.id.markCount_home);

//        holder.tagImg.setBackground();
        holder.tagName.setText("来自分类：" + activity.getMainTag().getTagName());
        holder.subject.setText("主题：" + activity.getSubject());
        holder.presenter.setText("主讲人：" + activity.getPresenter());
        holder.time.setText("时间：" + df.format(activity.getTime()));
        holder.place.setText("地点：" + activity.getPlace());
        holder.hold_institute.setText("举办机构：" + activity.getHoldInstitute().getInstituteName());
        holder.markCount.setText(activity.getMarkCount() + "人想参加");
        view.setTag(R.id.activity, activity);
    }

    class ViewHolder {
        public ImageView tagImg;
        public TextView tagName;
        public TextView subject;
        public TextView presenter;
        public TextView time;
        public TextView place;
        public TextView hold_institute;
        public TextView markCount;
    }
}
