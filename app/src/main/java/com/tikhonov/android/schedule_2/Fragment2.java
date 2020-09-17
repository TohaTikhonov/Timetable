package com.tikhonov.android.schedule_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment2 extends Fragment {
    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_tuesday, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(getContext());
        SQLiteDatabase db = raspisanieDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("THEME", new String[]{"BUTTONBACK", "BUTTONTEXT", "BIGLINES", "SMALLLINES"}, null, null, null, null, null);
        cursor.moveToFirst();
        setBLines(cursor.getString(2));
        db.close();
        cursor.close();
    }

    public void setBLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(Objects.requireNonNull(getView()).findViewById(R.id.bline_tuesday_1));
        list.add(getView().findViewById(R.id.bline_tuesday_2));
        list.add(getView().findViewById(R.id.bline_tuesday_3));
        list.add(getView().findViewById(R.id.bline_tuesday_4));
        list.add(getView().findViewById(R.id.bline_tuesday_5));
        for (View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }
}
