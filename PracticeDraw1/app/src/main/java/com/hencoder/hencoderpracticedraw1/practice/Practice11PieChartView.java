package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.CircleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018年7月28日09:04:37
 * <p>
 * 圆饼图
 */
public class Practice11PieChartView extends View {

    private static final int DEFAULT_TOP_BOTTOM_PADDING = 30;
    private static final float DEFAULT_LINE_WIDTH = 3;
    private List<CircleModel> mCircleModelList = new ArrayList<>();
    private Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 圆形半径
     */
    private static final int RADIUS = 200;
    /**
     * 圆形区域
     */
    private RectF mCircleRectF;
    private static final float LINE_LENGTH = 50;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mLinePaint.setStrokeWidth(DEFAULT_LINE_WIDTH);
        mLinePaint.setColor(Color.WHITE);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(12);
        //圆形区域
        mCircleRectF = new RectF(-RADIUS, -RADIUS, RADIUS, RADIUS);
        getTestData();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        int size = mCircleModelList.size();

        canvas.translate(getWidth() / 2, getHeight() / 2);// 画布移到中心

        float startAngle = 0;

        for (int i = 0; i < size; i++) {
            CircleModel circleModel = mCircleModelList.get(i);

            // 扇形的中心线相对于坐标系的角度(0~2π)
            double theta = (startAngle + circleModel.angle / 2) * Math.PI / 180;

            drawContent(canvas, startAngle, theta, circleModel);
            startAngle = startAngle + circleModel.angle;
        }
    }

    private void drawContent(Canvas canvas, float startAngle, double theta, CircleModel circleModel) {
        // 画扇形
        mArcPaint.setColor(circleModel.color);
        canvas.drawArc(mCircleRectF, startAngle, circleModel.angle, true, mArcPaint);

        // 画斜线
        float lineStartX = (float) (RADIUS * Math.cos(theta));
        float lineStartY = (float) (RADIUS * Math.sin(theta));
        float lineStopX = (float) ((RADIUS + LINE_LENGTH) * Math.cos(theta));
        float lineStopY = (float) ((RADIUS + LINE_LENGTH) * Math.sin(theta));
        canvas.drawLine(lineStartX, lineStartY, lineStopX, lineStopY, mLinePaint);

        // 画横线和文字
        float lineEndX;
        Rect r = getTextBounds(circleModel.name, mTextPaint);
        if (theta > Math.PI / 2 && theta <= Math.PI * 3 / 2) {// 左半边，往左画横线
            lineEndX = lineStopX - LINE_LENGTH;
            // 画线
            canvas.drawLine(lineStopX, lineStopY, lineEndX, lineStopY, mLinePaint);

            // 画文字
            canvas.drawText(circleModel.name, lineEndX - r.width() - 10, lineStopY + r.height() / 2, mTextPaint);
        } else {// 往右画横线
            lineEndX = lineStopX + LINE_LENGTH;
            // 画线
            canvas.drawLine(lineStopX, lineStopY, lineEndX, lineStopY, mLinePaint);

            // 文字
            canvas.drawText(circleModel.name, lineEndX + 10, lineStopY + r.height() / 2, mTextPaint);
        }
    }

    /**
     * 测量文字大小
     *
     * @param text
     * @param paint
     * @return
     */
    private Rect getTextBounds(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    public List<CircleModel> getCircleModelList() {
        return mCircleModelList;
    }

    public void setCircleModelList(List<CircleModel> circleModelList) {
        if (circleModelList == null) {
            return;
        }
        mCircleModelList = circleModelList;
        int allSize = 0;
        for (CircleModel circleModel : mCircleModelList) {
            allSize += circleModel.size;
        }
        //计算每个弧形所占角度
        for (CircleModel circleModel : mCircleModelList) {
            circleModel.angle = (float) (circleModel.size / (allSize * 1.0) * 360);
        }
        invalidate();
    }

    private void getTestData() {
        List<CircleModel> circleModels = new ArrayList<>();
        circleModels.add(new CircleModel("香蕉", 45, Color.parseColor("#FFE4C4")));
        circleModels.add(new CircleModel("苹果", 46, Color.parseColor("#FAEBD7")));
        circleModels.add(new CircleModel("草莓", 58, Color.parseColor("#EEA9B8")));
        circleModels.add(new CircleModel("菠萝", 25, Color.parseColor("#DAA520")));
        circleModels.add(new CircleModel("梨子", 10, Color.parseColor("#D1EEEE")));
        circleModels.add(new CircleModel("百香果", 3, Color.parseColor("#CD2626")));
        circleModels.add(new CircleModel("西瓜", 79, Color.parseColor("#B5B5B5")));
        mCircleModelList = circleModels;
        int allSize = 0;
        for (CircleModel circleModel : mCircleModelList) {
            allSize += circleModel.size;
        }
        //计算每个弧形所占角度
        for (CircleModel circleModel : mCircleModelList) {
            circleModel.angle = (float) (circleModel.size / (allSize * 1.0) * 360);
        }
    }

}
