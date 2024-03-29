package id.asiatek.asiatrans.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import id.asiatek.asiatrans.utils.ViewUtils;

public class RoundedTopImageView extends AppCompatImageView {
    private Paint mPaint;
    private Path mPath;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private int mRadius = ViewUtils.convertDpToPixel(10);
    private int mWidth;
    private int mHeight;
    private Drawable mDrawable;

    public RoundedTopImageView(Context context) {
        super(context);
        init();
    }

    public RoundedTopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedTopImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);

        mPath = new Path();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        mDrawable = drawable;
        if (drawable == null) {
            return;
        }
        mBitmap = drawableToBitmap(drawable);

        int bDIWidth = mBitmap.getWidth();
        int bDIHeight = mBitmap.getHeight();

        //Fit to screen.
        float scale;
        if ((mHeight / (float)bDIHeight) >= (mWidth / (float)bDIWidth)){
            scale =  mHeight / (float)bDIHeight;
        } else {
            scale = mWidth / (float)bDIWidth;
        }

        float borderLeft = (mWidth - (bDIWidth * scale)) / 2;
        float borderTop = (mHeight - (bDIHeight * scale)) / 2;

        mMatrix = getImageMatrix();
        RectF drawableRect = new RectF(0, 0, bDIWidth, bDIHeight);
        RectF viewRect = new RectF(borderLeft, borderTop, (bDIWidth * scale) + borderLeft, (bDIHeight * scale) + borderTop);
        mMatrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        invalidate();
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if ((mDrawable != null) && (mHeight > 0) && (mWidth > 0)) {
            setImageDrawable(mDrawable);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap == null) {
            return;
        }

        canvas.drawColor(Color.TRANSPARENT);

        mPath.reset();
        mPath.moveTo(0, mRadius);
        mPath.lineTo(0, canvas.getHeight());
        mPath.lineTo(canvas.getWidth(), canvas.getHeight());
        mPath.lineTo(canvas.getWidth(), mRadius);
        mPath.quadTo(canvas.getWidth(), 0, canvas.getWidth() - mRadius, 0);
        mPath.lineTo(mRadius, 0);
        mPath.quadTo(0, 0, 0, mRadius);


        canvas.drawPath(mPath, mPaint);
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

}
