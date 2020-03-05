package com.thomas.ui.demo.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.thomas.core.utils.ColorUtils;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoLazyFragment;

public class FoundFragment extends DemoLazyFragment {
    public static FoundFragment newInstance() {
        FoundFragment fragment = new FoundFragment();
        return fragment;
    }

    private FoundFragment() {
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_found;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

    @Override
    protected void onFirstUserVisible() {
        mContentView.setBackgroundColor(ColorUtils.getRandomColor());
    }
}
