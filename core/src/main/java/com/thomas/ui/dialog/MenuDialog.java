package com.thomas.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.R;
import com.thomas.ui.helper.ScreenHelper;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * 菜单居中弹窗，有纯菜单模式，单选模式，多选模式。
 */
public class MenuDialog<T extends AbsKV> extends BasePopupWindow {

    private AppCompatTextView tvDialogTitle, tvDialogCancel, tvDialogOk;
    private RecyclerView rvDialogContent;
    private View viewDialogDividerVertical, viewDialogDividerHorizontal;
    public static int TYPE_ONLY_MENU = 0;
    public static int TYPE_SINGLE_MENU = 1;
    public static int TYPE_MULTIPLE_MENU = 2;

    private List<T> selectItems = new ArrayList<>();
    private int position = 0;

    private MenuDialog(Context context) {
        super(context);
    }

    public MenuDialog(Context context, Builder builder) {
        this(context);
        setPopupGravity(Gravity.CENTER);
        setClipChildren(false);
        tvDialogTitle = findViewById(R.id.thomas_tv_title);
        rvDialogContent = findViewById(R.id.thomas_rv_content);
        tvDialogOk = findViewById(R.id.thomas_btn_ok);
        viewDialogDividerVertical = findViewById(R.id.thomas_divider_vertical);
        viewDialogDividerHorizontal = findViewById(R.id.thomas_divider_horizontal);
        tvDialogCancel = findViewById(R.id.thomas_btn_cancel);

        if (builder.dialogType == TYPE_ONLY_MENU) {
            tvDialogTitle.setVisibility(View.GONE);
            tvDialogCancel.setVisibility(View.GONE);
            tvDialogOk.setVisibility(View.GONE);
            viewDialogDividerVertical.setVisibility(View.GONE);
            viewDialogDividerHorizontal.setVisibility(View.INVISIBLE);
        }

        if (TextUtils.isEmpty(builder.title)) {
            tvDialogTitle.setVisibility(View.GONE);
        } else {
            tvDialogTitle.setVisibility(View.VISIBLE);
            tvDialogTitle.setText(builder.title);
        }

        tvDialogCancel.setText(TextUtils.isEmpty(builder.cancel) ? getContext().getString(android.R.string.cancel) : builder.cancel);
        tvDialogOk.setText(TextUtils.isEmpty(builder.ok) ? getContext().getString(android.R.string.ok) : builder.ok);

        tvDialogCancel.setOnClickListener(v -> {
            dismiss();
            builder.items.clear();
            builder.items.addAll(builder.temps);
        });

        if (builder.dialogType != TYPE_ONLY_MENU) {

            tvDialogOk.setOnClickListener(v -> {
                dismissWithOutAnimate();
                for (int i = 0; i < builder.items.size(); i++) {
                    if (((T) builder.items.get(i)).getChoice()) {
                        if (builder.dialogType == TYPE_SINGLE_MENU) {
                            position = i;
                        }
                        selectItems.add((T) builder.items.get(i));
                    }
                }
                if (builder.dialogType == TYPE_SINGLE_MENU && builder.onSingleClickListener != null) {
                    builder.onSingleClickListener.onClick(position, selectItems.get(0).getKey(), selectItems.get(0).getValue());
                }
                if (builder.dialogType == TYPE_MULTIPLE_MENU && builder.onMultipleClickListener != null) {
                    builder.onMultipleClickListener.onClick(selectItems);
                }

            });


        }


        DialogMenuAdapter adapter = new DialogMenuAdapter(builder.items, builder.dialogType);

        rvDialogContent.setAdapter(adapter);
        rvDialogContent.setLayoutManager(new LinearLayoutManager(getContext()));
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


        if (ScreenHelper.isLandscape(getContext())) {
            //横屏
            setMaxHeight( ScreenHelper.getScreenHeight(getContext())/ 2);
            setMaxWidth(ScreenHelper.getScreenWidth(getContext()) / 3);
            setMinWidth(ScreenHelper.getScreenWidth(getContext()) / 3);
            setMinHeight(ScreenHelper.getScreenHeight(getContext()) / 4);
        } else {
            //竖屏
            setMaxHeight(ScreenHelper.getScreenHeight(getContext()) / 3);
            setMaxWidth((ScreenHelper.getScreenWidth(getContext()) / 3) * 2);
            setMinWidth(ScreenHelper.getScreenWidth(getContext()) / 3);
            setMinHeight(ScreenHelper.getScreenHeight(getContext()) / 4);
        }

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


        public DialogMenuAdapter(@Nullable List<T> data, int dialogType) {
            super(R.layout.item_view_menu_dialog, data);
            this.dialogType = dialogType;
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {
            if (dialogType == TYPE_ONLY_MENU) {
                helper.findView(R.id.thomas_iv_item_state).setVisibility(View.GONE);
            } else {
                helper.findView(R.id.thomas_iv_item_state).setVisibility(View.VISIBLE);
                if (item.getChoice()) {
                    helper.setImageResource(R.id.thomas_iv_item_state, R.drawable.thomas_shape_selected);
                } else {
                    helper.setImageResource(R.id.thomas_iv_item_state, R.drawable.thomas_shape_unselected);
                }
            }
            helper.setText(R.id.thomas_tv_item_name, item.getKey());
        }
    }
}
