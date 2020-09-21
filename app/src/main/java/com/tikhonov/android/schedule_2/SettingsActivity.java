package com.tikhonov.android.schedule_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private Cursor cursor;
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
        list.add((ImageView) findViewById(R.id.theme6));
    }

    @Override
    protected void onPause() {
        super.onPause();
        cursor.close();
    }

    @Override
    public void onResume() {
        super.onResume();

        cursor = MainActivity.db.query("THEME", new String[]{"IMAGEBLACK", "BUTTONBACK", "BUTTONTEXT", "SCREEN", "SELECTEDSCREEN", "ISSELECTED"}, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.getString(5).equals("0")) {
            cursor.moveToNext();
        }
        setMainImageANDButtonSettings(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        setSelectedScreenshots(cursor);
    }

    public void setSelectedScreenshots(Cursor cursor) {
        cursor.moveToFirst();
        if(Integer.parseInt(cursor.getString(5)) == 0) {
            setScreen("alina_theme", list.get(0));
        } else {
            setScreen("alina_selected", list.get(0));
        }
        cursor.moveToNext();
        if(Integer.parseInt(cursor.getString(5)) == 0) {
            setScreen("loli_theme", list.get(1));
        } else {
            setScreen("loli_selected", list.get(1));
        }
        cursor.moveToNext();
        if(Integer.parseInt(cursor.getString(5)) == 0) {
            setScreen("electricity_theme", list.get(2));
        } else {
            setScreen("electricity_selected", list.get(2));
        }
        cursor.moveToNext();
        if(Integer.parseInt(cursor.getString(5)) == 0) {
            setScreen("iamgay_theme", list.get(3));
        } else {
            setScreen("iamgay_selected", list.get(3));
        }
        cursor.moveToNext();
        if(Integer.parseInt(cursor.getString(5)) == 0) {
            setScreen("pornhub_theme", list.get(4));
        } else {
            setScreen("pornhub_selected", list.get(4));
        }
        cursor.moveToNext();
        if(Integer.parseInt(cursor.getString(5)) == 0) {
            setScreen("satanism_theme", list.get(5));
        } else {
            setScreen("satanism_selected", list.get(5));
        }
    }

    public void setMainImageANDButtonSettings(String pathMainImage, String pathButtonSettings, String color) {
        int drawableId1 = this.getResources().getIdentifier(pathMainImage, "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.image_settings);
        imageView.setImageResource(drawableId1);

        int drawableId2 = this.getResources().getIdentifier(pathButtonSettings, "drawable", getPackageName());
        Drawable shapeDrawable = ContextCompat.getDrawable(getApplicationContext(), drawableId2);
        Button button = (Button) findViewById(R.id.button4);
        button.setBackground(shapeDrawable);
        button.setTextColor(Color.parseColor(color));
    }

    public void backDay(View view) {
        onBackPressed();
    }

    public void setScreen(String name, ImageView iv) {
        int pathImage2 = this.getResources().getIdentifier(name, "drawable", getPackageName());
        iv.setImageResource(pathImage2);
    }










    
    public void updateTheme(String image, String theme_black, String rounded_theme, String color_button) {
        ContentValues values = new ContentValues();

        values.put("ISSELECTED", "0");
        MainActivity.db.update("THEME", values, "ISSELECTED=?", new String[]{"1"});
        values.put("ISSELECTED", "1");
        MainActivity.db.update("THEME", values, "IMAGE=?", new String[]{image});

        setMainImageANDButtonSettings(theme_black, rounded_theme, color_button);

        cursor = MainActivity.db.query("THEME", new String[]{"IMAGEBLACK", "BUTTONBACK", "BUTTONTEXT", "SCREEN", "SELECTEDSCREEN", "ISSELECTED"}, null, null, null, null, null);
        setSelectedScreenshots(cursor);
    }

    public void updateAlina(View view) { //тема Alina
        updateTheme("alina", "alina_black", "rounded_alina", "#D9C6BF");
    }

    public void updateLoli(View view) { //тема Loli
        updateTheme("loli", "loli_black", "rounded_loli", "#FDDDD2");
    }

    public void updateElectricity(View view) { // тема Electricity
        updateTheme("electricity", "electricity_black", "rounded_electricity", "#000000");
    }

    public void updateIamgay(View view) { // тема Iamgey
        updateTheme("iamgay", "iamgay_black", "rounded_iamgay", "#C4E5EC");
    }

    public void updatePornhub(View view) { // тема Pornhub
        updateTheme("pornhub", "pornhub_black", "rounded_pornhub", "#ffffff");
    }

    public void updateSatanism(View view) { // тема Satanism
        updateTheme("satanism", "satanism_black", "rounded_satanism", "#ffffff");
    }
}