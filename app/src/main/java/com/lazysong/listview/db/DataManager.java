package com.lazysong.listview.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Script;

/**
 * Created by lazysong on 2016/11/1.
 */
public class DataManager {
    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public DataManager(Context context) {
        this.context = context;
        helper = new DatabaseHelper(context,"mydb", null, 1);
        database = helper.getWritableDatabase();
    }

    public void loadDatabase() {
        database.execSQL("INSERT INTO INSTITUTE VALUES(\n" +
                "\t1, '中国科学技术大学', \n" +
                "\t1, NULL, \n" +
                "\t'中国科学技术大学是中国科学院所属的一所以前沿科学和高新技术为主、兼有特色管理和人文学科的综合性全国重点大学。\n" +
                " 　  1958年9月创建于北京，首任校长由郭沫若兼任。她的创办被称为“我国教育史和科学史上的一项重大事件”。" +
                "建校后，中国科学院实施“全院办校，所系结合”的办学方针，学校紧紧围绕国家急需的新兴科技领域设置系科专业，" +
                "创造性地把理科与工科即前沿科学与高新技术相结合，注重基础课教学，高起点、宽口径培养新兴、边缘、交叉学科的尖端科技人才，" +
                "汇集了严济慈、华罗庚、钱学森、赵忠尧、郭永怀、赵九章、贝时璋等一批国内最有声望的科学家，使学校得到迅速发展，" +
                "建校第二年即被列为全国重点大学。'\n" +
                ");\n");
        database.execSQL("INSERT INTO INSTITUTE VALUES(\n" +
                "\t2, '中国人民大学', \n" +
                "\t1, NULL, \n" +
                "\t'中国人民大学（Renmin University of China）是一所以人文社会科学为主的综合性研究型全国重点大学，" +
                "直属于教育部，由教育部与北京市共建。学校的前身是1937年诞生于抗日战争烽火中的陕北公学，以及后来的华北联合大学和华北大学。" +
                "1949年12月16日，中央人民政府政务院第十一次政务会议根据中共中央政治局的建议，通过了《关于成立中国人民大学的决定》。" +
                "1950年10月3日，以华北大学为基础合并组建的中国人民大学隆重举行开学典礼，成为新中国创办的第一所新型正规大学。'\n" +
                ");\n");
        database.execSQL("INSERT INTO INSTITUTE VALUES(\n" +
                "\t3, '南京大学', \n" +
                "\t1, NULL, \n" +
                "\t'南京大学坐落于钟灵毓秀、虎踞龙蟠的金陵古都，是一所历史悠久、声誉卓著的百年名校。" +
                "其前身是创建于1902年的三江师范学堂，此后历经两江师范学堂、南京高等师范学校、国立东南大学、第四中山大学、" +
                "国立中央大学、国立南京大学等历史时期，于1950年更名为南京大学。1952年，在全国高校院系调整中，" +
                "南京大学调整出工学、农学、师范等部分院系后与创办于1888年的金陵大学文、理学院等合并，仍名南京大学。'\n" +
                ");\n");
        database.execSQL("INSERT INTO ACTIVITY VALUES (\n" +
                "\t1, '数据挖掘技术的应用', \n" +
                "\t'谢玄洋博士', '中国人民银行高级工程师', \n" +
                "\tDATETIME('NOW'), '唯真楼小剧场', \n" +
                "\t1\n" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY VALUES (\n" +
                "\t2, '德意志帝国的崛起', \n" +
                "\t'李维', '南京大学教授', \n" +
                "\tDATETIME('NOW'), '唯真楼小剧场', \n" +
                "\t21\n" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY VALUES (\n" +
                "\t3, '人文精神与社会转型', \n" +
                "\t'陈平原', '中国人民大学客座教授', \n" +
                "\tDATETIME('NOW'), '唯真楼小剧场', \n" +
                "\t3\n" +
                ");");
        database.execSQL("INSERT INTO TAG VALUES(\n" +
                "\t1, '计算机技术', \n" +
                "NULL" +
                ");");
        database.execSQL("INSERT INTO TAG VALUES(\n" +
                "\t2, '数据挖掘', \n" +
                "NULL" +
                ");");
        database.execSQL("INSERT INTO TAG VALUES(\n" +
                "\t3, '人文历史', \n" +
                "NULL" +
                ");");
        database.execSQL("INSERT INTO TAG VALUES(\n" +
                "\t4, '社会学', \n" +
                "NULL" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY_TAG VALUES(\n" +
                "\t1, 1\n" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY_TAG VALUES(\n" +
                "\t1, 2\n" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY_TAG VALUES(\n" +
                "\t2, 3\n" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY_TAG VALUES(\n" +
                "\t3, 3\n" +
                ");");
        database.execSQL("INSERT INTO ACTIVITY_TAG VALUES(\n" +
                "\t3, 4\n" +
                ");");
    }

    public void DBclean() {
        database.execSQL("DELETE FROM INSTITUTE;");
        database.execSQL("DELETE FROM ACTIVITY;");
        database.execSQL("DELETE FROM TAG;");
        database.execSQL("DELETE FROM ACTIVITY_TAG;");
    }

    public void closeDB() {
        database.close();

    }
}
