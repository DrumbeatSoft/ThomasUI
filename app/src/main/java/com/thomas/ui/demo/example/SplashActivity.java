package com.thomas.ui.demo.example;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.SPUtils;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoActivity;

import butterknife.BindView;

public class SplashActivity extends DemoActivity {

    @BindView(R.id.iv_splash_lottie)
    LottieAnimationView ivSplashLottie;

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

    @Override
    public void doBusiness() {
        ivSplashLottie.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                if (SPUtils.getInstance("welcome").getBoolean("showed", false)) {
                    ActivityUtils.startActivity(HomeActivity.class);
                } else {
                    ActivityUtils.startActivity(WelcomeActivity.class);
                }

                ActivityUtils.finishActivity(mActivity, false);
            }
        });
    }


    @Override
    public void onBackPressed() {

    }
}
