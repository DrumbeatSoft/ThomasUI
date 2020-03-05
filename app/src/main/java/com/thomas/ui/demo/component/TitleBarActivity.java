package com.thomas.ui.demo.component;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.thomas.core.utils.ActivityUtils;
import com.thomas.ui.ThomasTitleBar;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoActivity;

import butterknife.BindView;

public class TitleBarActivity extends DemoActivity {


    @BindView(R.id.thomasTitleBar)
    ThomasTitleBar thomasTitleBar;

    @Override
    public int bindLayout() {
        return R.layout.activity_title_bar;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        thomasTitleBar.setListener((v, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
    }

    @Override
    public void doBusiness() {

    }

}
