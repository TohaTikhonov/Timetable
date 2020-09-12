package com.tikhonov.android.schedule_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Objects;

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

    @Override
    protected void onPause() {
        super.onPause();
        cursor.close();
        db.close();
    }

    public void setMainImage(String path) {
        int drawableId = this.getResources().getIdentifier(path, "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.image_day);
        imageView.setImageResource(drawableId);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        Intent intent = getIntent();
        int currentItem = 0;
        String day = Objects.requireNonNull(intent.getExtras()).getString("nameDay");
        assert day != null;
        switch (day) {
            case "1":
                currentItem = 1002;
                break;
            case "2":
                currentItem = 1003;
                break;
            case "3":
                currentItem = 1004;
                break;
            case "4":
                currentItem = 1005;
                break;
            case "5":
                currentItem = 1006;
                break;
            case "6":
                currentItem = 1007;
                break;
        }

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentItem);
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            position = position % 6;
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
                case 5:
                    return new Fragment6();
                default:
                    return new Fragment1();
            }
        }
    }
}