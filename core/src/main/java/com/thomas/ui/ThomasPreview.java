package com.thomas.ui;

import android.content.Context;

import com.thomas.ui.popup.PhotoPreview;

import java.util.List;

public class ThomasPreview {

    /**
     * 多张图片预览
     *
     * @param context
     * @param pictures
     */
    public static void showPreview(Context context, List<String> pictures) {
        showPreview(context, 0, pictures);
    }

    /**
     * 单个图片预览
     *
     * @param context
     * @param picture
     */
    public static void showPreview(Context context, String picture) {
        PhotoPreview.Builder builder = new PhotoPreview.Builder(context);
        builder.setPictures(picture).build().showPopupWindow();
    }

    /**
     * 图片预览，有初始位置
     *
     * @param context
     * @param position
     * @param pictures
     */
    public static void showPreview(Context context, int position, List<String> pictures) {
        PhotoPreview.Builder builder = new PhotoPreview.Builder(context);
        builder.setPictures(pictures).setCurrent(position).build().showPopupWindow();
    }
}
