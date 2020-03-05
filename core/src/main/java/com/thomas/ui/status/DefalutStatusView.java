package com.thomas.ui.status;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.thomas.ui.R;

import static com.thomas.ui.status.TLoading.STATUS_EMPTY_DATA;
import static com.thomas.ui.status.TLoading.STATUS_LOADING;
import static com.thomas.ui.status.TLoading.STATUS_LOAD_FAILED;
import static com.thomas.ui.status.TLoading.STATUS_LOAD_SUCCESS;

public class DefalutStatusView extends LinearLayoutCompat implements View.OnClickListener {

    private final AppCompatTextView mTextView;
    private final Runnable mRetryTask;
    private final AppCompatImageView mImageView;

    public DefalutStatusView(Context context, Runnable retryTask) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_default_status, this, true);
        mImageView = findViewById(R.id.iv_status);
        mTextView = findViewById(R.id.tv_status);
        this.mRetryTask = retryTask;
        setBackgroundColor(0xFFF0F0F0);
    }


    public void setMsgViewVisibility(boolean visible) {
        mTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setStatus(int status) {
        boolean show = true;
        View.OnClickListener onClickListener = null;
//        int image = R.drawable.loading;
        int str = R.string.thomasDefaultStatusNone;
        switch (status) {
            case STATUS_LOAD_SUCCESS: show = false; break;
            case STATUS_LOADING: str = R.string.thomasDefaultStatusLoading; break;
            case STATUS_LOAD_FAILED:
                str = R.string.thomasDefaultStatusFailed;
//                image = R.drawable.icon_failed;
                onClickListener = this;
                break;
            case STATUS_EMPTY_DATA:
                str = R.string.thomasDefaultStatusEmpty;
//                image = R.drawable.icon_empty;
                break;
            default: break;
        }
//        mImageView.setImageResource(image);
        setOnClickListener(onClickListener);
        mTextView.setText(str);
        setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (mRetryTask != null) {

            mRetryTask.run();
        }
    }
}
