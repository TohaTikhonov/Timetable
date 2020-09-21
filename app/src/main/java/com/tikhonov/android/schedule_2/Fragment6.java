package com.tikhonov.android.schedule_2;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Objects;

public class Fragment6 extends Fragment {
    public Fragment6() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_saturday, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor cursor = MainActivity.db.query("THEME", new String[]{"LINES"}, "ISSELECTED = ?", new String[] {"1"}, null, null, null);
        cursor.moveToFirst();
        setBLines(cursor.getString(0));
        cursor.close();
    }

    public void setBLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(Objects.requireNonNull(getView()).findViewById(R.id.line_satarday_1));
        list.add(getView().findViewById(R.id.line_satarday_2));
        for (View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }
}