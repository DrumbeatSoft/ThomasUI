package com.thomas.ui.popup;

import android.content.Context;
import android.view.View;

import razerdp.basepopup.BaseLazyPopupWindow;

/**
 * 菜单popupWindow
 */
public class MenuPopup extends BaseLazyPopupWindow {
    public MenuPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return null;
    }
}
