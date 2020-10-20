package com.thomas.ui.quick;

import android.content.Context;
import android.view.Gravity;

import com.thomas.ui.dialog.BottomGridDialog;
import com.thomas.ui.dialog.BottomListDialog;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.List;

public class ThomasBottomSheet {


    /**
     * 展示一个底部菜单弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetNormal(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheetNormal(context, Gravity.CENTER, datas, onSingleClickListener);
    }

    /**
     * 展示一个带标题的底部菜单弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetWithTitle(Context context, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheetWithTitle(context, Gravity.CENTER, title, datas, onSingleClickListener);
    }


    /**
     * 展示一个底部菜单弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetNormal(Context context, int gravity, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheet(context, gravity, "", datas, onSingleClickListener);
    }


    /**
     * 展示一个带标题的底部菜单弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetWithTitle(Context context, int gravity, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheet(context, gravity, title, datas, onSingleClickListener);
    }

    private static void showBottomSheet(Context context, int gravity, String title, List datas, OnSingleClickListener onSingleClickListener) {
        BottomListDialog.Builder builder = new BottomListDialog.Builder(context);
        builder.setTitle(title).setGravity(gravity).setItems(datas).setOnItemClickListener(onSingleClickListener).build().showPopupWindow();
    }


    /**
     * 展示一个底部宫格弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomGrid(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        BottomGridDialog.Builder builder = new BottomGridDialog.Builder(context);
        builder.setItems(datas).setOnItemClickListener(onSingleClickListener).build().showPopupWindow();
    }

    /**
     * 展示一个底部宫格弹窗，自定义每行条目数
     * @param context
     * @param spanCount
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomGrid(Context context, int spanCount, List datas, OnSingleClickListener onSingleClickListener) {
        BottomGridDialog.Builder builder = new BottomGridDialog.Builder(context);
        builder.setSpanCount(spanCount).setItems(datas).setOnItemClickListener(onSingleClickListener).build().showPopupWindow();
    }
}
