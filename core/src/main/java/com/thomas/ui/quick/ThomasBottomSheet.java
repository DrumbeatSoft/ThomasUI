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
     * 展示一个带取消的底部菜单弹窗
     *
     * @param context
     * @param cancel
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetWithCancel(Context context, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheetWithCancel(context, Gravity.CENTER, cancel, datas, onSingleClickListener);
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
     * 展示一个带标题和取消的底部菜单弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetAll(Context context, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheetAll(context, Gravity.CENTER, title, datas, onSingleClickListener);
    }

    /**
     * 展示一个带标题和取消的底部菜单弹窗
     *
     * @param context
     * @param title
     * @param cancel
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetAll(Context context, String title, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheetAll(context, Gravity.CENTER, title, cancel, datas, onSingleClickListener);
    }


    /**
     * 展示一个底部菜单弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetNormal(Context context, int gravity, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheet(context, gravity, "", "", false, datas, onSingleClickListener);
    }

    /**
     * 展示一个带取消的底部菜单弹窗
     *
     * @param context
     * @param cancel
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetWithCancel(Context context, int gravity, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheet(context, gravity, "", cancel, true, datas, onSingleClickListener);
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
        showBottomSheet(context, gravity, title, "", false, datas, onSingleClickListener);
    }

    /**
     * 展示一个带标题和取消的底部菜单弹窗
     *
     * @param context
     * @param title
     * @param cancel
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetAll(Context context, int gravity, String title, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheet(context, gravity, title, cancel, true, datas, onSingleClickListener);
    }

    /**
     * 展示一个带标题和取消的底部菜单弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomSheetAll(Context context, int gravity, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomSheet(context, gravity, title, "", true, datas, onSingleClickListener);
    }

    private static void showBottomSheet(Context context, int gravity, String title, String cancel, boolean showCancel, List datas, OnSingleClickListener onSingleClickListener) {
        BottomListDialog.Builder builder = new BottomListDialog.Builder(context);
        builder.setTitle(title).setCancel(cancel).setGravity(gravity).setShowCancel(showCancel).setItems(datas).setOnItemClickListener(onSingleClickListener).build().showPopupWindow();
    }

    /**
     * 展示一个底部宫格弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomGridNormal(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomGrid(context, "", "", false, datas, onSingleClickListener);
    }

    /**
     * 展示一个带取消的底部宫格弹窗
     *
     * @param context
     * @param cancel
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomGridWithCancel(Context context, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomGrid(context, "", cancel, true, datas, onSingleClickListener);
    }

    /**
     * 展示一个带标题的底部宫格弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomGridWithTitle(Context context, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomGrid(context, title, "", false, datas, onSingleClickListener);
    }

    /**
     * 展示一个带标题和取消的底部宫格弹窗
     *
     * @param context
     * @param title
     * @param cancel
     * @param datas
     * @param onSingleClickListener
     */
    public static void showBottomGridAll(Context context, String title, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showBottomGrid(context, title, cancel, true, datas, onSingleClickListener);
    }

    private static void showBottomGrid(Context context, String title, String cancel, boolean showCancel, List datas, OnSingleClickListener onSingleClickListener) {
        BottomGridDialog.Builder builder = new BottomGridDialog.Builder(context);
        builder.setTitle(title).setCancel(cancel).setShowCancel(showCancel).setItems(datas).setOnItemClickListener(onSingleClickListener).build().showPopupWindow();
    }

}
