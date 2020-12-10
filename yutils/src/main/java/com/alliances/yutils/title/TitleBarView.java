package com.alliances.yutils.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alliances.yutils.R;
import com.alliances.yutils.utils.TextViewUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义标题栏
 */
@SuppressWarnings("deprecation")
public class TitleBarView extends LinearLayout {
    private Context mContext;
    private FrameLayout mBackgroud;
    private LinearLayout mLeft;
    private RelativeLayout mRight;
    private TextView mLeftTxt;
    private ImageView mLeftImg;
    private TextView mTitle;
    private ImageView mTitleImg;
    private ImageView mRightImg;
    private TextView mRightTxt;
    private Set<View> mViewSet = new HashSet<View>();
    private OnLeftClickListener mLeftClickListener;
    private OnRightClickListener mRightClickListener;
    private String titleStr, leftStr, rightStr;
    private int leftDrawable, rightDrawable;
    private Drawable mLeftDrawable;
    private int bgColor;
    private static int titleColor;
    private static int leftColor, rightColor;
    private int leftTextSize, rightTextSize, titleTextSize;


    public TitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
        setListener();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        InitStyleAndViews(context, attrs);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        InitStyleAndViews(context, attrs);
    }

    private void InitStyleAndViews(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        titleStr = ta.getString(R.styleable.TitleBarView_title_text);

        leftStr = ta.getString(R.styleable.TitleBarView_left_text);
        rightStr = ta.getString(R.styleable.TitleBarView_right_text);
        leftDrawable = ta.getResourceId(R.styleable.TitleBarView_leftDrawable, R.drawable.back2x);
        rightDrawable = ta.getResourceId(R.styleable.TitleBarView_rightDrawable, R.color.white);
        mLeftDrawable = ta.getDrawable(R.styleable.TitleBarView_leftDrawable);
        //背景颜色-左右-title 颜色
        bgColor = ta.getColor(R.styleable.TitleBarView_titleBackgroundColor, Color.WHITE);
        titleColor = ta.getColor(R.styleable.TitleBarView_title_text_color, Color.BLACK);
        leftColor = rightColor = ta.getColor(R.styleable.TitleBarView_left_right_text_color, Color.parseColor("#80000000"));
        //将Text大小固定
        titleTextSize = 16;
        leftTextSize = rightTextSize = ta.getInt(R.styleable.TitleBarView_left_right_text_color, 16);
        initView();
        setListener();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.title_bar_common, this);
        mBackgroud = (FrameLayout) findViewById(R.id.title_bar_view_layout);
        mLeft = (LinearLayout) findViewById(R.id.titlebar_left_layout);
        mLeftTxt = (TextView) findViewById(R.id.titlebar_left_txt);
        mLeftImg = (ImageView) findViewById(R.id.title_left_img);

        mTitle = (TextView) findViewById(R.id.titlebar_title);
        mTitleImg = (ImageView) findViewById(R.id.titlebar_title_img);

        mRight = (RelativeLayout) findViewById(R.id.titlebar_right_layout);
        mRightImg = (ImageView) findViewById(R.id.titlebar_right_img);
        mRightTxt = (TextView) findViewById(R.id.titlebar_right_txt);

        mLeftTxt.setText(leftStr);
        mLeftTxt.setTextSize(leftTextSize);
        mLeftTxt.setTextColor(leftColor);

        mRightTxt.setText(rightStr);
        mRightTxt.setTextSize(rightTextSize);
        mRightTxt.setTextColor(rightColor);

        mTitle.setText(titleStr);
        mTitle.setTextSize(titleTextSize);
        mTitle.setTextColor(titleColor);

        try {
            mLeftImg.setImageBitmap(BitmapFactory.decodeResource(getResources(), leftDrawable));
            mRightImg.setImageBitmap(BitmapFactory.decodeResource(getResources(), rightDrawable));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBackgroud.setBackgroundColor(bgColor);


        mViewSet.add(mLeftTxt);
        mViewSet.add(mLeftImg);
        mViewSet.add(mTitle);
        mViewSet.add(mTitleImg);
        mViewSet.add(mRightImg);
        mViewSet.add(mRightTxt);
    }

    private void setListener() {

        mRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightClickListener != null) {
                    mRightClickListener.onClick();
                }
            }
        });
        mLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftClickListener != null) {

                    mLeftClickListener.onClick();
                }
            }
        });
    }

    /**
     * 初始化titlebar样式
     *
     * @param style
     */
    public void initStyle(TitleBarStyle style) {
        switch (style) {
            /* ------------------------------- */
            case OnlyTitle:
                setViewsVisibleAndOtherGone(mTitle);
                break;

            case LeftTxt_Title:
                setViewsVisibleAndOtherGone(mLeftTxt, mTitle);
                break;
            case Title_RightTxt:
                setViewsVisibleAndOtherGone(mTitle, mRightTxt);
                break;

            case LeftTxt_Title_RightTxt:
                setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mRightTxt);
                break;
            case LeftTxt_Title_TitleImg_RightTxt:
                setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mTitleImg, mRightTxt);
                break;

            /* ------------------------------- */
            case LeftImg_Title:
                setViewsVisibleAndOtherGone(mLeftImg, mTitle);
                break;
            case Title_RightImg:
                setViewsVisibleAndOtherGone(mTitle, mRightImg);
                break;
            case LeftImg_Title_RightImg:
                setViewsVisibleAndOtherGone(mLeftImg, mTitle, mRightImg);
                break;
            case LeftTxt_Title_RightImg:
                setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mRightImg);
                break;
            case LeftImg_Title_RightTxt:
                setViewsVisibleAndOtherGone(mLeftImg, mTitle, mRightTxt);
                break;
            case LeftImg_RightImg:
                setViewsVisibleAndOtherGone(mLeftImg, mRightImg);
                break;
            case LeftImg:
                setViewsVisibleAndOtherGone(mLeftImg);
                break;
            case RightImg:
                setViewsVisibleAndOtherGone(mRightImg);
                break;
            case LeftImgTxt_Title:
                setViewsVisibleAndOtherGone(mLeftImg, mLeftTxt, mTitle);
                break;
        }
    }

    /**
     * 隐藏或显示所有的View
     *
     * @param isVisiable
     */
    private void setAllViewsVisibleOrGone(boolean isVisiable) {
        for (View view : mViewSet) {
            view.setVisibility(isVisiable ? VISIBLE : GONE);
        }

    }

    /**
     * 设置相关Views显示，除此之外Views隐藏
     *
     * @param targetViews
     */
    private void setViewsVisibleAndOtherGone(View... targetViews) {
        for (View view : mViewSet) {
            List<View> targetViewList = Arrays.asList(targetViews);

            // 判断View是否在targetViews目标集合中
            if (view instanceof ImageView) {
                Log.d("TitleBarView", targetViewList.contains(view) + "");
            }
            if (targetViewList.contains(view)) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 只有标题
     *
     * @param resId
     */
    public void initOnlyTitle(int resId) {
        initStyle(TitleBarStyle.OnlyTitle);
        setTitle(resId);
    }

    /**
     * 只有标题
     *
     * @param txt
     */
    public void initOnlyTitle(String txt) {
        initStyle(TitleBarStyle.OnlyTitle);
        setTitle(txt);
    }

    /**
     * @param title   标题
     * @param leftTxt 左边文字
     */
    public void initTitleWithLeftTxt(String title, String leftTxt) {
        initStyle(TitleBarStyle.LeftTxt_Title);
        setTitle(title);
        setLeftTxt(leftTxt);
    }

    /**
     * @param title              标题
     * @param leftTxt            左边文字
     * @param leftTxtDrawableRes 利用TextView的Drawable属性，设置“左边文字”的左侧Drawable
     * @param padding            “左边文字”的左侧Drawable的padding
     */
    public void initTitleWithLeftTxtDrawable(String title, String leftTxt, int leftTxtDrawableRes, int padding) {
        initStyle(TitleBarStyle.LeftTxt_Title);
        setTitle(title);
        setLeftTxt(leftTxt);
        TextViewUtils.setLeftImage(mLeftTxt, leftTxtDrawableRes, padding);
    }

    /**
     * @param title    标题
     * @param rightTxt 右边文字
     */
    public void initTitleWithRightTxt(String title, String rightTxt) {
        initStyle(TitleBarStyle.Title_RightTxt);
        setTitle(title);
        setRightTxt(rightTxt);
    }

    /**
     * @param title               标题
     * @param rightTxt            右边文字
     * @param rightTxtDrawableRes 利用TextView的Drawable属性，设置“右边文字”的左侧Drawable
     * @param padding             “右边文字”的左侧Drawable的padding
     */
    public void initTitleWithRightTxtDrawable(String title, String rightTxt, int rightTxtDrawableRes, int padding) {
        initStyle(TitleBarStyle.Title_RightTxt);
        setTitle(title);
        setRightTxt(rightTxt);
        TextViewUtils.setLeftImage(mRightTxt, rightTxtDrawableRes, padding);
    }

    /**
     * @param title    标题
     * @param leftTxt  左边文字
     * @param rightTxt 右边文字
     */
    public void initTitleWithLeftTxtRightTxt(String title, String leftTxt, String rightTxt) {
        initStyle(TitleBarStyle.LeftTxt_Title_RightTxt);
        setLeftTxt(leftTxt);
        setTitle(title);
        setRightTxt(rightTxt);
    }

    /**
     * 不用标题
     * mLeftImg左侧图片
     * mRightImg右侧图片
     */
    public void initTitleWithLeftImgRightImg(int leftImg, int rightImg) {
        initStyle(TitleBarStyle.LeftImg_RightImg);
        setLeftImg(leftImg);
        setRightImg(rightImg);
    }

    /**
     * @param title      标题
     * @param titleImgId 标题图片
     * @param leftTxt    左边文字
     * @param rightTxt   右边文字
     */
    public void setTitleTitleImgWithLeftTxtRightTxt(String title, int titleImgId, String leftTxt, String rightTxt) {
        initStyle(TitleBarStyle.LeftTxt_Title_TitleImg_RightTxt);
        setLeftTxt(leftTxt);
        setTitle(title);
        setTitleImg(titleImgId);
        setRightTxt(rightTxt);
    }

    /**
     * @param title     标题
     * @param leftImgId 左边图片
     */
    public void initTitleWithLeftImg(String title, int leftImgId) {
        initStyle(TitleBarStyle.LeftImg_Title);
        setTitle(title);
//        setLeftImg(leftImgId);
    }

    /**
     * @param title      标题
     * @param rightImgId 右边图片
     */
    public void initTitleWithRightImg(String title, int rightImgId) {
        initStyle(TitleBarStyle.Title_RightImg);
        setTitle(title);
        setRightImg(rightImgId);
    }

    /**
     * @param title      标题
     * @param leftImgId  左边图片
     * @param rightImgId 右边图片
     */
    public void initTitleWithLeftImgRightImg(String title, int leftImgId, int rightImgId) {
        initStyle(TitleBarStyle.LeftImg_Title_RightImg);
//        setLeftImg(leftImgId);
        setTitle(title);
        setRightImg(rightImgId);
    }

    /* ---start----------------------------------------------- */

    /**
     * @param title      标题
     * @param leftTxt    左边文字
     * @param rightImgId 右边图片
     */
    public void initTitleWithLeftTxtRightImg(String title, String leftTxt, int rightImgId) {
        initStyle(TitleBarStyle.LeftTxt_Title_RightImg);
        setLeftTxt(leftTxt);
        setTitle(title);
        setRightImg(rightImgId);
    }

    /**
     * @param title     标题
     * @param leftImgId 左边图片
     * @param rightTxt  右边文字
     */
    public void initTitleWithLeftImgRightTxt(String title, int leftImgId, String rightTxt) {
        initStyle(TitleBarStyle.LeftImg_Title_RightTxt);
//        setLeftImg(leftImgId);
        setTitle(title);
        setRightTxt(rightTxt);
    }
    /* ---end----------------------------------------------- */

    public TextView getLeftTxtView() {
        return this.mLeftTxt;
    }


    public TextView getTitleView() {
        return this.mTitle;
    }

    public ImageView getTitleImgView() {
        return this.mTitleImg;
    }

    public TextView getRightTxtView() {
        return this.mRightTxt;
    }

    public ImageView getRightImgView() {
        return this.mRightImg;
    }

    @Override
    public void setBackgroundResource(int resid) {
        setTitleBarBackground(resid);
    }

    /**
     * 设置TitleBar背景
     *
     * @param resId
     */
    public void setTitleBarBackground(int resId) {
        mBackgroud.setBackgroundResource(resId);
    }

    /**
     * 设置TitleBar背景
     *
     * @param resId
     */
    public void setTitleBackGroundColor(int resId) {
        mBackgroud.setBackgroundColor(resId);
    }

    public void setLeftTxt(int resId) {
        mLeftTxt.setText(resId);
    }

    public void setLeftTxt(String txt) {
        mLeftTxt.setText(txt);
    }

    public void setLeftImg(int resId) {
        mLeftImg.setImageResource(resId);
    }

    public void setLeftTextSize(int size) {
        mLeftTxt.setTextSize(size);
    }

    public void setLeftTextColor(int color) {
        mLeftTxt.setTextColor(color);
    }

    public void setRightImg(int resId) {
        mRightImg.setImageResource(resId);
    }

    public void setRightTxt(int resId) {
        mRightTxt.setText(resId);
    }

    public void setRightTxt(String txt) {
        mRightTxt.setText(txt);
    }

    public void setRightVisible(int visible) {
        mRight.setVisibility(visible);
    }

    public void setRightTxtSize(int size) {
        mRightTxt.setTextSize(size);
    }

    public void setRightTxtColor(int color) {
        mRightTxt.setTextColor(color);
    }

    public void setTitle(int txtRes) {
        mTitle.setText(txtRes);
    }

    public void setTtitleColor(int colorRes) {
        if (mTitle != null) {
            mTitle.setTextColor(colorRes);
        }
    }

    public void setTitleSize(int size) {
        mTitle.setTextSize(size);
    }

    public void setTitle(String txt) {
        mTitle.setText(txt);
    }


    /**
     * 设置title右边的图片
     *
     * @param resId
     */
    public void setTitleImg(int resId) {
        mTitleImg.setImageResource(resId);
    }

    /**
     * 左右点击事件
     *
     * @param onLeft
     * @param onRight
     */
    public void setOnLeftandRightClick(OnLeftClickListener onLeft, OnRightClickListener onRight) {
        this.mLeftClickListener = onLeft;
        this.mRightClickListener = onRight;
    }

    /**
     * 左边点击事件
     *
     * @param listener
     */
    public void setOnLeftClickListener(
            OnLeftClickListener listener) {
        mLeftClickListener = listener;
    }

    /**
     * 右边点击事件
     *
     * @param listener
     */
    public void setOnRightClickListener(OnRightClickListener listener) {
        mRightClickListener = listener;
    }

    public interface OnLeftClickListener {
        void onClick();
    }


    public interface OnRightClickListener {
        void onClick();
    }
}
