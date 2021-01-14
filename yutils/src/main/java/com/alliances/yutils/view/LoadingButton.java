package com.alliances.yutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.alliances.yutils.R;
import com.alliances.yutils.utils.LocalDisplay;


public class LoadingButton extends AppCompatButton {
    private int loadingMode = 1;
    private int loadingColor;
    private int normalColor;
    private int mCornerRadius = 0;
    private int mProgressMargin = 0;
    private int mMinProgress = 0;
    private int mProgress = 0;
    private int mMaxProgress = 100;

    private GradientDrawable mDrawableProgress;
    private GradientDrawable mDrawableNormal;
    private GradientDrawable mDrawableComplete;


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
        mCornerRadius = ta.getInteger(R.styleable.LoadingButton_corner_radius, 100);
        init();
    }

    private void init() {


        mDrawableProgress = new GradientDrawable();
        mDrawableProgress.setCornerRadius(mCornerRadius);

        mDrawableNormal = new GradientDrawable();
        mDrawableNormal.setCornerRadius(mCornerRadius);
        mDrawableNormal.setStroke(LocalDisplay.dp2px(getContext(), 1), getResources().getColor(R.color.mercury));


        mDrawableComplete = new GradientDrawable();
        mDrawableComplete.setCornerRadius(mCornerRadius);

        mDrawableProgress.setColor(loadingColor);
        mDrawableComplete.setColor(loadingColor);
        mDrawableNormal.setColor(normalColor);

        setBackgroundDrawable(mDrawableNormal);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float progressWidth =
                (float) getMeasuredWidth() * ((float) (mProgress - mMinProgress) / mMaxProgress - mMinProgress);
        if (loadingMode == 1) {
            mDrawableProgress.setBounds((int) mProgressMargin, (int) mProgressMargin,
                    (int) (progressWidth - mProgressMargin), getMeasuredHeight() - (int) mProgressMargin);

            if (mProgress != 0 && mProgress == 50) {
                setTextColor(Color.WHITE);
            }
            //Draw progress
            mDrawableProgress.draw(canvas);
        }
        super.onDraw(canvas);
    }

    /**
     * @param progress
     */
    public void setProgress(int progress) {
        this.mProgress = progress;
        setText(mProgress + "%");
        invalidate();
    }


}
