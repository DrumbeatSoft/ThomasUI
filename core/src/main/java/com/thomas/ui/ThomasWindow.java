package com.thomas.ui;

import android.view.View;

import com.thomas.ui.listener.OnSingleClickListener;
import com.thomas.ui.popup.MenuPopup;

import java.util.List;

public class ThomasWindow {
    public static void showMenu(View view, List datas, OnSingleClickListener onSingleClickListener) {
        MenuPopup.Builder builder = new MenuPopup.Builder(view.getContext());
        builder.setItems(datas).setOnItemClickListener(onSingleClickListener)
                .build().showPopupWindow(view);
    }
}
