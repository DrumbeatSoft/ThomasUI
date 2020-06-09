package com.thomas.ui.quick;

import android.content.Context;

import com.thomas.ui.dialog.ListDialog;
import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class ThomasListDialog {
    /**
     * 显示一个居中的菜单弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showMenu(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_ONLY_MENU, null, datas, -1, null, null, null, onSingleClickListener, null);
    }

    /**
     * 显示一个居中的单选弹窗，没有标题，没有默认选中状态
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_SINGLE_MENU, null, datas, -1, null, null, null, onSingleClickListener, null);
    }

    /**
     * 显示一个居中的单选弹窗，没有标题，有默认选中状态
     *
     * @param context
     * @param datas
     * @param selected
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, List datas, int selected, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_SINGLE_MENU, null, datas, selected, null, null, null, onSingleClickListener, null);
    }


    /**
     * 显示一个居中的单选弹窗，有标题，没有默认选中状态
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_SINGLE_MENU, title, datas, -1, null, null, null, onSingleClickListener, null);
    }

    /**
     * 显示一个居中的单选弹窗，有标题，有默认选中状态
     *
     * @param context
     * @param title
     * @param datas
     * @param selected
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, String title, List datas, int selected, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_SINGLE_MENU, title, datas, selected, null, null, null, onSingleClickListener, null);
    }


    /**
     * 显示一个居中的单选弹窗，有标题，没有默认选中状态，可自定义取消、确定按钮文字
     *
     * @param context
     * @param title
     * @param datas
     * @param sure
     * @param cancel
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, String title, List datas, String sure, String cancel, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_SINGLE_MENU, title, datas, -1, null, sure, cancel, onSingleClickListener, null);
    }

    /**
     * 显示一个居中的单选弹窗，有标题，有默认选中状态，可自定义取消、确定按钮文字
     *
     * @param context
     * @param title
     * @param datas
     * @param selected
     * @param sure
     * @param cancel
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, String title, List datas, int selected, String sure, String cancel, OnSingleClickListener onSingleClickListener) {
        showListDialog(context, ListDialog.TYPE_SINGLE_MENU, title, datas, selected, null, sure, cancel, onSingleClickListener, null);
    }


    /**
     * 显示一个居中的多选弹窗，没有标题，没有默认选中状态
     *
     * @param context
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, List datas, OnMultipleClickListener onMultipleClickListener) {
        showListDialog(context, ListDialog.TYPE_MULTIPLE_MENU, null, datas, -1, null, null, null, null, onMultipleClickListener);
    }

    /**
     * 显示一个居中的多选弹窗，没有标题，有默认选中状态
     *
     * @param context
     * @param datas
     * @param positions
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener) {
        showListDialog(context, ListDialog.TYPE_MULTIPLE_MENU, null, datas, -1, positions, null, null, null, onMultipleClickListener);
    }


    /**
     * 显示一个居中的多选弹窗，有标题，没有默认选中状态
     *
     * @param context
     * @param title
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, String title, List datas, OnMultipleClickListener onMultipleClickListener) {
        showListDialog(context, ListDialog.TYPE_MULTIPLE_MENU, title, datas, -1, null, null, null, null, onMultipleClickListener);
    }

    /**
     * 显示一个居中的多选弹窗，有标题，有默认选中状态
     *
     * @param context
     * @param title
     * @param datas
     * @param positions
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, String title, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener) {
        showListDialog(context, ListDialog.TYPE_MULTIPLE_MENU, title, datas, -1, positions, null, null, null, onMultipleClickListener);
    }


    /**
     * 显示一个居中的多选弹窗，有标题，没有默认选中状态，可自定义取消、确定按钮文字
     *
     * @param context
     * @param title
     * @param datas
     * @param sure
     * @param cancel
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, String title, List datas, String sure, String cancel, OnMultipleClickListener onMultipleClickListener) {
        showListDialog(context, ListDialog.TYPE_MULTIPLE_MENU, title, datas, -1, null, sure, cancel, null, onMultipleClickListener);
    }

    /**
     * 显示一个居中的多选弹窗，有标题，有默认选中状态，可自定义取消、确定按钮文字
     *
     * @param context
     * @param title
     * @param datas
     * @param positions
     * @param sure
     * @param cancel
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, String title, List datas, List<Integer> positions, String sure, String cancel, OnMultipleClickListener onMultipleClickListener) {
        showListDialog(context, ListDialog.TYPE_MULTIPLE_MENU, title, datas, -1, positions, sure, cancel, null, onMultipleClickListener);
    }

    private static void showListDialog(Context context, int dialogType, String title,
                                       List datas, int position, List<Integer> positions, String sure, String cancel,
                                       OnSingleClickListener onSingleClickListener, OnMultipleClickListener onMultipleClickListener) {
        if (position != -1) {
            positions = new ArrayList<>();
            positions.add(position);
        }
        ListDialog.Builder builder = new ListDialog.Builder(context);
        builder.setDialogType(dialogType)
                .setTitle(title)
                .setItems(datas)
                .setSure(sure)
                .setCancel(cancel)
                .setSelected(positions)
                .setOnSureClickListener(onSingleClickListener)
                .setOnSureClickListener(onMultipleClickListener)
                .build().setPopupFadeEnable(true).showPopupWindow();
    }

}
