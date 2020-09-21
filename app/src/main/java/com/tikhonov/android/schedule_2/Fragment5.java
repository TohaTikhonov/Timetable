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

public class Fragment5 extends Fragment {
    public Fragment5() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_friday, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor cursor = MainActivity.db.query("THEME", new String[]{"LINES"}, "ISSELECTED = ?", new String[] {"1"}, null, null, null);
        cursor.moveToFirst();
        setLines(cursor.getString(0));
        cursor.close();
    }

    public void setLines(String path) {
        ArrayList<View> list = new ArrayList<>();
        list.add(Objects.requireNonNull(getView()).findViewById(R.id.line_friday_1));
        list.add(getView().findViewById(R.id.line_friday_2));
        list.add(getView().findViewById(R.id.line_friday_3));
        list.add(getView().findViewById(R.id.line_friday_4));
        list.add(getView().findViewById(R.id.line_friday_5));
        list.add(getView().findViewById(R.id.line_friday_6));
        list.add(getView().findViewById(R.id.line_friday_7));
        list.add(getView().findViewById(R.id.line_friday_8));
        for (View v : list) {
            v.setBackgroundColor(Color.parseColor(path));
        }
    }
}