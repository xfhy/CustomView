package com.hencoder.hencoderpracticedraw4.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice01ClipRectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public Practice01ClipRectView(Context context) {
        super(context);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = (getWidth() - bitmap.getWidth()) / 2;
        int top = (getHeight() - bitmap.getHeight()) / 2;

        /*
        1. 范围裁切  可以是Path ,也可以是简单的Rect
        2. 一定要记得先canvas.save(),再canvas.restore();

        Path path = new Path();
        int radius = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() / 2 : bitmap.getWidth() / 2;
        path.addCircle(getWidth() / 2, getHeight() / 2, radius, Path.Direction.CW);*/

        canvas.save();
        canvas.clipRect(left + 10, top + 50, left + bitmap.getWidth() - 10, top + bitmap.getHeight() - 50);
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();
    }
}
