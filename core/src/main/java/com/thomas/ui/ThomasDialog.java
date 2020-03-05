package com.thomas.ui;

import android.content.Context;

import com.thomas.ui.dialog.DateDialog;
import com.thomas.ui.dialog.MenuDialog;
import com.thomas.ui.dialog.MessageDialog;
import com.thomas.ui.dialog.OnDateClickListener;
import com.thomas.ui.dialog.OnDialogClickListener;
import com.thomas.ui.dialog.OnMultipleClickListener;
import com.thomas.ui.dialog.OnSingleClickListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThomasDialog {


    /**
     * 弹出一条消息,点击确定按钮弹窗消失
     *
     * @param context
     * @param content
     */
    public static void showTips(Context context, String content) {
        showMessageDialog(context, MessageDialog.TYPE_ONLY_ONE_BUTTON,
                null, content, null, null, null, null);
    }

    /**
     * 弹出一条消息,点击确定按钮执行自己的逻辑,并且可以自定义确定按钮的文字。
     *
     * @param context
     * @param content
     * @param sure
     * @param onSureClickListener
     */
    public static void showTips(Context context, String content, String sure, OnDialogClickListener onSureClickListener) {
        showMessageDialog(context, MessageDialog.TYPE_ONLY_ONE_BUTTON,
                null, content, sure, null, onSureClickListener, null);
    }

    /**
     * 弹出一条消息,有确定和取消按钮,点击按钮执行自己的逻辑,并且可以自定义按钮的文字。
     *
     * @param context
     * @param content
     * @param sure
     * @param onSureClickListener
     * @param cancel
     * @param onCancelClickListener
     */
    public static void showTips(Context context, String content,
                                String sure, OnDialogClickListener onSureClickListener,
                                String cancel, OnDialogClickListener onCancelClickListener) {
        showMessageDialog(context, MessageDialog.TYPE_NO_TITLE, null, content,
                sure, cancel, onSureClickListener, onCancelClickListener);
    }

    /**
     * 弹出一个消息类型的对话框,有确定和取消按钮,点击按钮执行自己的逻辑,并且可以自定义按钮的文字。
     *
     * @param context
     * @param title
     * @param content
     * @param sure
     * @param onSureClickListener
     * @param cancel
     * @param onCancelClickListener
     */
    public static void showMessage(Context context, String title, String content,
                                   String sure, OnDialogClickListener onSureClickListener,
                                   String cancel, OnDialogClickListener onCancelClickListener) {
        showMessageDialog(context, MessageDialog.TYPE_NORMAL_DIALOG, title, content,
                sure, cancel, onSureClickListener, onCancelClickListener);
    }

    /**
     * 弹出一个消息类型的对话框,有确定和取消按钮,
     * 点击取消按钮弹窗消失,
     * 点击确定按钮执行自己的逻辑,并且可以自定义按钮的文字。
     *
     * @param context
     * @param title
     * @param content
     * @param sure
     * @param onSureClickListener
     */
    public static void showMessage(Context context, String title, String content,
                                   String sure, OnDialogClickListener onSureClickListener) {
        showMessage(context, title, content, sure, onSureClickListener, null, null);
    }

    /**
     * 弹出一个消息类型的对话框,有确定和取消按钮,没有标题
     * 点击取消按钮弹窗消失,
     * 点击确定按钮执行自己的逻辑,并且可以自定义按钮的文字。
     *
     * @param context
     * @param content
     * @param sure
     * @param onSureClickListener
     */
    public static void showMessage(Context context, String content,
                                   String sure, OnDialogClickListener onSureClickListener) {
        showMessage(context, null, content, sure, onSureClickListener, null, null);
    }


    /**
     * 显示一个居中的菜单弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showMenu(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_ONLY_MENU, null, datas, null, null, onSingleClickListener, null);
    }

    /**
     * 显示一个没有标题的单选弹窗
     *
     * @param context
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, List datas, OnSingleClickListener onSingleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_SINGLE_MENU, null, datas, null, null, onSingleClickListener, null);
    }

    /**
     * 显示一个带标题的单选弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, String title, List datas, OnSingleClickListener onSingleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_SINGLE_MENU, title, datas, null, null, onSingleClickListener, null);
    }

    /**
     * 显示一个带标题的单选弹窗，并且按钮文字可以自定义
     *
     * @param context
     * @param title
     * @param datas
     * @param sure
     * @param cancel
     * @param onSingleClickListener
     */
    public static void showSingle(Context context, String title, List datas, String sure, String cancel, OnSingleClickListener onSingleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_SINGLE_MENU, title, datas, sure, cancel, onSingleClickListener, null);
    }

    /**
     * 显示一个没有标题的多选弹窗
     *
     * @param context
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, List datas, OnMultipleClickListener onMultipleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_MULTIPLE_MENU, null, datas, null, null, null, onMultipleClickListener);

    }

    /**
     * 显示一个带标题的多选弹窗
     *
     * @param context
     * @param title
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, String title, List datas, OnMultipleClickListener onMultipleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_MULTIPLE_MENU, title, datas, null, null, null, onMultipleClickListener);
    }

    /**
     * 显示一个带标题的多选弹窗，并且按钮文字可以自定义
     *
     * @param context
     * @param title
     * @param datas
     * @param sure
     * @param cancel
     * @param onMultipleClickListener
     */
    public static void showMultiple(Context context, String title, List datas, String sure, String cancel, OnMultipleClickListener onMultipleClickListener) {
        showMenuDialog(context, MenuDialog.TYPE_MULTIPLE_MENU, title, datas, sure, cancel, null, onMultipleClickListener);
    }

    /**
     * 显示一个时间选择弹窗
     *
     * @param context
     * @param hasDay
     * @param onDateClickListener
     */
    public static void showDateDialog(Context context, String title, boolean hasDay, OnDateClickListener onDateClickListener) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        showDateDialog(context, title, currentYear - 50, currentYear + 50, new Date(), hasDay, null, null, onDateClickListener);
    }

    /**
     * 显示一个时间选择弹窗，可自定义默认日期
     *
     * @param context
     * @param selectDate
     * @param hasDay
     * @param onDateClickListener
     */
    public static void showDateDialog(Context context, String title, Date selectDate, boolean hasDay, OnDateClickListener onDateClickListener) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        showDateDialog(context, title, currentYear - 50, currentYear + 50, selectDate, hasDay, null, null, onDateClickListener);
    }

    public static void showMessageDialog(Context context, int dialogType,
                                         String title, String content, String sure, String cancel,
                                         OnDialogClickListener onSureClickListener, OnDialogClickListener onCancelClickListener) {
        MessageDialog.Builder builder = new MessageDialog.Builder(context);
        builder.setTitle(title)
                .setContent(content)
                .setSure(sure, onSureClickListener)
                .setCancel(cancel, onCancelClickListener)
                .setDialogType(dialogType)
                .build().showPopupWindow();
    }

    public static void showMenuDialog(Context context, int dialogType, String title,
                                      List datas, String sure, String cancel,
                                      OnSingleClickListener onSingleClickListener, OnMultipleClickListener onMultipleClickListener) {
        MenuDialog.Builder builder = new MenuDialog.Builder(context);
        builder.setDialogType(dialogType)
                .setTitle(title)
                .setItems(datas)
                .setSure(sure)
                .setCancel(cancel)
                .setOnSureClickListener(onSingleClickListener)
                .setOnSureClickListener(onMultipleClickListener)
                .build().showPopupWindow();
    }


    public static void showDateDialog(Context context, String title,
                                      int startYear, int endYear, Date selectDate, boolean hasDay,
                                      String sure, String cancel, OnDateClickListener onDateClickListener) {
        DateDialog.Builder builder = new DateDialog.Builder(context);
        builder.setTitle(title)
                .setStartYear(startYear)
                .setEndYear(endYear)
                .setSelectedDate(selectDate)
                .supportDay(hasDay)
                .setSure(sure)
                .setCancel(cancel)
                .setOnDateClickListener(onDateClickListener)
                .build().showPopupWindow();
    }

}
