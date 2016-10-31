package com.lazysong.listview.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lazysong on 2016/10/30.
 */
public class DBtest {
    Context context;
    public DBtest(Context context) {
        this.context = context;
    }
    public void testDB() {
        DatabaseHelper helper = new DatabaseHelper(context,"mydb", null, 1);
        SQLiteDatabase database = helper.getWritableDatabase();
        //    private final String CREATE_TABLE_TEST = "create table activity(no int primary key, time date, place varchar);";
//        database.execSQL("insert into activity values(1, '2016-2-1', 'ustc');");
    }
}
