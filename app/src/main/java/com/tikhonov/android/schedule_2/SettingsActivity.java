package com.tikhonov.android.schedule_2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;
    private Cursor cursorSelected;
    public ArrayList<ImageView> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        list.add((ImageView) findViewById(R.id.theme1));
        list.add((ImageView) findViewById(R.id.theme2));
        list.add((ImageView) findViewById(R.id.theme3));
        list.add((ImageView) findViewById(R.id.theme4));
        list.add((ImageView) findViewById(R.id.theme5));
    }

    @Override
    public void onResume() {
        super.onResume();
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getReadableDatabase();
        cursor = db.query("THEME", new String[]{"IMAGEBLACK", "BUTTONBACK", "BUTTONTEXT"}, null, null, null, null, null);
        cursor.moveToFirst();
        setMainImage(cursor.getString(0));

        int drawableId = this.getResources().getIdentifier(cursor.getString(1), "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) findViewById(R.id.button_settings);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor(cursor.getString(2)));

        cursorSelected = db.query("SELECTED", new String[] {"SCREEN", "SELECTEDSCREEN", "SELECTED"}, null, null, null, null, null);
        cursorSelected.moveToFirst();
        if(Integer.parseInt(cursorSelected.getString(2)) == 0) {
            setScreen("alina_theme", list.get(0));
        } else {
            setScreen("alina_selected", list.get(0));
        }
        cursorSelected.moveToNext();
        if(Integer.parseInt(cursorSelected.getString(2)) == 0) {
            setScreen("loli_theme", list.get(1));
        } else {
            setScreen("loli_selected", list.get(1));
        }
        cursorSelected.moveToNext();
        if(Integer.parseInt(cursorSelected.getString(2)) == 0) {
            setScreen("storm_theme", list.get(2));
        } else {
            setScreen("storm_selected", list.get(2));
        }
        cursorSelected.moveToNext();
        if(Integer.parseInt(cursorSelected.getString(2)) == 0) {
            setScreen("noct_theme", list.get(3));
        } else {
            setScreen("noct_selected", list.get(3));
        }
        cursorSelected.moveToNext();
        if(Integer.parseInt(cursorSelected.getString(2)) == 0) {
            setScreen("sasha_theme", list.get(4));
        } else {
            setScreen("sasha_selected", list.get(4));
        }
    }

    public void setMainImage(String path) { //Настраивает картинку понедельника
        int drawableId = this.getResources().getIdentifier(path, "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.image_settings);
        imageView.setImageResource(drawableId);
    }

    public void backDay(View view) {
        onBackPressed();
    }

    public void updateAlina(View view) { //тема Alina
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", "alina");
        values.put("BUTTONBACK", "rounded_alina");
        values.put("BUTTONTEXT", "#D9C6BF");
        values.put("IMAGEBLACK", "alina_black");
        values.put("BIGLINES", "#C88548");
        values.put("SMALLLINES", "#985E30");

        setMainImage("alina_black");

        int drawableId = this.getResources().getIdentifier("rounded_alina", "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) findViewById(R.id.button_settings);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor("#D9C6BF"));

        setScreen("alina_selected", list.get(0));
        setScreen("loli_theme", list.get(1));
        setScreen("storm_theme", list.get(2));
        setScreen("noct_theme", list.get(3));
        setScreen("sasha_theme", list.get(4));

        db.update("THEME", values, "_id=?", new String[]{"1"});

        cursorSelected = db.query("SELECTED", new String[] {"SELECTED"}, null, null, null, null, null);

        cursorSelected.moveToFirst();
        ContentValues selectedValues = new ContentValues();
        selectedValues.put("SELECTED", 1);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"1"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"2"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"3"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"4"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"5"});
    }

    public void setScreen(String name, ImageView iv) {
        int pathImage2 = this.getResources().getIdentifier(name, "drawable", getPackageName());
        iv.setImageResource(pathImage2);
    }

    public void updateLoli(View view) { //тема Loli
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", "loli");
        values.put("BUTTONBACK", "rounded_loli");
        values.put("BUTTONTEXT", "#FDDDD2");
        values.put("IMAGEBLACK", "loli_black");
        values.put("BIGLINES", "#E69BA2");
        values.put("SMALLLINES", "#DC8E9B");
        setMainImage("loli_black");

        int drawableId = this.getResources().getIdentifier("rounded_loli", "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) findViewById(R.id.button_settings);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor("#FDDDD2"));

        setScreen("alina_theme", list.get(0));
        setScreen("loli_selected", list.get(1));
        setScreen("storm_theme", list.get(2));
        setScreen("noct_theme", list.get(3));
        setScreen("sasha_theme", list.get(4));

        db.update("THEME", values, "_id=?", new String[]{"1"});

        cursorSelected = db.query("SELECTED", new String[] {"SELECTED"}, null, null, null, null, null);

        cursorSelected.moveToFirst();
        ContentValues selectedValues = new ContentValues();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"1"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 1);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"2"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"3"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"4"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"5"});
    }

    public void updateElectricity(View view) { // тема Electricity
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", "storm");
        values.put("BUTTONBACK", "rounded_storm");
        values.put("BUTTONTEXT", "#000000");
        values.put("IMAGEBLACK", "storm_black");
        values.put("BIGLINES", "#EBD8EC");
        values.put("SMALLLINES", "#EBD8EC");

        int drawableId = this.getResources().getIdentifier("rounded_storm", "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) findViewById(R.id.button_settings);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor("#000000"));
        setMainImage("storm_black");

        setScreen("alina_theme", list.get(0));
        setScreen("loli_theme", list.get(1));
        setScreen("storm_selected", list.get(2));
        setScreen("noct_theme", list.get(3));
        setScreen("sasha_theme", list.get(4));

        db.update("THEME", values, "_id=?", new String[]{"1"});

        cursorSelected = db.query("SELECTED", new String[] {"SELECTED"}, null, null, null, null, null);

        cursorSelected.moveToFirst();
        ContentValues selectedValues = new ContentValues();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"1"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"2"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 1);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"3"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"4"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"5"});
    }

    public void updateIamgey(View view) { // тема Iamgey
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", "noct");
        values.put("BUTTONBACK", "rounded_iamgey");
        values.put("BUTTONTEXT", "#C4E5EC");
        values.put("IMAGEBLACK", "noct_black");
        values.put("BIGLINES", "#5E7984");
        values.put("SMALLLINES", "#9FB2B9");

        int drawableId = this.getResources().getIdentifier("rounded_iamgey", "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) findViewById(R.id.button_settings);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor("#C4E5EC"));
        setMainImage("noct_black");

        setScreen("alina_theme", list.get(0));
        setScreen("loli_theme", list.get(1));
        setScreen("storm_theme", list.get(2));
        setScreen("noct_selected", list.get(3));
        setScreen("sasha_theme", list.get(4));

        db.update("THEME", values, "_id=?", new String[]{"1"});

        cursorSelected = db.query("SELECTED", new String[] {"SELECTED"}, null, null, null, null, null);

        cursorSelected.moveToFirst();
        ContentValues selectedValues = new ContentValues();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"1"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"2"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"3"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 1);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"4"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"5"});
    }

    public void updatePornhub(View view) { // тема Iamgey
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IMAGE", "sasha");
        values.put("BUTTONBACK", "rounded_pornhub");
        values.put("BUTTONTEXT", "#ffffff");
        values.put("IMAGEBLACK", "sasha_black");
        values.put("BIGLINES", "#f7971c");
        values.put("SMALLLINES", "#9E733C");

        int drawableId = this.getResources().getIdentifier("rounded_pornhub", "drawable", getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) findViewById(R.id.button_settings);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor("#ffffff"));
        setMainImage("sasha_black");

        int pathImage1 = this.getResources().getIdentifier("sasha_selected", "drawable", getPackageName());
        ImageView image = (ImageView) findViewById(R.id.theme5);
        image.setImageResource(pathImage1);

        setScreen("alina_theme", list.get(0));
        setScreen("loli_theme", list.get(1));
        setScreen("storm_theme", list.get(2));
        setScreen("noct_theme", list.get(3));
        setScreen("sasha_selected", list.get(4));

        db.update("THEME", values, "_id=?", new String[]{"1"});

        cursorSelected = db.query("SELECTED", new String[] {"SELECTED"}, null, null, null, null, null);

        cursorSelected.moveToFirst();
        ContentValues selectedValues = new ContentValues();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"1"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"2"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"3"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 0);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"4"});

        cursorSelected.moveToNext();
        selectedValues.put("SELECTED", 1);
        db.update("SELECTED", selectedValues, "_id=?", new String[]{"5"});
    }
}