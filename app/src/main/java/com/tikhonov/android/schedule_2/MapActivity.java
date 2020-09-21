package com.tikhonov.android.schedule_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        button = findViewById(R.id.button5);
    }

    private Cursor cursor;
    Button button;

    @Override
    public void onResume() {
        super.onResume();
        cursor = MainActivity.db.query("THEME", new String[]{"BUTTONBACK", "BUTTONTEXT"}, "ISSELECTED = ?", new String[] {"1"}, null, null, null);
        cursor.moveToFirst();
        setButtonsColor(cursor.getString(0));
        setButtonsText(cursor.getString(1));
    }

    public void setButtonsColor(String path) {
        int drawableId = this.getResources().getIdentifier(path, "drawable", getPackageName());
        Drawable shapeDrawable = ContextCompat.getDrawable(getApplicationContext(), drawableId);
        button.setBackground(shapeDrawable);
    }

    public void setButtonsText(String path) {
        button.setTextColor(Color.parseColor(path));
    }

    @Override
    protected void onPause() {
        super.onPause();
        cursor.close();
    }

    public void backMap(View view) {
        onBackPressed();
    }
}