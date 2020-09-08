package com.thomas.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;

/**
 * 菜单居中弹窗，有纯菜单模式，单选模式，多选模式。
 */
public class ListDialog<T extends AbsKV> extends BaseLazyPopupWindow {

    private AppCompatTextView tvDialogTitle, tvDialogCancel, tvDialogOk;
    private RecyclerView rvDialogContent;
    private View viewDialogDividerVertical, viewDialogDividerHorizontal, viewDialogDividerTitle;
    public static int TYPE_ONLY_MENU = 0;
    public static int TYPE_SINGLE_MENU = 1;
    public static int TYPE_MULTIPLE_MENU = 2;

    private final HashMap<Integer, Object> mSelectSet = new HashMap<>();
    private List<T> selectItems = new ArrayList<>();
    private List<Integer> selectedPositions = new ArrayList<>();
    private Builder builder;

    private ListDialog(Context context) {
        super(context);
    }

    private ListDialog(Context context, Builder builder) {
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
        ClickHelper.applyPressedViewAlpha(tvDialogCancel, tvDialogOk);

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
        ClickHelper.applySingleDebouncing(tvDialogCancel, v -> {
            mSelectSet.clear();
            dismiss();
        });


        ClickHelper.applySingleDebouncing(tvDialogOk, v -> {
            selectItems.clear();
            selectedPositions.clear();
            Iterator<Map.Entry<Integer, Object>> iterator = mSelectSet.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Object> entry = iterator.next();
                selectItems.add((T) entry.getValue());
                selectedPositions.add(entry.getKey());
            }
            if (selectedPositions != null && selectedPositions.size() != 0 && selectItems != null && selectItems.size() != 0) {
                if (builder.dialogType == TYPE_MULTIPLE_MENU && builder.onMultipleClickListener != null) {
                    builder.onMultipleClickListener.onClick(selectedPositions, selectItems);
                } else {
                    builder.onSingleClickListener.onClick(selectedPositions.get(0), selectItems.get(0).getKey(), selectItems.get(0).getValue());
                }
            } else {
                if (builder.dialogType == TYPE_MULTIPLE_MENU && builder.onMultipleClickListener != null) {
                    builder.onMultipleClickListener.onClick(selectedPositions, selectItems);
                }
            }
            dismiss();
        });

        DialogListAdapter<T> menuAdapter = new DialogListAdapter(builder.dialogType);
        rvDialogContent.setAdapter(menuAdapter);
        rvDialogContent.setLayoutManager(RecyclerViewHelper.getDefaultLayoutManager(getContext()));
        rvDialogContent.addItemDecoration(RecyclerViewHelper.getDefaultItemDecoration(getContext()));
        menuAdapter.setNewInstance(builder.items);
        menuAdapter.setSelected(builder.positions);
        menuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                T clickBean = menuAdapter.getData().get(position);
                if (builder.dialogType == TYPE_ONLY_MENU) {
                    if (builder.onSingleClickListener != null) {
                        builder.onSingleClickListener.onClick(position, clickBean.getKey(), clickBean.getValue());
                    }
                    dismiss();
                } else {
                    if (mSelectSet.containsKey(position)) {
                        if (builder.dialogType == TYPE_MULTIPLE_MENU) {
                            mSelectSet.remove(position);
                            adapter.notifyItemChanged(position);
                        }
                    } else {
                        if (builder.dialogType == TYPE_MULTIPLE_MENU) {
                            mSelectSet.put(position, clickBean);
                            adapter.notifyItemChanged(position);
                        } else {
                            mSelectSet.clear();
                            mSelectSet.put(position, clickBean);
                            adapter.notifyDataSetChanged();
                        }

                    }

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
        return AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toDismiss();
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
        private List<Integer> positions;
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
            return this;
        }

        public Builder setSelected(List<Integer> positions) {
            if (positions == null) {
                positions = new ArrayList<>();
            }
            this.positions = positions;
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

        public ListDialog build() {
            return new ListDialog(context, this);
        }
    }

    private class DialogListAdapter<T extends AbsKV> extends BaseQuickAdapter<T, BaseViewHolder> {
        private int dialogType;

        public DialogListAdapter(int dialogType) {
            super(R.layout.item_view_menu_dialog);
            this.dialogType = dialogType;
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {
            AppCompatCheckedTextView ctv = helper.findView(R.id.thomas_ctv_item_name);
            if (dialogType == TYPE_ONLY_MENU) {
                ctv.setCheckMarkDrawable(null);
            } else if (dialogType == TYPE_SINGLE_MENU) {
                ctv.setCheckMarkDrawable(R.drawable.thomas_single_choice_selector);
            } else {
                ctv.setCheckMarkDrawable(R.drawable.thomas_multiple_choice_selector);
            }
            ctv.setText(item.getKey());
            ctv.setChecked(mSelectSet.containsKey(helper.getAdapterPosition()));
        }

        public void setSelected(List<Integer> positions) {
            for (int position : positions) {
                mSelectSet.put(position, getItem(position));
            }
            notifyDataSetChanged();
        }
    }


}
