package com.thomas.ui.dialog;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.thomas.ui.R;

import razerdp.basepopup.BasePopupWindow;

public class TipsDialog extends BasePopupWindow {
    public TipsDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_tips_dialog);
    }


    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }


}
