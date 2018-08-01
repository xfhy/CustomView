package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice01LinearGradientView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice01LinearGradientView(Context context) {
        super(context);
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
     * 构造代码块：直接在类中定义且没有加static关键字的代码块称为{}构造代码块。
     * 构造代码块在创建对象时被调用，每次创建对象都会被调用，并且构造代码块的执行次序优先于类构造函数。
     * 如果存在多个构造代码块，执行顺序由他们在代码中出现的次序决定，先出现先执行。
     */
    {
        // 用 Paint.setShader(shader) 设置一个 LinearGradient  LinearGradient是线性的
        // LinearGradient 的参数：坐标：(100, 100) 到 (500, 500) ；颜色：#E91E63 到 #2196F3
        paint.setShader(new LinearGradient(100, 100, 500, 500,
                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(300, 300, 200, paint);
    }
}
