package com.thomas.ui.quick;

import android.view.View;

import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSearchClickListener;
import com.thomas.ui.listener.OnSingleClickListener;
import com.thomas.ui.popup.ListPopup;
import com.thomas.ui.popup.ListWindow;

import java.util.ArrayList;
import java.util.List;

public class ThomasPopup {

    public static void showMenu(View view, List datas, OnSingleClickListener onSingleClickListener) {
        ListPopup.Builder builder = new ListPopup.Builder(view.getContext());
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
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, List datas, int position, OnSingleClickListener onSingleClickListener) {
        showSingleWithSearch(view, hint, "", "", datas, position, onSingleClickListener);
    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener) {
        showMultipleWithSearch(view, hint, "", "", datas, positions, onMultipleClickListener);
    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, List datas,  OnMultipleClickListener onMultipleClickListener) {
        showMultipleWithSearch(view, hint, "", "", datas, null, onMultipleClickListener);
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
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, List datas, int position, OnSingleClickListener onSingleClickListener,
                                            OnSearchClickListener onSearchClickListener) {
        showSingleWithSearch(view, hint, "", "", datas, position, onSingleClickListener, onSearchClickListener);
    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener,
                                              OnSearchClickListener onSearchClickListener) {
        showMultipleWithSearch(view, hint, "", "", datas, positions, onMultipleClickListener, onSearchClickListener);
    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, List datas,  OnMultipleClickListener onMultipleClickListener,
                                              OnSearchClickListener onSearchClickListener) {
        showMultipleWithSearch(view, hint, "", "", datas, null, onMultipleClickListener, onSearchClickListener);
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
     * 单选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(View view, List datas, int position, OnSingleClickListener onSingleClickListener) {
        showSingle(view, "", "", datas, position, onSingleClickListener);
    }

    /**
     * 多选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(View view, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener) {
        showMultiple(view, "", "", datas, positions, onMultipleClickListener);
    }

    /**
     * 多选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(View view, List datas, OnMultipleClickListener onMultipleClickListener) {
        showMultiple(view, "", "", datas, null, onMultipleClickListener);
    }


    /**
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, String ok, String cancel, List datas, OnSingleClickListener onSingleClickListener) {
        showWindow(view, ListWindow.TYPE_SINGLE_MENU, true, hint, ok, cancel, datas, -1, null, onSingleClickListener, null, null);

    }

    /**
     * 单选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, String ok, String cancel, List datas, int position, OnSingleClickListener onSingleClickListener) {
        showWindow(view, ListWindow.TYPE_SINGLE_MENU, true, hint, ok, cancel, datas, position, null, onSingleClickListener, null, null);

    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, String ok, String cancel, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener) {
        showWindow(view, ListWindow.TYPE_MULTIPLE_MENU, true, hint, ok, cancel, datas, -1, positions, null, onMultipleClickListener, null);

    }

    /**
     * 多选带搜索的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultipleWithSearch(View view, String hint, String ok, String cancel, List datas, OnMultipleClickListener onMultipleClickListener) {
        showWindow(view, ListWindow.TYPE_MULTIPLE_MENU, true, hint, ok, cancel, datas, -1, null, null, onMultipleClickListener, null);

    }

    /**
     * 单选带搜索的菜单弹窗(搜索回调)
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingleWithSearch(View view, String hint, String ok, String cancel,
                                            List datas, int position, OnSingleClickListener onSingleClickListener,
                                            OnSearchClickListener onSearchClickListener) {
        showWindow(view, ListWindow.TYPE_SINGLE_MENU, true, hint, ok, cancel, datas, position, null,
                onSingleClickListener, null, onSearchClickListener);

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
        showWindow(view, ListWindow.TYPE_SINGLE_MENU, true, hint, ok, cancel, datas, -1, null,
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
                                              List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener,
                                              OnSearchClickListener onSearchClickListener) {
        showWindow(view, ListWindow.TYPE_MULTIPLE_MENU, true, hint, ok, cancel, datas, -1, positions,
                null, onMultipleClickListener, onSearchClickListener);
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
        showWindow(view, ListWindow.TYPE_MULTIPLE_MENU, true, hint, ok, cancel, datas, -1, null,
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
        showWindow(view, ListWindow.TYPE_SINGLE_MENU, false, "", ok, cancel, datas, -1, null, onSingleClickListener, null, null);
    }

    /**
     * 单选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onSingleClickListener
     */
    public static void showSingle(View view, String ok, String cancel, List datas, int position, OnSingleClickListener onSingleClickListener) {
        showWindow(view, ListWindow.TYPE_SINGLE_MENU, false, "", ok, cancel, datas, position, null, onSingleClickListener, null, null);
    }

    /**
     * 多选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(View view, String ok, String cancel, List datas, List<Integer> positions, OnMultipleClickListener onMultipleClickListener) {
        showWindow(view, ListWindow.TYPE_MULTIPLE_MENU, false, "", ok, cancel, datas, -1, positions, null, onMultipleClickListener, null);
    }

    /**
     * 多选的菜单弹窗
     *
     * @param view
     * @param datas
     * @param onMultipleClickListener
     */
    public static void showMultiple(View view, String ok, String cancel, List datas, OnMultipleClickListener onMultipleClickListener) {
        showWindow(view, ListWindow.TYPE_MULTIPLE_MENU, false, "", ok, cancel, datas, -1, null, null, onMultipleClickListener, null);
    }


    private static void showWindow(View view, int type, boolean withSearch, String hint,
                                   String ok, String cancel, List datas,
                                   int position,
                                   List<Integer> positions,
                                   OnSingleClickListener onSingleClickListener,
                                   OnMultipleClickListener onMultipleClickListener,
                                   OnSearchClickListener onSearchClickListener) {

        if (position != -1) {
            positions = new ArrayList<>();
            positions.add(position);
        }
        if (positions == null) {
            positions = new ArrayList<>();
        }
        ListWindow.Builder builder = new ListWindow.Builder(view.getContext());
        builder.setDialogType(type).setItems(datas).setSelected(positions).withSearch(withSearch).setHint(hint)
                .setSure(ok).setCancel(cancel).setOnSureClickListener(onSingleClickListener)
                .setOnSureClickListener(onMultipleClickListener)
                .setOnSearchClickListener(onSearchClickListener).build().showPopupWindow(view);
    }

}
