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
import com.lazysong.listview.bean.Institute;
import com.lazysong.listview.bean.Tag;

/**
 * Created by lazysong on 2016/11/6.
 */
public class TagCursorAdapter extends CursorAdapter {
    private LayoutInflater layoutInflater;
    private Tag tag;

    public TagCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return getCursor().getCount();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.list_item_tag, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = new ViewHolder();
        tag = new Tag();
        tag.setTagNo(cursor.getInt(cursor.getColumnIndex("TAG_NO")));
        tag.setTagName(cursor.getString(cursor.getColumnIndex("TAG_NAME")));
//        tag.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));

        /*更新list_item界面*/
        holder.img = (ImageView) view.findViewById(R.id.tag_img);
        holder.tagName = (TextView) view.findViewById(R.id.tag_name);

//        holder.img.setImageBitmap(tag.getImg());
        holder.tagName.setText(tag.getTagName());
        view.setTag(R.id.tag, tag);
    }

    class ViewHolder {
        public ImageView img;
        public TextView tagName;
    }
}


