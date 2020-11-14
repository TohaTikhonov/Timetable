package com.tikhonov.android.schedule_2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ThemeSetter {
    public static void setImage(Context context, String packageName, String nameImage, ImageView imageView) {
        int drawableId = context.getResources().getIdentifier(nameImage, "drawable", packageName);
        imageView.setImageResource(drawableId);
    }

    public static void setButtonsTextColor(ArrayList<Button> buttons, String colorCode) {
        for (Button b : buttons) {
            b.setTextColor(Color.parseColor(colorCode));
        }
    }

    public static void setButtonsColor(Context context, String packageName, String path, Context applicationContext, ArrayList<Button> buttons) {
        int drawableId = context.getResources().getIdentifier(path, "drawable", packageName);
        Drawable shapeDrawable = ContextCompat.getDrawable(applicationContext, drawableId);
        for (Button b : buttons) {
            b.setBackground(shapeDrawable);
        }
    }

    public static void setBackgroundViews(ArrayList<View> list, String colorCode) {
        for (View v : list) {
            v.setBackgroundColor(Color.parseColor(colorCode));
        }
    }
}
