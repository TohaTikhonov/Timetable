package com.tikhonov.android.schedule_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Set;

public class DayActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    public void onResume() {
        super.onResume();
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(this);
        db = raspisanieDatabaseHelper.getReadableDatabase();
        cursor = db.query("THEME", new String[]{"IMAGEBLACK"}, null, null, null, null, null);
        cursor.moveToFirst();

        setMainImage(cursor.getString(0));
    }

    public void setMainImage(String path) { //Настраивает картинку понедельника
        int drawableId = this.getResources().getIdentifier(path, "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.image_day);
        imageView.setImageResource(drawableId);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void toSettings(View view) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        Intent intent = getIntent();
        int currentItem = 0;
        String day = intent.getExtras().getString("day");
        switch (day) {
            case "Понедельник":
                currentItem = 0;
                break;
            case "Вторник":
                currentItem = 1;
                break;
            case "Среда":
                currentItem = 2;
                break;
            case "Четверг":
                currentItem = 3;
                break;
            case "Пятница":
                currentItem = 4;
                break;
        }

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentItem);
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                case 2:
                    return new Fragment3();
                case 3:
                    return new Fragment4();
                case 4:
                    return new Fragment5();
                default:
                    return new Fragment1();
            }
        }
    }
}