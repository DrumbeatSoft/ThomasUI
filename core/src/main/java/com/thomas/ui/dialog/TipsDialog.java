package com.thomas.ui.dialog;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.thomas.ui.R;
import com.thomas.ui.helper.ScreenHelper;

import razerdp.basepopup.BaseLazyPopupWindow;

public class TipsDialog extends BaseLazyPopupWindow {

    public static int TYPE_SUCCESS = 0;
    public static int TYPE_ERROR = 1;
    public static int TYPE_WARN = 2;
    public static int TYPE_LOADING = 3;
    public static int TYPE_CUSTOM = 4;

    private Builder builder;
    private AppCompatTextView tvMsg;
    private ProgressBar progressBar;
    private AppCompatImageView ivState;


    private Handler handler;
    private Runnable dismissRunnable = () -> {
        if (isShowing()) {
            dismiss();
        }
    };
    private int duration = 2000;

    private TipsDialog(Context context) {
        super(context);
    }

    private TipsDialog(Context context, Builder builder) {
        this(context);
        setBackgroundColor(android.R.color.transparent);
        this.builder = builder;
        if (ScreenHelper.isLandscape(context)) {
            setMaxHeight(ScreenHelper.getScreenWidth(context) / 6);
            setMinHeight(ScreenHelper.getScreenWidth(context) / 6);
            setMaxWidth(ScreenHelper.getScreenWidth(context) / 6);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 6);

        } else {
            setMaxHeight(ScreenHelper.getScreenHeight(context) / 6);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 6);
            setMaxWidth(ScreenHelper.getScreenHeight(context) / 6);
            setMinWidth(ScreenHelper.getScreenHeight(context) / 6);
        }

    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_tips_dialog);
    }


    @Override
    public void onViewCreated(View contentView) {
        if (builder.duration != 0) {
            this.duration = builder.duration;
        }
        tvMsg = findViewById(R.id.tv_msg);
        progressBar = findViewById(R.id.progress);
        ivState = findViewById(R.id.iv_status);
        if (!TextUtils.isEmpty(builder.message)) {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(builder.message);
        } else {
            if (builder.type == TYPE_SUCCESS) {
                tvMsg.setVisibility(View.VISIBLE);
                tvMsg.setText(R.string.thomasTipStateSuccess);
            } else if (builder.type == TYPE_ERROR) {
                tvMsg.setVisibility(View.VISIBLE);
                tvMsg.setText(R.string.thomasTipStateError);
            } else if (builder.type == TYPE_WARN) {
                tvMsg.setVisibility(View.VISIBLE);
                tvMsg.setText(R.string.thomasTipStateWarn);
            } else if (builder.type == TYPE_LOADING) {
                tvMsg.setVisibility(View.VISIBLE);
                tvMsg.setText(R.string.thomasTipStateLoading);
            } else {
                tvMsg.setVisibility(View.GONE);
                tvMsg.setText(R.string.thomasTipStateCustom);
            }

        }

        if (builder.type == TYPE_SUCCESS) {
            progressBar.setVisibility(View.GONE);
            ivState.setVisibility(View.VISIBLE);
            ivState.setImageResource(R.drawable.thomas_tip_success);
        } else if (builder.type == TYPE_ERROR) {
            progressBar.setVisibility(View.GONE);
            ivState.setVisibility(View.VISIBLE);
            ivState.setImageResource(R.drawable.thomas_tip_error);
        } else if (builder.type == TYPE_WARN) {
            progressBar.setVisibility(View.GONE);
            ivState.setVisibility(View.VISIBLE);
            ivState.setImageResource(R.drawable.thomas_tip_warn);
        } else if (builder.type == TYPE_LOADING) {
            progressBar.setVisibility(View.VISIBLE);
            ivState.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            ivState.setVisibility(View.VISIBLE);
        }

        if (builder.type == TYPE_CUSTOM && builder.customResId != 0) {
            ivState.setImageResource(builder.customResId);
        }
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

    @Override
    public void showPopupWindow() {
        super.showPopupWindow();
        setBackPressEnable(false);
        setOutSideTouchable(false);
        setOutSideDismiss(false);
        setPopupGravity(Gravity.CENTER);

        if (handler == null) {
            handler = new Handler();
        }
        if (handler.hasCallbacks(dismissRunnable)) {
            handler.removeCallbacks(dismissRunnable);
            dismissWithOutAnimate();
        }
        handler.postDelayed(dismissRunnable, duration);
    }

    public static class Builder {
        private Context context;
        private int type = TYPE_LOADING;
        private int duration;
        private String message;
        private int customResId;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setCustomResources(@DrawableRes int resId) {
            this.customResId = resId;
            return this;
        }

        public TipsDialog build() {
            return new TipsDialog(context, this);
        }

    }

}
