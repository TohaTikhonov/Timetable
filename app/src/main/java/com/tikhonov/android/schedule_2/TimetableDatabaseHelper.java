package com.tikhonov.android.schedule_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimetableDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mydbc";
    private static final int DB_VERSION = 3;

    public TimetableDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DAY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "ON1 TEXT, "
                + "UNDER1 TEXT, "
                + "ON2 TEXT, "
                + "UNDER2 TEXT, "
                + "ON3 TEXT, "
                + "UNDER3 TEXT, "
                + "ON4 TEXT, "
                + "UNDER4 TEXT, "
                + "ON5 TEXT, "
                + "UNDER5 TEXT, "
                + "ON6 TEXT, "
                + "UNDER6 TEXT, "
                + "ON7 TEXT, "
                + "UNDER7 TEXT);");

        putDay(sqLiteDatabase, "Понедельник", "", "", "","", "","", "", "", "", "", "", "","", "");
        putDay(sqLiteDatabase, "Вторник", "", "", "","", "","", "", "", "", "", "", "","", "");
        putDay(sqLiteDatabase, "Среда", "", "", "","", " ","", " ", "", "", "", "", "","", "");
        putDay(sqLiteDatabase, "Четверг", "", "", "","", "","", "", "", "", "","", "", "", "");
        putDay(sqLiteDatabase, "Пятница", "", "", "","", "","", "", "", "", "", "", "","", "");
        putDay(sqLiteDatabase, "Суббота", "", "", "","", "","", "", "", "", "", "", "","", "");
    }

    public void putDay(SQLiteDatabase sqLiteDatabase, String name, String on1, String under1,
                         String on2, String under2, String on3, String under3, String on4, String under4,
                       String on5, String under5, String on6, String under6, String on7, String under7) {

        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("ON1", on1);
        values.put("UNDER1", under1);
        values.put("ON2", on2);
        values.put("UNDER2", under2);
        values.put("ON3", on3);
        values.put("UNDER3", under3);
        values.put("ON4", on4);
        values.put("UNDER4", under4);
        values.put("ON5", on5);
        values.put("UNDER5", under5);
        values.put("ON6", on6);
        values.put("UNDER6", under6);
        values.put("ON7", on7);
        values.put("UNDER7", under7);

        sqLiteDatabase.insert("DAY", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
