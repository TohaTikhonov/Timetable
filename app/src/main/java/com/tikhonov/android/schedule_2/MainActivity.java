package com.tikhonov.android.schedule_2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private SQLiteDatabase db;
    private Cursor cursor;
    public ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            SQLiteOpenHelper timetableDatabaseHelper = new RaspisanieDatabaseHelper(this);
            db = timetableDatabaseHelper.getReadableDatabase();
            cursor = db.query("THEME", new String[]{"IMAGE", "BUTTONBACK", "BUTTONTEXT"}, null, null, null, null, null);
            cursor.moveToFirst();

            setMainImage(cursor.getString(0));
            setButtonsColor(cursor.getString(1));
            setButtonsText(cursor.getString(2));
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cursor.close();
        db.close();
    }

    public void toParticularDay(View view) {
        Intent intent = new Intent(this, DayActivity.class);
        Button button = (Button) view;
        String day = button.getText().toString();
        intent.putExtra("nameDay", day);
        startActivity(intent);
    }

    public void setMainImage(String path) {
        int drawableId = this.getResources().getIdentifier(path, "drawable", getPackageName());
        ImageView  imageView = (ImageView) findViewById(R.id.image_main);
        imageView.setImageResource(drawableId);
    }

    public void setButtonsColor(String path) {
        int drawableId = this.getResources().getIdentifier(path, "drawable", getPackageName());
        Drawable shapeDrawable = ContextCompat.getDrawable(getApplicationContext(), drawableId);
        for(Button b : buttons) {
            b.setBackground(shapeDrawable);
        }
    }

    public void setButtonsText(String path) {
        for(Button b : buttons) {
            b.setTextColor(Color.parseColor(path));
        }
    }
}