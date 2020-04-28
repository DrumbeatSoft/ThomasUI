package com.thomas.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.R;
import com.thomas.ui.entity.AbsKV;
import com.thomas.ui.helper.ClickHelper;
import com.thomas.ui.helper.RecyclerViewHelper;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;

/**
 * 菜单居中弹窗，有纯菜单模式，单选模式，多选模式。
 */
public class MenuDialog<T extends AbsKV> extends BaseLazyPopupWindow {

    private AppCompatTextView tvDialogTitle, tvDialogCancel, tvDialogOk;
    private RecyclerView rvDialogContent;
    private View viewDialogDividerVertical, viewDialogDividerHorizontal, viewDialogDividerTitle;
    public static int TYPE_ONLY_MENU = 0;
    public static int TYPE_SINGLE_MENU = 1;
    public static int TYPE_MULTIPLE_MENU = 2;

    private List<T> selectItems = new ArrayList<>();
    private int position = -1;
    private Builder builder;

    private MenuDialog(Context context) {
        super(context);
    }

    private MenuDialog(Context context, Builder builder) {
        this(context);
        this.builder = builder;
        if (ScreenHelper.isLandscape(context)) {
            //横屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
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
        rvDialogContent = findViewById(R.id.thomas_rv_content);
        tvDialogOk = findViewById(R.id.thomas_btn_ok);
        viewDialogDividerVertical = findViewById(R.id.thomas_divider_vertical);
        viewDialogDividerHorizontal = findViewById(R.id.thomas_divider_horizontal);
        viewDialogDividerTitle = findViewById(R.id.thomas_divider_title);
        tvDialogCancel = findViewById(R.id.thomas_btn_cancel);


        if (builder.dialogType == TYPE_ONLY_MENU) {
            tvDialogTitle.setVisibility(View.GONE);
            viewDialogDividerTitle.setVisibility(View.GONE);
            tvDialogCancel.setVisibility(View.GONE);
            tvDialogOk.setVisibility(View.GONE);
            viewDialogDividerVertical.setVisibility(View.GONE);
            viewDialogDividerHorizontal.setVisibility(View.INVISIBLE);
        }

        if (TextUtils.isEmpty(builder.title)) {
            tvDialogTitle.setVisibility(View.GONE);
            viewDialogDividerTitle.setVisibility(View.GONE);
        } else {
            tvDialogTitle.setVisibility(View.VISIBLE);
            tvDialogTitle.setText(builder.title);
        }

        tvDialogCancel.setText(TextUtils.isEmpty(builder.cancel) ? getContext().getString(android.R.string.cancel) : builder.cancel);
        tvDialogOk.setText(TextUtils.isEmpty(builder.ok) ? getContext().getString(android.R.string.ok) : builder.ok);
        ClickHelper.applySingleDebouncing(tvDialogCancel,v -> {
            dismiss();
            builder.items.clear();
            builder.items.addAll(builder.temps);
        });

        if (builder.dialogType != TYPE_ONLY_MENU) {

            ClickHelper.applySingleDebouncing(tvDialogOk,v -> {
                dismiss();
                for (int i = 0; i < builder.items.size(); i++) {
                    if (((T) builder.items.get(i)).getChoice()) {
                        if (builder.dialogType == TYPE_SINGLE_MENU) {
                            position = i;
                        }
                        selectItems.add((T) builder.items.get(i));
                    }
                }
                if (builder.dialogType == TYPE_SINGLE_MENU && builder.onSingleClickListener != null&&position!=-1) {
                    builder.onSingleClickListener.onClick(position, selectItems.get(0).getKey(), selectItems.get(0).getValue());
                }
                if (builder.dialogType == TYPE_MULTIPLE_MENU && builder.onMultipleClickListener != null) {
                    builder.onMultipleClickListener.onClick(selectItems);
                }

            });


        }


        DialogMenuAdapter adapter = new DialogMenuAdapter(builder.dialogType);

        rvDialogContent.setAdapter(adapter);
        rvDialogContent.setLayoutManager(RecyclerViewHelper.getDefaultLayoutManager(getContext()));
        rvDialogContent.addItemDecoration(RecyclerViewHelper.getDefaultItemDecoration(getContext()));
        adapter.setNewData(builder.items);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AbsKV clickBean = (AbsKV) adapter.getData().get(position);
                if (builder.dialogType == TYPE_ONLY_MENU) {
                    if (builder.onSingleClickListener != null) {
                        builder.onSingleClickListener.onClick(position, clickBean.getKey(), clickBean.getValue());
                    }
                    dismiss();
                } else {
                    if (builder.dialogType == TYPE_MULTIPLE_MENU) {
                        clickBean.setChoice(!clickBean.getChoice());
                    } else {
                        //单选的逻辑
                        for (int i = 0; i < adapter.getData().size(); i++) {
                            ((AbsKV) adapter.getData().get(i)).setChoice(i == position);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_menu_dialog);
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
        setPopupGravity(Gravity.CENTER);
        setClipChildren(false);
    }

    public static class Builder<T extends AbsKV> {
        private Context context;
        private int dialogType = TYPE_ONLY_MENU;
        private String title, cancel, ok;
        private List<T> items;
        private List<T> temps = new ArrayList<>();
        private OnSingleClickListener onSingleClickListener;
        private OnMultipleClickListener onMultipleClickListener;

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

        public Builder setItems(List<T> datas) {
            this.items = datas;
            for (T t : datas) {
                temps.add((T) t.clone());
            }
            return this;
        }

        public Builder setCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setSure(String ok) {
            this.ok = ok;
            return this;
        }

        public Builder setOnSureClickListener(OnSingleClickListener onSingleClickListener) {
            this.onSingleClickListener = onSingleClickListener;
            return this;
        }

        public Builder setOnSureClickListener(OnMultipleClickListener onMultipleClickListener) {
            this.onMultipleClickListener = onMultipleClickListener;
            return this;
        }

        public MenuDialog build() {
            return new MenuDialog(context, this);
        }
    }

    private class DialogMenuAdapter<T extends AbsKV> extends BaseQuickAdapter<T, BaseViewHolder> {
        private int dialogType;
        private static final int SELECT_PAYLOAD = 110;

        public DialogMenuAdapter(int dialogType) {
            super(R.layout.item_view_menu_dialog);
            this.dialogType = dialogType;
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {
            if (dialogType == TYPE_ONLY_MENU) {
                helper.findView(R.id.thomas_iv_item_state).setVisibility(View.INVISIBLE);
            } else {
                helper.findView(R.id.thomas_iv_item_state).setVisibility(View.VISIBLE);
            }
            helper.setText(R.id.thomas_tv_item_name, item.getKey());
            changeState(helper, item, false);
        }

        @Override
        protected void convert(BaseViewHolder helper, T item, List<?> payloads) {
            for (Object payload : payloads) {
                if (payload instanceof Integer && (int) payload == SELECT_PAYLOAD) {
                    // 增量刷新，使用动画变化箭头
                    changeState(helper, item, true);
                }
            }
        }


        private void changeState(BaseViewHolder helper, AbsKV item, boolean isAnimate) {
            if (dialogType != TYPE_ONLY_MENU) {
                if (item.getChoice()) {
                    helper.setImageResource(R.id.thomas_iv_item_state, R.drawable.thomas_shape_selected);
                } else {
                    helper.setImageResource(R.id.thomas_iv_item_state, R.drawable.thomas_shape_unselected);
                }
            }
        }
    }


}
