package id.asiatek.asiatrans.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.DrawableCompat;

public class CompatUtils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void changeTintColorDrawable(View v, int color) {
        Drawable buttonDrawable = v.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, color);
        v.setBackground(buttonDrawable);
    }

    public static void changeBgColor(View v, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            v.setBackgroundTintList(colorStateList);
        }
    }
}
