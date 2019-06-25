package com.example.seguidor_de_linha.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "dados.Database";
    public static final String ID = "_id";
    public static final String TABLE = "dados";
    public static final String INFORMACAO = "info";
    public static final String THRESHOLD = "thre";
    public static final String VMAX = "vmax";
    public static final String VMIN = "vmin";
    public static final String KP = "kp";
    public static final String KD = "kd";
    public static final String TIMER = "timer";
    public static final String T1 = "t1";
    public static final String T2 = "t2";
    public static final String T3 = "t3";
    public static final String T4 = "t4";
    public static final String T5 = "t5";
    public static final String T6 = "t6";
    public static final String T7 = "t7";
    public static final String T8 = "t8";
    public static final String T9 = "t9";
    public static final String T10 = "t10";
    public static final String D1 = "d1";
    public static final String D2 = "d2";
    public static final String D3 = "d3";
    public static final String D4 = "d4";
    private static final int VERSION = 1;

    public CreateDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ("
                + "_id integer primary key autoincrement, " +
                "info text, " +
                "thre integer, " +
                "vmax integer, " +
                "vmin integer," +
                "kp integer," +
                "kd integer," +
                "timer integer," +
                "t1 integer," +
                "t2 integer," +
                "t3 integer," +
                "t4 integer," +
                "t5 integer," +
                "t6 integer," +
                "t7 integer," +
                "t8 integer," +
                "t9 integer," +
                "t10 integer," +
                "d1 integer," +
                "d2 integer," +
                "d3 integer," +
                "d4 integer" +
        ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
