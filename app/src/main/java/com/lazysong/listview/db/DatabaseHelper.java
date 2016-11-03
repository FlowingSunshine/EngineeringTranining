package com.lazysong.listview.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lazysong on 2016/10/30.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
//    private final String CREATE_TABLE_TEST = "CREATE TABLE TEST(_ID INTEGER AUTO INCREMENT, NO INT PRIMARY KEY, TIME DATE, PLACE VARCHAR);";
    private final String CREATE_TABLE_TEST = "CREATE TABLE TEST(NO INT PRIMARY KEY, TIME DATE, PLACE VARCHAR);";
    private final String CREATE_TABLE_USER = "CREATE TABLE USER(" +
            "USER_ID VARCHAR(40) PRIMARY KEY, USER_NAME VARCHAR(100) NOT NULL, " +
            "SEX INTEGER CHECK (SEX IN (1,2)), PASSWORD VARCHAR(40) NOT NULL, " +
            "USER_IMG BLOB NULL, PHONE VARCHAR(20) NULL, EMAIL VARCHAR(40) NULL" +
            ");";
    private final String CREATE_TABLE_INSTITUTE = "CREATE TABLE INSTITUTE(" +
            "INSTITUTE_NO INTEGER PRIMARY KEY, INSTITUTE_NAME VARCHAR(100) UNIQUE, " +
            "INSTITUTE_TYPE INT NOT NULL, IMG BLOB NULL, " +
            "DESCRIPTION TEXT NULL" +
            ");";
    private final String CREATE_TABLE_ACTIVITY = "CREATE TABLE ACTIVITY(" +
            "ACTIVITY_ID INTEGER PRIMARY KEY, SUBJECT VARCHAR(100) NOT NULL, " +
            "PRESENTER VARCHAR(100) NOT NULL, PRESENTER_INFO TEXT NULL, " +
            "TIME DATETIME NOT NULL, PLACE VARCHAR(100) NOT NULL, " +
            "HOLD_INSTITUTE INTEGER, MAIN_TAG INTEGER," +
            "MARK_COUNT INTEGER, " +
            "FOREIGN KEY (HOLD_INSTITUTE) REFERENCES INSTITUTE(INSTITUTE_NO), " +
            "FOREIGN KEY (MAIN_TAG) REFERENCES TAG(TAG_NO)" +
            ");";
    private final String CREATE_TABLE_TAG = "CREATE TABLE TAG(" +
            "TAG_NO INTEGER PRIMARY KEY, TAG_NAME VARCHAR(100) NOT NULL, " +
            "TAG_IMG BLOB NULL" +
            ");";
    private final String CREATE_TABLE_ACTIVITY_TAG = "CREATE TABLE ACTIVITY_TAG(\n" +
            "ACTIVITY_NO INTEGER, TAG_NO INTEGER, " +
            "PRIMARY KEY (ACTIVITY_NO, TAG_NO), " +
            "FOREIGN KEY (ACTIVITY_NO) REFERENCES ACTIVITY(ACTIVITY_NO), " +
            "FOREIGN KEY (TAG_NO) REFERENCES TAG(TAG_NO)" +
            ");";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
//            db.execSQL(CREATE_TABLE_TEST);
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_INSTITUTE);
            db.execSQL(CREATE_TABLE_ACTIVITY);
            db.execSQL(CREATE_TABLE_TAG);
            db.execSQL(CREATE_TABLE_ACTIVITY_TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
