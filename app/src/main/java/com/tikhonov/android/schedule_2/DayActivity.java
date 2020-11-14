package com.tikhonov.android.schedule_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class DayActivity extends AppCompatActivity {
    public static final String DAY_NAME = "dayName";
    private ImageView imageView;

    @Override
    public void onResume() {
        super.onResume();
        ThemeSetter.setImage(this, getPackageName(), MainActivity.sharedPreferences.getString(MainActivity.IMAGE_BACKGROUND, "alina"), imageView);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        imageView = (ImageView) findViewById(R.id.image_day);

        Intent intent = getIntent();
        int currentItem = 0;
        String day = Objects.requireNonNull(intent.getExtras()).getString(DAY_NAME);
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
                case 1:
                    return new DayFragment("Вторник");
                case 2:
                    return new DayFragment("Среда");
                case 3:
                    return new DayFragment("Четверг");
                case 4:
                    return new DayFragment("Пятница");
                case 5:
                    return new DayFragment("Суббота");
                default:
                    return new DayFragment("Понедельник");
            }
        }
    }
}