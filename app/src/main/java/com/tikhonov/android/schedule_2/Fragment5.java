package com.tikhonov.android.schedule_2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment5 extends Fragment {
    private SQLiteDatabase db;
    private Cursor cursor;

    public Fragment5() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_friday, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(getContext());
        db = raspisanieDatabaseHelper.getReadableDatabase();
        cursor = db.query("THEME", new String[]{"BUTTONBACK", "IMAGEBLACK", "BUTTONTEXT", "BIGLINES", "SMALLLINES"}, null, null, null, null, null);
        cursor.moveToFirst();
        setButtonBack(cursor.getString(0));
        setButtonsText(cursor.getString(2));
        setBLines(cursor.getString(3));
        setSLines(cursor.getString(4));
    }

    public void setButtonBack(String path) {
        int drawableId = this.getResources().getIdentifier(path, "drawable", getActivity().getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable shapeDrawable = this.getResources().getDrawable(drawableId);
        Button button = (Button) getView().findViewById(R.id.button10);
        button.setBackground(shapeDrawable);
    }

    public void setButtonsText(String path) {
        Button button = (Button) getView().findViewById(R.id.button10);
        button.setTextColor(Color.parseColor(path));
    }

    public void setBLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(getView().findViewById(R.id.bline_friday_1));
        list.add(getView().findViewById(R.id.bline_friday_2));
        list.add(getView().findViewById(R.id.bline_friday_3));
        list.add(getView().findViewById(R.id.bline_friday_4));
        list.add(getView().findViewById(R.id.bline_friday_5));
        list.add(getView().findViewById(R.id.bline_friday_6));
        for(View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }

    public void setSLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(getView().findViewById(R.id.sline_friday_1));
        list.add(getView().findViewById(R.id.sline_friday_2));
        for(View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }
}