package id.asiatek.asiatrans.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class QuicksandButton extends Button {

    public QuicksandButton(Context context) {
        super( context );
        setFont();

    }

    public QuicksandButton(Context context, AttributeSet attrs) {
        super( context, attrs );
        setFont();
    }

    public QuicksandButton(Context context, AttributeSet attrs, int defStyle) {
        super( context, attrs, defStyle );
        setFont();
    }

    private void setFont() {
        Typeface normal = Typeface.createFromAsset(getContext().getAssets(),"Quicksand-Bold.ttf");
        setTypeface( normal, Typeface.NORMAL );

        Typeface bold = Typeface.createFromAsset( getContext().getAssets(), "Quicksand-Bold.ttf" );
        setTypeface( normal, Typeface.BOLD );
    }




}