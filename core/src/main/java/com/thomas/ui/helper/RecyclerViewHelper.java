package com.thomas.ui.helper;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHelper {

    public static LinearLayoutManager getDefaultLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    public static LinearLayoutManager getDefaultLayoutManager(Context context, @RecyclerView.Orientation int orientation) {
        return new LinearLayoutManager(context, orientation, false);
    }

    public static ThomasDividerItemDecoration getDefaultItemDecoration(Context context) {
        return new ThomasDividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    public static ThomasDividerItemDecoration getDefaultItemDecoration(Context context, @RecyclerView.Orientation int orientation) {
        return new ThomasDividerItemDecoration(context, orientation);
    }
}
