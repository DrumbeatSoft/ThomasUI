package com.thomas.ui;

import android.content.Context;

import com.thomas.ui.photo.PhotoPreview;

import java.util.List;

public class ThomasPreview {

    public static void showPreview(Context context, List<String> pictures) {
        PhotoPreview.Builder builder = new PhotoPreview.Builder(context);
        builder.build().showPopupWindow();
    }
}
