package com.thomas.ui.demo.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.thomas.core.ui.BaseActivity;
import com.thomas.core.utils.BarUtils;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.example.SplashActivity;

import butterknife.ButterKnife;

public abstract class DemoActivity extends BaseActivity {
    @Override
    protected boolean isNeedAdapt() {
        return false;
    }

    @Override
    protected int setAdaptScreen() {
        return 0;
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public void initStatusBar() {
        if (BarUtils.isNavBarVisible(this)) {
            BarUtils.setNavBarVisibility(this, !isTransparent());
        }
        if (BarUtils.isSupportNavBar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                BarUtils.setNavBarColor(this, ContextCompat.getColor(this, android.R.color.black));
            }
        }
        BarUtils.setStatusBarLightMode(mActivity, true);
        if (!(mActivity instanceof SplashActivity)) {
            BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomasWindowBackground), false);
        }
    }

    @Override
    public void setContentView() {
        super.setContentView();
        ButterKnife.bind(this);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }


    @Override
    public void onThomasClick(@NonNull View view) {

    }
}
