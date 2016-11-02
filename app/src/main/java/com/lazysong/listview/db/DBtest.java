package com.lazysong.listview.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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
//        database.execSQL("insert into activity values(1, datetime('now'), \"中国科学技术大学\");");
        //插入用户数据
        /*database.execSQL("INSERT INTO USER VALUES(" +
                "'18322223333', 'NullPoint', " +
                "1, '123434', " +
                "tNULL, '18322223333', 'NullPoint@qq.com'" +
                ");");*/
        //插入机构表数据
        database.execSQL("INSERT INTO INSTITUTE VALUES(\n" +
                "\t1, '中国科学技术大学', \n" +
                "\t1, NULL, \n" +
                "\t'中国科学技术大学是中国科学院所属的一所以前沿科学和高新技术为主、兼有特色管理和人文学科的综合性全国重点大学。\n" +
                " 　  1958年9月创建于北京，首任校长由郭沫若兼任。她的创办被称为“我国教育史和科学史上的一项重大事件”。建校后，中国科学院实施“全院办校，所系结合”的办学方针，学校紧紧围绕国家急需的新兴科技领域设置系科专业，创造性地把理科与工科即前沿科学与高新技术相结合，注重基础课教学，高起点、宽口径培养新兴、边缘、交叉学科的尖端科技人才，汇集了严济慈、华罗庚、钱学森、赵忠尧、郭永怀、赵九章、贝时璋等一批国内最有声望的科学家，使学校得到迅速发展，建校第二年即被列为全国重点大学。'\n" +
                ");\n");

       /* Cursor cursor = database.rawQuery("select * from activity;", null);
        if(cursor == null)
            Log.v("mysqlite", "cursor == null");
        else {
            while(cursor.moveToNext()) {
                Toast.makeText(context, cursor.getString(2), Toast.LENGTH_SHORT).show();
            }
        }*/
    }
}
