package com.thomas.ui.demo.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.SPUtils;
import com.thomas.ui.adapter.ThomasFragmentStateAdapter;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoActivity;
import com.thomas.ui.demo.fragment.WelcomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WelcomeActivity extends DemoActivity {

    @BindView(R.id.vp_welcome)
    ViewPager2 vpWelcome;
    @BindView(R.id.btn_home)
    AppCompatButton btnHome;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

        fragments.add(WelcomeFragment.newInstance(0));
        fragments.add(WelcomeFragment.newInstance(1));
        fragments.add(WelcomeFragment.newInstance(2));
        vpWelcome.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vpWelcome.setAdapter(new ThomasFragmentStateAdapter(mActivity,fragments));

        vpWelcome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == fragments.size() - 1) {
                    btnHome.setVisibility(View.VISIBLE);
                } else {
                    btnHome.setVisibility(View.GONE);
                }
            }
        });

        applyThomasClickListener(btnHome);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {
        if (view == btnHome) {
            SPUtils.getInstance("welcome").put("showed", true);
            ActivityUtils.startActivity(HomeActivity.class);
            ActivityUtils.finishActivity(mActivity, false);
        }
    }
}
