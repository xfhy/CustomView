package com.xfhy.animator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by feiyang on 2018/8/30 16:59
 * Description : 百分之几+外圈
 * 可以加上属性动画,效果更炫酷
 * val objectAnimator = ObjectAnimator.ofInt(sportsView, "progress", 0, 63)
objectAnimator.duration = 1000
objectAnimator.start()
 *
 * 效果图:https://ws3.sinaimg.cn/large/006tKfTcgy1fj7y2vnw5jg30ek0dijwq.gif
 */
class SportsView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0)
    : View(context, attributeSet, defStyle) {

    companion object {
        private const val DEFAULT_FONT_SIZE = 100f
        private const val DEFAULT_ARC_WIDTH = 30f
        private const val DEFAULT_FONT_COLOR = 0xFF303F9F.toInt()
        private const val DEFAULT_ARC_COLOR = 0xFFFF4081.toInt()
    }

    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mArcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mWidth = 0
    private var mHeight = 0
    private var mArcRect: RectF? = null
    //public 的会自动生成getter 和 setter 方法
    var progress = 0
        set(value) {
            field = value
            invalidate()
        }
    private val mCenterPoint = Point(0, 0)

    init {
        mTextPaint.textSize = DEFAULT_FONT_SIZE
        mTextPaint.typeface = Typeface.DEFAULT_BOLD
        mTextPaint.color = DEFAULT_FONT_COLOR

        //设置线条端点为圆头
        mArcPaint.strokeCap = Paint.Cap.ROUND
        mArcPaint.color = DEFAULT_ARC_COLOR
        mArcPaint.strokeWidth = DEFAULT_ARC_WIDTH
        mArcPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        val halfWidth = DEFAULT_ARC_WIDTH / 2
        mArcRect = RectF(halfWidth, halfWidth, w - halfWidth, h - halfWidth)
        mCenterPoint.x = w / 2
        mCenterPoint.y = h / 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawArc(canvas)
        drawText(canvas)
    }

    private fun drawText(canvas: Canvas?) {
        val textContent = "$progress%"
        val fontMetrics = mTextPaint.fontMetrics
        //文字高度
        val textHeight = (fontMetrics.top + fontMetrics.bottom) / 2
        //文字宽度
        val textWidth = mTextPaint.measureText(textContent)
        canvas?.drawText(textContent, mCenterPoint.x - textWidth / 2, mCenterPoint.y - textHeight, mTextPaint)
    }

    private fun drawArc(canvas: Canvas?) {
        canvas?.drawArc(mArcRect, 135f, (progress / 100.0f) * 360, false, mArcPaint)
    }

}