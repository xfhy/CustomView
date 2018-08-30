package com.hencoder.hencoderpracticedraw4.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
    Paint paint = new Paint();
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    private Path mPath1;
    private Path mPath2;

    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        mPath1 = new Path();
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        mPath1.addCircle(point1.x + bitmapWidth - 50, point1.y + bitmapHeight - 50, 200, Path.Direction.CW);

        mPath2 = new Path();
        //前面在说 dir 参数的时候提到， Path.setFillType(fillType) 是用来设置图形自相交时的填充算法的：
        mPath2.setFillType(Path.FillType.INVERSE_WINDING);
        mPath2.addCircle(point2.x + 200,point2.y + 200,150, Path.Direction.CW);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.clipPath(mPath1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, this.paint);
        canvas.restore();

        canvas.save();
        canvas.clipPath(mPath2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, this.paint);
        canvas.restore();
    }
}
