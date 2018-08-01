package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice16TextPathView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path textPath = new Path();
    String text = "Hello HenCoder";

    public Practice16TextPathView(Context context) {
        super(context);
    }

    public Practice16TextPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice16TextPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(120);

        // 使用 Paint.getTextPath() 来获取文字的 Path

        /**
         * 返回指定文本的路径（大纲）。
         * 注意: 就像Canvas.drawText，这将尊重对齐设置
         * @param 输入要从中检索路径的文本
         * @param 开始文本中的第一个字符
         * @param 结束1超过文本中的最后一个字符
         * @param x 文本原点的x坐标   左下角是文本的原点
         * @param y 文本原点的y坐标
         * @param path 接收描述文本的数据的路径,也就是接收输出值
         */
        pathPaint.setTextSize(120);
        pathPaint.getTextPath(text,0,text.length(),50,600,textPath);
        pathPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(text, 50, 200, paint);

        canvas.drawPath(textPath, pathPaint);
    }
}
