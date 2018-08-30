package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice04ScaleView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice04ScaleView(Context context) {
        super(context);
    }

    public Practice04ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice04ScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         * canvas可以用来旋转   一定要记得save()   restore()
         * 2. canvas.rotate(degrees)  参数里的 degrees 是旋转角度，单位是度（也就是一周有 360° 的那个单位），方向是顺时针为正向； px 和 py 是轴心的位置。
         * */

        canvas.save();
        canvas.rotate(180, point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(30, point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
