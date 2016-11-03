package com.lazysong.listview;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lazysong on 2016/11/2.
 */
public class MyCursorAdapter extends CursorAdapter {
    private LayoutInflater layoutInflater;

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
        holder.subject = (TextView) view.findViewById(R.id.subject_home);
        holder.presenter = (TextView) view.findViewById(R.id.presenter_home);
        holder.hold_institute = (TextView) view.findViewById(R.id.hosted_institute_home);
        holder.subject.setText(cursor.getString(cursor.getColumnIndex("SUBJECT")) + "");
        holder.presenter.setText(cursor.getString(cursor.getColumnIndex("PRESENTER")));
        holder.hold_institute.setText(cursor.getString(cursor.getColumnIndex("HOLD_INSTITUTE")) + "");
    }

    class ViewHolder {
        public TextView subject;
        public TextView presenter;
        public TextView hold_institute;
    }
}
