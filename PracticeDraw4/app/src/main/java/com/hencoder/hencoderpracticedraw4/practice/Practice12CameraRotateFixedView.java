package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Camera mCamera;

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        mCamera = new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Canvas 的几何变换顺序是反的，所以要把移动到中心的代码写在下面，把从中心移动回来的代码写在上面。

        canvas.save();
        //保存 Camera 的状态
        mCamera.save();
        // 旋转 Camera 的三维空间
        mCamera.rotateX(30);
        //旋转之后把投影移动回来
        canvas.translate(point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);
        // 把旋转投影到 Canvas
        mCamera.applyToCanvas(canvas);
        //旋转之前把绘制内容移动到轴心(原点)
        canvas.translate(-(point1.x + bitmap.getWidth() / 2), -(point1.y + bitmap.getHeight() / 2));
        //恢复 Camera 的状态
        mCamera.restore();
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        mCamera.save();
        mCamera.rotateY(20);
        //旋转之后把投影移动回来
        canvas.translate(point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);
        // 把旋转投影到 Canvas
        mCamera.applyToCanvas(canvas);
        //旋转之前把绘制内容移动到轴心(原点)
        canvas.translate(-(point1.x + bitmap.getWidth() / 2), -(point1.y + bitmap.getHeight() / 2));
        mCamera.restore();
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
