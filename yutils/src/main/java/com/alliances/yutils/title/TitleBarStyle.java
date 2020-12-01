package com.alliances.yutils.title;

/**
 * Created by z0324 on 2017/7/24.
 */

public enum TitleBarStyle {
    OnlyTitle, /**
     * 中间标题
     */

    LeftTxt_Title, /**
     * 左边文字，中间标题
     */
    Title_RightTxt, /**
     * 中间标题，右边文字
     */
    LeftTxt_Title_RightTxt, /**
     * 左边文字，中间标题，右边文字
     */
    LeftTxt_Title_TitleImg_RightTxt, /**
     * 左边文字，中间标题和标题的图片，右边文字
     */

    LeftImg_Title, /**
     * 左边图片，中间标题
     */
    Title_RightImg, /**
     * 中间标题，右边图片
     */
    LeftImg_Title_RightImg,
    LeftTxt_Title_RightImg,
    LeftImg_Title_RightTxt,
    /**
     * LeftTxt_Img_title
     */
    LeftImgTxt_Title,
    /**
     * LeftImg_RightImg
     */
    LeftImg_RightImg,
    /**
     * 只有左边图片
     */
    LeftImg,
    /**
     * 只有右边图片
     */
    RightImg;

}