package com.thomas.ui.demo.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.core.ui.LazyFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class DemoLazyFragment extends LazyFragment {

    private Unbinder unbinder;

    @Override
    public void setContentView() {
        super.setContentView();
        unbinder = ButterKnife.bind(this, mContentView);
    }

    @Override
    protected void onFirstUserVisible() {
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void destroyViewAndThing() {

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
    public void initData(@Nullable Bundle bundle) {

    }


    @Override
    public void onThomasClick(@NonNull View view) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
