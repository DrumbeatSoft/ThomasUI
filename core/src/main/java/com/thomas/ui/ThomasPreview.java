package com.thomas.ui;

import android.content.Context;

import com.thomas.ui.popup.PhotoPreview;

import java.util.List;

public class ThomasPreview {

    public static void showPreview(Context context, List<String> pictures) {
        showPreview(context, 0, pictures);
    }

    public static void showPreview(Context context, String picture) {
        PhotoPreview.Builder builder = new PhotoPreview.Builder(context);
        builder.setPictures(picture).build().showPopupWindow();
    }

    public static void showPreview(Context context, int position, List<String> pictures) {
        PhotoPreview.Builder builder = new PhotoPreview.Builder(context);
        builder.setPictures(pictures).setCurrent(position).build().showPopupWindow();
    }
}
