package com.thomas.ui.quick;

import android.content.Context;

import com.thomas.ui.dialog.BottomDateDialog;
import com.thomas.ui.dialog.DateDialog;
import com.thomas.ui.listener.OnDateClickListener;

import java.util.Calendar;
import java.util.Date;

public class ThomasDateDialog {
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
                .build().setPopupFadeEnable(true).showPopupWindow();
    }


    /**
     * 展示一个不带标题的底部时间选择弹窗
     *
     * @param context
     * @param startYear
     * @param endYear
     * @param selectDate
     * @param hasDay
     * @param onDateClickListener
     */
    public static void showDateBottom(Context context, int startYear, int endYear, Date selectDate, boolean hasDay, OnDateClickListener onDateClickListener) {
        showDateBottom(context, "", startYear, endYear, selectDate, hasDay, onDateClickListener);
    }

    /**
     * 展示一个带标题的底部时间选择弹窗
     *
     * @param context
     * @param title
     * @param startYear
     * @param endYear
     * @param selectDate
     * @param hasDay
     * @param onDateClickListener
     */
    public static void showDateBottom(Context context, String title, int startYear, int endYear, Date selectDate, boolean hasDay, OnDateClickListener onDateClickListener) {
        BottomDateDialog.Builder builder = new BottomDateDialog.Builder(context);
        builder.setTitle(title).setStartYear(startYear).setEndYear(endYear).setSelectedDate(selectDate)
                .supportDay(hasDay).setOnDateClickListener(onDateClickListener).build().showPopupWindow();
    }
}
