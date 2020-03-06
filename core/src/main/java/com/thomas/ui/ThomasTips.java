package com.thomas.ui;

import android.content.Context;

import androidx.annotation.DrawableRes;

import com.thomas.ui.dialog.TipsDialog;

import static com.thomas.ui.dialog.TipsDialog.TYPE_CUSTOM;
import static com.thomas.ui.dialog.TipsDialog.TYPE_ERROR;
import static com.thomas.ui.dialog.TipsDialog.TYPE_LOADING;
import static com.thomas.ui.dialog.TipsDialog.TYPE_SUCCESS;
import static com.thomas.ui.dialog.TipsDialog.TYPE_WARN;

public class ThomasTips {


    /**
     * 显示完成状态弹窗
     */
    public static void showSuccess(Context context) {
        showSuccess(context, "");

    }

    public static void showError(Context context) {
        showError(context, "");
    }

    public static void showWarn(Context context) {
        showWarn(context, "");
    }

    public static void showLoading(Context context) {
        showLoading(context, "");
    }

    /**
     * 显示完成状态弹窗，带自定义文字
     *
     * @param msg 自定义文字
     */
    public static void showSuccess(Context context, String msg) {
        showTips(context, TYPE_SUCCESS, msg);
    }

    /**
     * 显示错误状态弹窗，带自定义文字
     *
     * @param msg 自定义文字
     */
    public static void showError(Context context, String msg) {
        showTips(context, TYPE_ERROR, msg);
    }

    /**
     * 显示警告状态弹窗，带自定义文字
     *
     * @param msg 自定义文字
     */
    public static void showWarn(Context context, String msg) {
        showTips(context, TYPE_WARN, msg);
    }

    /**
     * 显示加载状态弹窗，带自定义文字
     *
     * @param msg 自定义文字
     */
    public static void showLoading(Context context, String msg) {
        showTips(context, TYPE_LOADING, msg);
    }


    /**
     * 显示自定义弹窗
     * @param context
     * @param resId
     */
    public static void showCustom(Context context, @DrawableRes int resId) {
        showTips(context, TYPE_CUSTOM, resId, "");
    }

    /**
     * 显示自定义弹窗
     * @param context
     * @param resId
     * @param msg
     */
    public static void showCustom(Context context, @DrawableRes int resId, String msg) {
        showTips(context, TYPE_CUSTOM, resId, msg);
    }



    /**
     * 显示状态弹窗，带自定义文字
     *
     * @param context
     * @param type
     * @param msg
     */
    private static void showTips(Context context, int type, @DrawableRes int resId, String msg) {
        TipsDialog.Builder builder = new TipsDialog.Builder(context);
        builder.setType(type).setMessage(msg).setCustomResources(resId).build().showPopupWindow();

    }

    /**
     * 显示状态弹窗，带自定义文字
     *
     * @param context
     * @param type
     * @param msg
     */
    private static void showTips(Context context, int type, String msg) {
        TipsDialog.Builder builder = new TipsDialog.Builder(context);
        builder.setType(type).setMessage(msg).build().showPopupWindow();

    }
}
