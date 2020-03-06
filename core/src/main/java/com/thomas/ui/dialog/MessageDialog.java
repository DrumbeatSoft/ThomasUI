package com.thomas.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.appcompat.widget.AppCompatTextView;

import com.thomas.ui.R;
import com.thomas.ui.helper.ScreenHelper;

import razerdp.basepopup.BaseLazyPopupWindow;

/**
 * 消息类型的居中弹窗，最多带有标题，内容，确定按钮，取消按钮内容
 */
public class MessageDialog extends BaseLazyPopupWindow {

    private AppCompatTextView tvDialogTitle, tvDialogContent, tvDialogCancel, tvDialogOk;
    private View viewDialogDivider;
    public static int TYPE_NORMAL_DIALOG = 0;
    public static int TYPE_NO_TITLE = 1;
    public static int TYPE_ONLY_ONE_BUTTON = 2;
    private Builder builder;

    private MessageDialog(Context context) {
        super(context);
    }

    private MessageDialog(Context context, Builder builder) {
        this(context);
        setPopupGravity(Gravity.CENTER);
        setClipChildren(false);
        this.builder = builder;
        if (ScreenHelper.isLandscape(context)) {
            //横屏
            setMaxHeight(ScreenHelper.getScreenHeight(context) / 2);
            setMaxWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 4);
        } else {
            //竖屏
            setMaxHeight(ScreenHelper.getScreenHeight(context) / 3);
            setMaxWidth((ScreenHelper.getScreenWidth(context) / 3) * 2);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 4);
        }
    }


    @Override
    public void onViewCreated(View contentView) {
        tvDialogTitle = findViewById(R.id.thomas_tv_title);
        tvDialogContent = findViewById(R.id.thomas_tv_content);
        tvDialogOk = findViewById(R.id.thomas_btn_ok);
        viewDialogDivider = findViewById(R.id.thomas_divider_vertical);
        tvDialogCancel = findViewById(R.id.thomas_btn_cancel);
        if (builder.dialogType == TYPE_NO_TITLE) {
            tvDialogTitle.setVisibility(View.GONE);
            tvDialogContent.setGravity(Gravity.LEFT | Gravity.CENTER_HORIZONTAL);
        }
        if (builder.dialogType == TYPE_ONLY_ONE_BUTTON) {
            tvDialogCancel.setVisibility(View.GONE);
            viewDialogDivider.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(builder.title)) {
            tvDialogTitle.setVisibility(View.GONE);
        } else {
            tvDialogTitle.setVisibility(View.VISIBLE);
            tvDialogTitle.setText(builder.title);
        }
        tvDialogContent.setText(builder.content);

        tvDialogCancel.setText(TextUtils.isEmpty(builder.cancel) ? getContext().getString(android.R.string.cancel) : builder.cancel);
        tvDialogOk.setText(TextUtils.isEmpty(builder.ok) ? getContext().getString(android.R.string.ok) : builder.ok);

        if (builder.onCancelClickListener == null) {
            tvDialogCancel.setOnClickListener(v -> dismiss());
        } else {
            tvDialogCancel.setOnClickListener(v -> {
                dismiss();
                builder.onCancelClickListener.onClick();
            });
        }

        if (builder.onSureClickListener == null) {
            tvDialogOk.setOnClickListener(v -> dismiss());
        } else {
            tvDialogOk.setOnClickListener(v -> {
                dismiss();
                builder.onSureClickListener.onClick();
            });
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_message_dialog);
    }


    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }


    public static class Builder {
        private Context context;
        private int dialogType = TYPE_NORMAL_DIALOG;
        private String title, content, cancel, ok;
        private OnDialogClickListener onCancelClickListener, onSureClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDialogType(int dialogType) {
            this.dialogType = dialogType;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCancel(String cancel, OnDialogClickListener onClickListener) {
            this.cancel = cancel;
            return setOnCancelClickListener(onClickListener);
        }

        public Builder setSure(String ok, OnDialogClickListener onClickListener) {
            this.ok = ok;
            return setOnSureClickListener(onClickListener);
        }

        public Builder setOnCancelClickListener(OnDialogClickListener onCancelClickListener) {
            this.onCancelClickListener = onCancelClickListener;
            return this;
        }

        public Builder setOnSureClickListener(OnDialogClickListener onSureClickListener) {
            this.onSureClickListener = onSureClickListener;
            return this;
        }

        public MessageDialog build() {
            return new MessageDialog(context, this);
        }

    }


}
