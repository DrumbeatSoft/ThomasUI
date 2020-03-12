package com.thomas.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.R;
import com.thomas.ui.entity.AbsKV;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;

public class BottomGridDialog extends BaseLazyPopupWindow {

    private AppCompatTextView tvDialogTitle, tvDialogCancel;
    private RecyclerView rvDialogContent;
    private View viewDialogDividerTitle, viewDialogDividerCancel;
    private Builder builder;

    private BottomGridDialog(Context context) {
        super(context);
    }

    public BottomGridDialog(Context context, Builder builder) {
        this(context);
        this.builder = builder;

        if (ScreenHelper.isLandscape(context)) {
            //横屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
            setMaxWidth(ScreenHelper.getScreenHeight(context));
            setMinWidth(ScreenHelper.getScreenHeight(context));
        } else {
            //竖屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
            setMaxWidth(ScreenHelper.getScreenWidth(context));
            setMinWidth(ScreenHelper.getScreenWidth(context));
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_bottom_grid_dialog);
    }


    @Override
    public void showPopupWindow() {
        super.showPopupWindow();
        setAlignBackground(false);
        setClipChildren(false);
        setPopupGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    }


    @Override
    public void onViewCreated(View contentView) {
        tvDialogTitle = findViewById(R.id.thomas_tv_title);
        tvDialogCancel = findViewById(R.id.thomas_btn_cancel);
        rvDialogContent = findViewById(R.id.thomas_rv_content);
        viewDialogDividerTitle = findViewById(R.id.thomas_divider_title);
        viewDialogDividerCancel = findViewById(R.id.thomas_divider_cancel);
        if (TextUtils.isEmpty(builder.title)) {
            tvDialogTitle.setVisibility(View.GONE);
            viewDialogDividerTitle.setVisibility(View.GONE);
        } else {
            tvDialogTitle.setVisibility(View.VISIBLE);
            viewDialogDividerTitle.setVisibility(View.VISIBLE);
            tvDialogTitle.setText(builder.title);
        }

        if (builder.showCancel) {
            tvDialogCancel.setVisibility(View.VISIBLE);
            viewDialogDividerCancel.setVisibility(View.VISIBLE);
        } else {
            tvDialogCancel.setVisibility(View.GONE);
            viewDialogDividerCancel.setVisibility(View.GONE);
        }

        tvDialogCancel.setText(TextUtils.isEmpty(builder.cancel) ? getContext().getString(android.R.string.cancel) : builder.cancel);

        tvDialogCancel.setOnClickListener(v -> {
            dismiss();
        });


        DialogMenuAdapter adapter = new DialogMenuAdapter();

        rvDialogContent.setAdapter(adapter);
        rvDialogContent.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter.setNewData(builder.items);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AbsKV clickBean = (AbsKV) adapter.getData().get(position);
                if (builder.onSingleClickListener != null) {
                    builder.onSingleClickListener.onClick(position, clickBean.getKey(), clickBean.getValue());
                }
                dismiss();
            }
        });

    }


    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 360);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 360);
    }

    private class DialogMenuAdapter<T extends AbsKV> extends BaseQuickAdapter<T, BaseViewHolder> {

        public DialogMenuAdapter() {
            super(R.layout.item_view_grid_bottom);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {
            helper.setText(R.id.thomas_tv_item_name, item.getKey());
            if (item.getResId() == 0) {
                helper.findView(R.id.thomas_iv_item_res).setVisibility(View.INVISIBLE);
            } else {
                helper.findView(R.id.thomas_iv_item_res).setVisibility(View.VISIBLE);
                helper.setImageResource(R.id.thomas_iv_item_res, item.getResId());
            }

        }
    }

    public static class Builder<T extends AbsKV> {
        private Context context;
        private String title, cancel;
        private List<T> items;
        private boolean showCancel;
        private OnSingleClickListener onSingleClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setShowCancel(boolean showCancel) {
            this.showCancel = showCancel;
            return this;
        }


        public Builder setItems(List<T> datas) {
            this.items = datas;
            return this;
        }


        public Builder setOnItemClickListener(OnSingleClickListener onSingleClickListener) {
            this.onSingleClickListener = onSingleClickListener;
            return this;
        }


        public BottomGridDialog build() {
            return new BottomGridDialog(context, this);
        }
    }

}
