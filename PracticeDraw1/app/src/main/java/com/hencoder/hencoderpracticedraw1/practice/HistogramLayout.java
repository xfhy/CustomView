package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.HistogramModel;
import com.hencoder.hencoderpracticedraw1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图
 */
public class HistogramLayout extends View {

    /*
     * 矩形颜色:ColumnColor
     * 文字颜色:TextColor
     * 存放横排数据:List存放  model:名称+数据(大小)
     *
     * 计算屏幕宽度->结合自身高度,计算每个柱子的高度.最高那根假设占整个高度的90%.
     * ->间距,计算柱子宽度->根据间距画矩形,然后算出下一个矩形的左下角坐标->将文字画在横线下边,需要计算文字高度
     *
     * */

    /**
     * 默认柱状图颜色
     */
    private final static int DEFAULT_COLUMN_COLOR = 0xFF000000;
    /**
     * 默认文字颜色
     */
    private final static int DEFAULT_TEXT_COLOR = 0xFFFFFFFF;
    /**
     * 间距
     */
    private final static int DEFAULT_MARGIN = 20;
    /**
     * 坐标系线条颜色
     */
    private static final int DEFAULT_COORDINATE_COLOR = 0xFFFFFFFF;
    /**
     * 文字大小
     */
    private static final float DEFAULT_TEXT_SIZE = 20;
    /**
     * 默认坐标系的线条宽度
     */
    private static final int DEFAULT_COORDINATE_WIDTH = 2;

    private int mColumnColor;
    private int mTextColor;

    private int mWidth;
    private int mHeight;

    private List<HistogramModel> mHistogramModelList = new ArrayList<>(5);

    //文字,矩形,坐标  画笔
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCoordinatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 文字高度
     */
    private float mTextHeight;
    /**
     * 最高的矩形的高度
     */
    private double mHishestColumnHeight = 0.0;
    /**
     * 最高矩形的大小
     */
    private int mHishestColumnSize = 0;

    public HistogramLayout(Context context) {
        this(context, null);
    }

    public HistogramLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistogramLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HistogramLayout);
        mColumnColor = typedArray.getColor(R.styleable.HistogramLayout_ColumnColor, DEFAULT_COLUMN_COLOR);
        mTextColor = typedArray.getColor(R.styleable.HistogramLayout_TextColor, DEFAULT_TEXT_COLOR);
        typedArray.recycle();

        initView();
    }

    private void initView() {
        mRectPaint.setColor(mColumnColor);
        mRectPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(DEFAULT_TEXT_SIZE);
        mCoordinatePaint.setColor(DEFAULT_COORDINATE_COLOR);
        mCoordinatePaint.setStrokeWidth(DEFAULT_COORDINATE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mHishestColumnHeight = (h - getPaddingBottom() - getPaddingTop()) * 0.8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        drawCoordinate(canvas);
        drawRectAndText(canvas);

    }

    /**
     * 画坐标系
     */
    private void drawCoordinate(Canvas canvas) {
        //画竖坐标                                          总高度-文字高度-线条高度-底部padding
        canvas.drawLine(getPaddingStart(), getPaddingTop(), getPaddingStart(),
                mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom(), mCoordinatePaint);
        //画横线
        canvas.drawLine(getPaddingStart(), mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom(),
                mWidth - getPaddingEnd(), mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom(), mCoordinatePaint);
    }

    /**
     * 画文字和矩形
     */
    private void drawRectAndText(Canvas canvas) {
        //空数据,不画
        if (mHistogramModelList == null || mHistogramModelList.size() == 0 || mHistogramModelList.get(0) == null) {
            return;
        }

        //实际可用的横向区域长度
        int realWidth = mWidth - getPaddingEnd() - getPaddingStart();
        //矩形个数
        int columnCount = mHistogramModelList.size();
        //间距个数==矩形个数+1
        int spacing = columnCount + 1;
        //单个矩形实际宽度== (实际横向区域宽度-所有间距的长度)/矩形个数
        int realColumnWidth = (realWidth - spacing * DEFAULT_MARGIN) / columnCount;
        //先计算出最高矩形的高度->总高度*0.8->其他矩形高度:自己size/最高那个的size*最高那个的高度

        int columnX = 0;
        Rect rect = new Rect();
        //现在可以开始循环了
        for (int i = 0; i < columnCount; i++) {
            HistogramModel histogramModel = mHistogramModelList.get(i);
            float thisHeight = (float) (histogramModel.size * 1.0 / mHishestColumnSize * mHishestColumnHeight);
            if (i == 0) {
                columnX = getPaddingStart() + DEFAULT_COORDINATE_WIDTH + DEFAULT_MARGIN;
                //画矩形
                canvas.drawRect(columnX, mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom() - thisHeight,
                        columnX + realColumnWidth
                        , mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom(), mRectPaint);
            } else {
                columnX = columnX + realColumnWidth + DEFAULT_MARGIN;
                canvas.drawRect(columnX, mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom() - thisHeight,
                        columnX + realColumnWidth,
                        mHeight - mTextHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom(), mRectPaint);
            }
            mTextPaint.getTextBounds(histogramModel.name, 0, histogramModel.name.length(), rect);
            int textWidth = rect.width();
            //int textHeight = rect.height();
            canvas.drawText(histogramModel.name, columnX + realColumnWidth / 2 - textWidth / 2,
                    mHeight - DEFAULT_COORDINATE_WIDTH - getPaddingBottom(), mTextPaint);
        }

        //画文字
        //画矩形
    }


    public void setHistogramModelList(List<HistogramModel> histogramModelList) {
        mHistogramModelList = histogramModelList;
        if (mHistogramModelList != null && mHistogramModelList.size() > 0 &&
                mHistogramModelList.get(0) != null && !TextUtils.isEmpty(mHistogramModelList.get(0).name)) {
            //文字高度
            Rect bounds = new Rect();
            mTextPaint.getTextBounds(mHistogramModelList.get(0).name, 0, mHistogramModelList.get(0).name.length(), bounds);
            mTextHeight = bounds.height();
            //最高矩形的高度
            for (HistogramModel histogramModel : mHistogramModelList) {
                if (histogramModel.size > mHishestColumnSize) {
                    mHishestColumnSize = histogramModel.size;
                }
            }
        }

        invalidate();
    }
}
