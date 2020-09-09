package com.tikhonov.android.schedule_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RaspisanieDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "a";
    private static final int DB_VERSION = 2;

    public RaspisanieDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE THEME (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "IMAGE TEXT, "
                + "BUTTONBACK TEXT, "
                + "BUTTONTEXT TEXT, "
                + "IMAGEBLACK TEXT, "
                + "BIGLINES TEXT, "
                + "SMALLLINES TEXT);");

        ContentValues values = new ContentValues();
        values.put("IMAGE", "storm");
        values.put("BUTTONBACK", "rounded_storm");
        values.put("BUTTONTEXT", "#000000");
        values.put("IMAGEBLACK", "storm_black");
        values.put("BIGLINES", "#EBD8EC");
        values.put("SMALLLINES", "#EBD8EC");
        sqLiteDatabase.insert("THEME", null, values);

        sqLiteDatabase.execSQL("CREATE TABLE SELECTED (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "SCREEN TEXT, "
                        + "SELECTEDSCREEN TEXT, "
                        + "SELECTED INTEGER);");

        putScreens(sqLiteDatabase, "alina_theme", "alina_selected", 0);
        putScreens(sqLiteDatabase, "loli_theme", "loli_selected", 0);
        putScreens(sqLiteDatabase, "storm_theme", "storm_selected", 1);
        putScreens(sqLiteDatabase, "noct_theme", "noct_selected", 0);
        putScreens(sqLiteDatabase, "sasha_theme", "sasha_selected", 0);
    }

    public void putScreens(SQLiteDatabase sqLiteDatabase, String screen, String selectedScreen, int selected) {
        ContentValues screenValues = new ContentValues();
        screenValues.put("SCREEN", screen);
        screenValues.put("SELECTEDSCREEN", selectedScreen);
        screenValues.put("SELECTED", selected);
        sqLiteDatabase.insert("SELECTED", null, screenValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
