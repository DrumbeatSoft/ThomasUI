package com.thomas.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.R;
import com.thomas.ui.entity.AbsKV;
import com.thomas.ui.helper.ClickHelper;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.TranslationConfig;

public class BottomGridDialog extends BaseLazyPopupWindow {

    private RecyclerView rvDialogContent;
    private Builder builder;

    private BottomGridDialog(Context context) {
        super(context);
    }

    private BottomGridDialog(Context context, Builder builder) {
        this(context);
        this.builder = builder;
        setAlignBackground(false);
        setClipChildren(false);
        setOutSideTouchable(false);
        setPopupGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
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
    public void onViewCreated(View contentView) {
        rvDialogContent = findViewById(R.id.thomas_rv_content);

        DialogMenuAdapter adapter = new DialogMenuAdapter();

        rvDialogContent.setAdapter(adapter);
        rvDialogContent.setLayoutManager(new GridLayoutManager(getContext(), builder.spanCount == 0 ? 4 : builder.spanCount));
        adapter.setNewInstance(builder.items);
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

        ClickHelper.applyGlobalDebouncing(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.FROM_BOTTOM).toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.TO_BOTTOM).toDismiss();
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
        private int spanCount;
        private List<T> items;
        private OnSingleClickListener onSingleClickListener;

        public Builder(Context context) {
            this.context = context;
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

        public Builder setSpanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }
    }

}
