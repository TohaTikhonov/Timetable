package com.tikhonov.android.schedule_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Button> buttons = new ArrayList<>();
    private ProgressBar progressBar;
    public static SQLiteDatabase db;
    private ImageView imageView;

    public static SharedPreferences sharedPreferences;
    public static final String fileName = "mysettings";
    public static final String IMAGE_BACKGROUND = "IMAGE";
    public static final String BUTTON_COLOR = "BUTTONBACK";
    public static final String BUTTON_TEXT_COLOR = "BUTTONTEXT";
    public static final String LINES_COLOR = "LINES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        imageView = (ImageView) findViewById(R.id.image_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        SQLiteOpenHelper timetableDatabaseHelper = new TimetableDatabaseHelper(this);
        db = timetableDatabaseHelper.getWritableDatabase();

        sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
        if (!sharedPreferences.contains(LINES_COLOR)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(IMAGE_BACKGROUND, "alina");
            editor.putString(BUTTON_COLOR, "rounded_alina");
            editor.putString(BUTTON_TEXT_COLOR, "#D9C6BF");
            editor.putString(LINES_COLOR, "#C88548");
            editor.apply();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ThemeSetter.setImage(this, getPackageName(), sharedPreferences.getString(IMAGE_BACKGROUND, null), imageView);
        ThemeSetter.setButtonsTextColor(buttons, sharedPreferences.getString(BUTTON_TEXT_COLOR, null));
        ThemeSetter.setButtonsColor(this, getPackageName(), sharedPreferences.getString(BUTTON_COLOR, null), getApplicationContext(), buttons);
    }

    @Override
    protected void onStop() {
        progressBar.setVisibility(View.GONE);
        super.onStop();
    }

    public void toToday(View view) {
        int numberDay = new GregorianCalendar().get(Calendar.DAY_OF_WEEK) - 1;
        if (numberDay == 0) {
            numberDay = 1;
            Toast.makeText(this, "Сегодня воскресенье, поэтому вот тебе понедельник", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, DayActivity.class);
        intent.putExtra(DayActivity.DAY_NAME, String.valueOf(numberDay));
        progressBar.setVisibility(View.VISIBLE);
        startActivity(intent);
    }

    public void toTomorrow(View view) {
        int numberDay = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        if (numberDay == 7) {
            numberDay = 1;
            Toast.makeText(this, "Завтра воскресенье, поэтому вот тебе понедельник", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, DayActivity.class);
        intent.putExtra(DayActivity.DAY_NAME, String.valueOf(numberDay));
        progressBar.setVisibility(View.VISIBLE);
        startActivity(intent);
    }

    public void toSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        progressBar.setVisibility(View.VISIBLE);
        startActivity(intent);
    }
}