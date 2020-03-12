package com.thomas.ui;

import android.view.View;

import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSearchClickListener;
import com.thomas.ui.listener.OnSingleClickListener;
import com.thomas.ui.popup.MenuPopup;
import com.thomas.ui.popup.MenuWindow;

import java.util.List;

public class ThomasWindow {
    public static void showMenu(View view, List datas, OnSingleClickListener onSingleClickListener) {
        MenuPopup.Builder builder = new MenuPopup.Builder(view.getContext());
        builder.setItems(datas).setOnItemClickListener(onSingleClickListener)
                .build().showPopupWindow(view);
    }

    /**
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, List datas, OnSingleClickListener onSingleClickListener) {
        showSingleWithSearch(view, hint, "", "", datas, onSingleClickListener);
    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, List datas, OnMultipleClickListener onMultipleClickListener) {
        showMultipleWithSearch(view, hint, "", "", datas, onMultipleClickListener);
    }

    /**
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, List datas, OnSingleClickListener onSingleClickListener,
                                            OnSearchClickListener onSearchClickListener) {
        showSingleWithSearch(view, hint, "", "", datas, onSingleClickListener, onSearchClickListener);
    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, List datas, OnMultipleClickListener onMultipleClickListener,
                                              OnSearchClickListener onSearchClickListener) {
        showMultipleWithSearch(view, hint, "", "", datas, onMultipleClickListener, onSearchClickListener);
    }

    /**
     * 单选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(View view, List datas, OnSingleClickListener onSingleClickListener) {
        showSingle(view, "", "", datas, onSingleClickListener);
    }

    /**
     * 多选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(View view, List datas, OnMultipleClickListener onMultipleClickListener) {
        showMultiple(view, "", "", datas, onMultipleClickListener);
    }


    /**
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, String ok, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showWindow(view, MenuWindow.TYPE_SINGLE_MENU, true, hint, ok, cancel, datas, onSingleClickListener, null, null);

    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, String ok, String cancel, List datas, OnMultipleClickListener onMultipleClickListener) {
        showWindow(view, MenuWindow.TYPE_MULTIPLE_MENU, true, hint, ok, cancel, datas, null, onMultipleClickListener, null);

    }

    /**
     * 单选带搜索的菜单弹窗(搜索回调)
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, String ok, String cancel,
                                            List datas, OnSingleClickListener onSingleClickListener,
                                            OnSearchClickListener onSearchClickListener) {
        showWindow(view, MenuWindow.TYPE_SINGLE_MENU, true, hint, ok, cancel, datas,
                onSingleClickListener, null, onSearchClickListener);

    }

    /**
     * 多选带搜索的菜单弹窗(搜索回调)
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, String ok, String cancel,
                                              List datas, OnMultipleClickListener onMultipleClickListener,
                                              OnSearchClickListener onSearchClickListener) {
        showWindow(view, MenuWindow.TYPE_MULTIPLE_MENU, true, hint, ok, cancel, datas,
                null, onMultipleClickListener, onSearchClickListener);

    }

    /**
     * 单选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(View view, String ok, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showWindow(view, MenuWindow.TYPE_SINGLE_MENU, false, "", ok, cancel, datas, onSingleClickListener, null, null);

    }

    /**
     * 多选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(View view, String ok, String cancel, List datas, OnMultipleClickListener onMultipleClickListener) {
        showWindow(view, MenuWindow.TYPE_MULTIPLE_MENU, false, "", ok, cancel, datas, null, onMultipleClickListener, null);
    }


    private static void showWindow(View view, int type, boolean withSearch, String hint,
                                   String ok, String cancel, List datas,
                                   OnSingleClickListener onSingleClickListener,
                                   OnMultipleClickListener onMultipleClickListener,
                                   OnSearchClickListener onSearchClickListener) {
        MenuWindow.Builder builder = new MenuWindow.Builder(view.getContext());
        builder.setDialogType(type).setItems(datas).withSearch(withSearch).setHint(hint)
                .setSure(ok).setCancel(cancel).setOnSureClickListener(onSingleClickListener)
                .setOnSureClickListener(onMultipleClickListener)
                .setOnSearchClickListener(onSearchClickListener).build().showPopupWindow(view);
    }

}
