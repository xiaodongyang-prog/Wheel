package com.alliances.yutils.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class TextViewUtils {
    /**
     * 获取TextView的高度
     *
     * @param context
     * @param text
     * @param textSize
     * @param deviceWidth
     * @param typeface
     * @param padding
     * @return
     */
    public static int getHeight(Context context, CharSequence text, int textSize, int deviceWidth, Typeface typeface, int padding) {
        TextView textView = new TextView(context);
        textView.setPadding(padding, 0, padding, padding);
        textView.setTypeface(typeface);
        textView.setText(text, TextView.BufferType.SPANNABLE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    /**
     * 设置左侧图片
     *
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setLeftImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置左侧图片
     *
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setLeftImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置顶部图片
     *
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setTopImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }

    /**
     * 设置顶部图片
     *
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setTopImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }

    /**
     * 设置右侧图片
     *
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setRightImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置右侧图片
     *
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setRightImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置底部图片
     *
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setBottomImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }

    /**
     * 设置底部图片
     *
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setBottomImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(LocalDisplay.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }

    public static void drawMidLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public static void undrawMidLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | ~Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public static void drawUnderLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void undrawUnderLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | ~Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void setBold(TextView textView, boolean work) {
        int flags = textView.getPaintFlags();
        if (work) {
            textView.setPaintFlags(flags | Paint.FAKE_BOLD_TEXT_FLAG);
        } else {
            textView.setPaintFlags(flags | ~Paint.FAKE_BOLD_TEXT_FLAG);
        }
    }

    public static void setMaxLength(TextView textView, int length) {
        InputFilter[] inputFilters = textView.getFilters();
        ArrayList<InputFilter> inputFilterArray = new ArrayList<InputFilter>();

        if (inputFilters != null) {
            for (int i = 0; i < inputFilters.length; i++) {
                InputFilter inputFilter = inputFilters[i];

                if (!(inputFilter instanceof LengthFilter))
                    inputFilterArray.add(inputFilter);
            }

        }
        inputFilterArray.add(new LengthFilter(length));
        textView.setFilters(inputFilterArray.toArray(new InputFilter[0]));
    }
    /************* 2019-06-11 14:55**********************/
    /**
     * 获取文字的宽度
     */
    public static float getTextWidth(String text, TextPaint paint, int size) {
        if (text == null || text.isEmpty()) {
            return 0f;
        }
        paint.setTextSize(size);
        return paint.measureText(text);
    }

    /**
     * 获取文字的高度
     */
    public static float getTextHeight(TextPaint paint, int size) {
        paint.setTextSize(size);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }

    /**
     * 获取文字的bottom
     */
    public static float getTextBottom(TextPaint paint, int size) {
        paint.setTextSize(size);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom;
    }

    /**
     * 给定文字的top获取文字的base line
     */
    public static float getTextBaseLineByTop(float top, TextPaint paint, int size) {
        paint.setTextSize(size);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return top - fontMetrics.top;
    }

    /**
     * 给定文字的bottom获取文字的base line
     */
    public static float getTextBaseLineByBottom(float bottom, TextPaint paint, int size) {
        paint.setTextSize(size);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return bottom - fontMetrics.bottom;
    }

    /**
     * 给定文字的center获取文字的base line
     */
    public static float getTextBaseLineByCenter(float center, TextPaint paint, int size) {
        paint.setTextSize(size);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float height = fontMetrics.bottom - fontMetrics.top;
        return center + height / 2 - fontMetrics.bottom;
    }

    public static float getTextBaseLineByCenter(float center, TextPaint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float height = fontMetrics.bottom - fontMetrics.top;
        return center + height / 2 - fontMetrics.bottom;
    }





}