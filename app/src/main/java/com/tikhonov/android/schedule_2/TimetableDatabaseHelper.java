package com.tikhonov.android.schedule_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimetableDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    public TimetableDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE THEME (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "IMAGE TEXT, "
                + "BUTTONBACK TEXT, "
                + "BUTTONTEXT TEXT, "
                + "IMAGEBLACK TEXT, "
                + "LINES TEXT, "
                + "SCREEN TEXT, "
                + "SELECTEDSCREEN TEXT, "
                + "ISSELECTED TEXT);");

        putTheme(sqLiteDatabase, "alina", "rounded_alina", "#ffffff", "alina_black",
                "#C88548", "alina_theme", "alina_selected", "0");
        putTheme(sqLiteDatabase, "loli", "rounded_loli", "#FDDDD2", "loli_black",
                "#E69BA2", "loli_theme", "loli_selected", "0");
        putTheme(sqLiteDatabase, "electricity", "rounded_electricity", "#000000", "electricity_black",
                "#EBD8EC", "electricity_theme", "electricity_selected", "1");
        putTheme(sqLiteDatabase, "iamgay", "rounded_iamgay", "#C4E5EC", "iamgay_black",
                "#5E7984", "iamgay_theme", "iamgay_selected", "0");
        putTheme(sqLiteDatabase, "pornhub", "rounded_pornhub", "#000000", "pornhub_black",
                "#f7971c", "pornhub_theme", "pornhub_selected", "0");
        putTheme(sqLiteDatabase, "satanism", "rounded_satanism", "#BAB8AA", "satanism_black",
                "#5D0C10", "satanism_theme", "satanism_selected", "0");
    }

    public void putTheme(SQLiteDatabase sqLiteDatabase, String image, String buttonBack, String buttonText,
                         String imageBlack, String lines, String screen, String selectedScreen, String selected) {

        ContentValues values = new ContentValues();
        values.put("IMAGE", image);
        values.put("BUTTONBACK", buttonBack);
        values.put("BUTTONTEXT", buttonText);
        values.put("IMAGEBLACK", imageBlack);
        values.put("LINES", lines);
        values.put("SCREEN", screen);
        values.put("SELECTEDSCREEN", selectedScreen);
        values.put("ISSELECTED", selected);
        sqLiteDatabase.insert("THEME", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
