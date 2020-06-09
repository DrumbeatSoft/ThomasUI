package com.thomas.ui.quick;

import android.content.Context;

import com.thomas.ui.dialog.MessageDialog;
import com.thomas.ui.listener.OnDialogClickListener;

public class ThomasMessageDialog {
    /**
     * 弹出一条消息,点击确定按钮弹窗消失
     *
     * @param context
     * @param content
     */
    public static void showSimpleMessage(Context context, String content) {
        showMessageDialog(context, MessageDialog.TYPE_ONLY_ONE_BUTTON,
                null, content, null, null, null, null);
    }

    /**
     * 弹出一条消息,点击确定按钮弹窗消失
     *
     * @param context
     * @param content
     */
    public static void showSimpleMessage(Context context, String content, OnDialogClickListener onSureClickListener) {
        showMessageDialog(context, MessageDialog.TYPE_ONLY_ONE_BUTTON,
                null, content, null, null, onSureClickListener, null);
    }

    /**
     * 弹出一条消息,点击确定按钮执行自己的逻辑,并且可以自定义确定按钮的文字。
     *
     * @param context
     * @param content
     * @param sure
     * @param onSureClickListener
     */
    public static void showSimpleMessage(Context context, String content, String sure, OnDialogClickListener onSureClickListener) {
        showMessageDialog(context, MessageDialog.TYPE_ONLY_ONE_BUTTON,
                null, content, sure, null, onSureClickListener, null);
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
     * 弹出一个消息类型的对话框,有确定和取消按钮,没有标题
     * 点击按钮执行自己的逻辑,并且可以自定义按钮的文字。
     *
     * @param context
     * @param content
     * @param sure
     * @param onSureClickListener
     */
    public static void showMessage(Context context, String content,
                                   String sure, OnDialogClickListener onSureClickListener,
                                   String cancel, OnDialogClickListener onCancelClickListener) {
        showMessage(context, null, content, sure, onSureClickListener, cancel, onCancelClickListener);
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

    private static void showMessageDialog(Context context, int dialogType,
                                          String title, String content, String sure, String cancel,
                                          OnDialogClickListener onSureClickListener, OnDialogClickListener onCancelClickListener) {
        MessageDialog.Builder builder = new MessageDialog.Builder(context);
        builder.setTitle(title)
                .setContent(content)
                .setSure(sure, onSureClickListener)
                .setCancel(cancel, onCancelClickListener)
                .setDialogType(dialogType)
                .build().setPopupFadeEnable(true).showPopupWindow();
    }

}
