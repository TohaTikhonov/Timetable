package com.tikhonov.android.schedule_2;

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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment4 extends Fragment {
    private Button mButton;

    public Fragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_thursday, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mButton = (Button) Objects.requireNonNull(getView()).findViewById(R.id.button9);
        SQLiteOpenHelper raspisanieDatabaseHelper = new RaspisanieDatabaseHelper(getContext());
        SQLiteDatabase db = raspisanieDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("THEME", new String[]{"BUTTONBACK", "BUTTONTEXT", "BIGLINES", "SMALLLINES"}, null, null, null, null, null);
        cursor.moveToFirst();

        setButtonBack(cursor.getString(0));
        setButtonsText(cursor.getString(1));
        setBLines(cursor.getString(2));
        setSLines(cursor.getString(3));
        db.close();
        cursor.close();
    }

    public void setButtonBack(String path) {
        int drawableId = this.getResources().getIdentifier(path, "drawable", Objects.requireNonNull(getActivity()).getPackageName());
        Drawable shapeDrawable = ContextCompat.getDrawable(getActivity().getApplicationContext(), drawableId);
        mButton.setBackground(shapeDrawable);
    }

    public void setButtonsText(String path) {
        mButton.setTextColor(Color.parseColor(path));
    }

    public void setBLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(Objects.requireNonNull(getView()).findViewById(R.id.bline_thursday_1));
        list.add(getView().findViewById(R.id.bline_thursday_2));
        list.add(getView().findViewById(R.id.bline_thursday_3));
        list.add(getView().findViewById(R.id.bline_thursday_4));
        list.add(getView().findViewById(R.id.bline_thursday_5));
        list.add(getView().findViewById(R.id.bline_thursday_6));
        for(View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }
    public void setSLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(Objects.requireNonNull(getView()).findViewById(R.id.sline_thursday_1));
        list.add(getView().findViewById(R.id.sline_thursday_2));
        list.add(getView().findViewById(R.id.sline_thursday_3));
        for(View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }
}