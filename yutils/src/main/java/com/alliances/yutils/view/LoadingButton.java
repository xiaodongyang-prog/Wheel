package com.alliances.yutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;

import com.alliances.yutils.R;
import com.alliances.yutils.utils.LocalDisplay;


public class LoadingButton extends View {
    private int loadingMode = 1;
    private int loadingColor;
    private int normalColor;
    private int mProgressMargin = 0;
    private int mProgress = 0;
    private int mMaxProgress = 100;

    private Paint textPaint;
    private Paint mPaint;

    public LoadingButton(Context context) {
        super(context);
    }

    public LoadingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);
    }

    public LoadingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * @param context
     * @param attrs
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        loadingMode = ta.getInteger(R.styleable.LoadingButton_loading_mode, 1);
        loadingColor = ta.getColor(R.styleable.LoadingButton_loading_color, context.getResources().getColor(R.color.dodger_blue));
        normalColor = ta.getColor(R.styleable.LoadingButton_normal_color, Color.WHITE);
        initPaint();
    }

    /**
     * 初始化画笔
     * @param
     */
    private void initPaint() {
        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(R.color.white));
        textPaint.setTextSize(LocalDisplay.dip2px(getContext(), 12));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mPaint.setTextSize(LocalDisplay.dip2px(getContext(), 12));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.dodger_blue));
        RectF baseRect = new RectF(mProgressMargin, mProgressMargin,
                getWidth(), getHeight());
        canvas.drawRoundRect(baseRect, getHeight() / 2, getHeight() / 2, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(new RectF(1, 1,
                getWidth() - 1, getHeight() - 1), getHeight() / 2, getHeight() / 2, mPaint);

        float section = Float.parseFloat(String.valueOf(mProgress)) / Float.parseFloat(String.valueOf(mMaxProgress));
        float progressSection = section * getWidth();
        mPaint.setColor(getResources().getColor(R.color.dodger_blue));

        if (loadingMode == 1) {
            Path path = new Path();
            //绘制进度
            //绘制左边半圆进度
            float swipeAngle = 180;
            if (progressSection < getHeight()) {
                swipeAngle = progressSection / getHeight() * 180;
            }
            path.addArc(new RectF(0, 0, getHeight(), getHeight()), 180 - swipeAngle / 2, swipeAngle);
            //绘制进度条
            if (progressSection > getHeight()) {
                path.addRect(new RectF(getHeight() / 2, 0, progressSection - getHeight() / 2, getHeight()), Path.Direction.CW);
            }

            if (mProgress == mMaxProgress) {
                canvas.drawCircle(getWidth() - getHeight() / 2, getHeight() / 2, getHeight() / 2, mPaint);
            }

            canvas.drawPath(path, mPaint);

            //终点的圆
            mPaint.setColor(getResources().getColor(R.color.concrete_solid));
            canvas.drawCircle(getWidth() - getHeight() / 2, getHeight() / 2, getHeight() / 2 - 1, mPaint);
            mPaint.setColor(getResources().getColor(R.color.dodger_blue));
            canvas.drawCircle(getWidth() - getHeight() / 2, getHeight() / 2, getHeight() / 2 - 3, mPaint);

            //drawText - Paint
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            RectF textRect = new RectF(getWidth() - getHeight(), 0, getWidth(), getHeight());
            float baseline = textRect.centerY() + distance;
            canvas.drawText(mProgress + "%", getWidth() - getHeight() / 2, baseline, textPaint);


            if (mProgress >= 1) {
                mPaint.setColor(getResources().getColor(R.color.dodger_blue));
                if (mProgress >= mMaxProgress / 2) {
                    mPaint.setColor(Color.WHITE);
                }
                RectF loadRect = new RectF(0,0,getWidth()-getHeight(),getHeight());
                canvas.drawText(getResources().getString(R.string.download_new_apk), loadRect.centerX(), baseline, mPaint);
            }
        }
        super.onDraw(canvas);
    }

    /**
     * @param progress
     */
    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }
}
