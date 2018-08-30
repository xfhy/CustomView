package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice07MatrixTranslateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    private Matrix mMatrix;

    public Practice07MatrixTranslateView(Context context) {
        super(context);
    }

    public Practice07MatrixTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07MatrixTranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Matrix mMatrix2;

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        mMatrix = new Matrix();
        mMatrix.postTranslate(-100, -100);

        mMatrix2 = new Matrix();
        mMatrix2.postTranslate(100, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        *  使用 Matrix 来做常见变换
        *  Matrix能做的变换更多
        *
        *  Matrix 做常见变换的方式：
            1.创建 Matrix 对象；
            2.调用 Matrix 的 pre/postTranslate/Rotate/Scale/Skew() 方法来设置几何变换；
            3.使用 Canvas.setMatrix(matrix) 或 Canvas.concat(matrix) 来把几何变换应用到 Canvas。

            把 Matrix 应用到 Canvas 有两个方法： Canvas.setMatrix(matrix) 和 Canvas.concat(matrix)。

Canvas.setMatrix(matrix)：用 Matrix 直接替换 Canvas 当前的变换矩阵，即抛弃 Canvas 当前的变换，改用 Matrix 的变换（注：根据下面评论里以及我在微信公众号中收到的反馈，不同的系统中  setMatrix(matrix) 的行为可能不一致，所以还是尽量用 concat(matrix) 吧）；
Canvas.concat(matrix)：用 Canvas 当前的变换矩阵和 Matrix 相乘，即基于 Canvas 当前的变换，叠加上 Matrix 中的变换。
        * */

        canvas.save();
        canvas.setMatrix(mMatrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.setMatrix(mMatrix2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
