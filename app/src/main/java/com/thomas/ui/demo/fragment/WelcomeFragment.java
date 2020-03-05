package com.thomas.ui.demo.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.thomas.core.utils.ColorUtils;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoLazyFragment;

import butterknife.BindView;

public class WelcomeFragment extends DemoLazyFragment {

    @BindView(R.id.tv_welcome)
    AppCompatTextView tvWelcome;

    private int position;

    public static WelcomeFragment newInstance(int position) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    private WelcomeFragment() {
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        position = bundle.getInt("position");
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_welcome;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        tvWelcome.setText("这里是引导页面的展示语" + (position+1));
        int color = ColorUtils.getRandomColor();
        tvWelcome.setBackgroundColor(color);
    }
}
