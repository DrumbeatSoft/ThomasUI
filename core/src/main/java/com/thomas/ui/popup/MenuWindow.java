package com.thomas.ui.popup;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.R;
import com.thomas.ui.entity.AbsKV;
import com.thomas.ui.helper.RecyclerViewHelper;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSearchClickListener;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.basepopup.BasePopupWindow;

public class MenuWindow<T extends AbsKV> extends BaseLazyPopupWindow {
    private Builder builder;
    private AppCompatTextView tvDialogCancel, tvDialogOk;
    private LinearLayoutCompat llSearch;
    private AppCompatImageButton btnDialogSearch;
    private AppCompatEditText etDialogSearch;
    private RecyclerView rvDialogContent;
    private View viewDialogDividerVertical, viewDialogDividerHorizontal, viewDialogDividerTitle;


    public static int TYPE_SINGLE_MENU = 1;
    public static int TYPE_MULTIPLE_MENU = 2;


    private List<T> selectItems = new ArrayList<>();
    private int position = 0;

    private MenuWindow(Context context) {
        super(context);
    }

    public MenuWindow(Context context, Builder builder) {
        this(context);
        this.builder = builder;

        if (ScreenHelper.isLandscape(context)) {
            //横屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 4) * 3);
            setMaxWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 4);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 4);
        } else {
            //竖屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 3);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 2);
            setMaxWidth((ScreenHelper.getScreenWidth(context) / 4) * 3);
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_menu_window);
    }


    @Override
    public void showPopupWindow(View anchorView) {
        setOffsetX(anchorView.getWidth());
        setAlignBackground(false);
        setAutoLocatePopup(true);
        setPopupFadeEnable(true);
        setAdjustInputMethod(false);
        super.showPopupWindow(anchorView);

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
    public void onViewCreated(View contentView) {
        btnDialogSearch = findViewById(R.id.thomas_btn_search);
        etDialogSearch = findViewById(R.id.thomas_et_search);
        rvDialogContent = findViewById(R.id.thomas_rv_content);
        tvDialogOk = findViewById(R.id.thomas_btn_ok);
        viewDialogDividerVertical = findViewById(R.id.thomas_divider_vertical);
        viewDialogDividerHorizontal = findViewById(R.id.thomas_divider_horizontal);
        viewDialogDividerTitle = findViewById(R.id.thomas_divider_title);
        tvDialogCancel = findViewById(R.id.thomas_btn_cancel);
        llSearch = findViewById(R.id.thomas_ll_search);
        etDialogSearch.setHint(TextUtils.isEmpty(builder.hint) ? getContext().getString(R.string.thomasDefaultSearchHint) : builder.hint);

        tvDialogCancel.setText(TextUtils.isEmpty(builder.cancel) ? getContext().getString(android.R.string.cancel) : builder.cancel);
        tvDialogOk.setText(TextUtils.isEmpty(builder.ok) ? getContext().getString(android.R.string.ok) : builder.ok);

        if (builder.withSearch) {
            llSearch.setVisibility(View.VISIBLE);
            viewDialogDividerTitle.setVisibility(View.VISIBLE);
        } else {
            llSearch.setVisibility(View.GONE);
            viewDialogDividerTitle.setVisibility(View.GONE);
        }

        tvDialogCancel.setOnClickListener(v -> {
            dismiss();
            builder.items.clear();
            builder.items.addAll(builder.temps);
        });
        tvDialogOk.setOnClickListener(v -> {
            dismiss();
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


        DialogMenuAdapter adapter = new DialogMenuAdapter();

        rvDialogContent.setAdapter(adapter);
        rvDialogContent.setLayoutManager(RecyclerViewHelper.getDefaultLayoutManager(getContext()));
        rvDialogContent.addItemDecoration(RecyclerViewHelper.getDefaultItemDecoration(getContext()));
        adapter.setNewData(builder.items);
        adapter.setEmptyView(R.layout.view_default_empty);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AbsKV clickBean = (AbsKV) adapter.getData().get(position);

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
        });


        etDialogSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    updateData(adapter, filter(builder.items, etDialogSearch.getEditableText().toString()));
                }
                return false;
            }
        });

        etDialogSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateData(adapter, filter(builder.items, etDialogSearch.getEditableText().toString()));
            }
        });

        btnDialogSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.onSearchClickListener != null) {
                    builder.onSearchClickListener.onClick(etDialogSearch.getEditableText().toString(), adapter);
                } else {
                    updateData(adapter, filter(builder.items, etDialogSearch.getEditableText().toString()));
                }
            }
        });
    }

    private void updateData(DialogMenuAdapter adapter, List filter) {
        adapter.setNewData(filter);
    }


    private List filter(List items, String s) {
        List results = new ArrayList<>();
        if (TextUtils.isEmpty(s)) {
            results.addAll(items);
        } else {
            Pattern p = Pattern.compile(s);
            for (int i = 0; i < items.size(); i++) {
                AbsKV currentBean = (AbsKV) items.get(i);
                Matcher m = p.matcher(currentBean.getKey());
                if (m.find()) {
                    currentBean.setChoice(currentBean.getChoice());
                    results.add(currentBean);
                }
            }
        }
        return results;
    }

    public static class Builder<T extends AbsKV> {
        private Context context;
        private String hint, cancel, ok;
        private int dialogType = TYPE_SINGLE_MENU;
        private boolean withSearch = false;
        private List<T> items;
        private List<T> temps = new ArrayList<>();
        private OnSingleClickListener onSingleClickListener;
        private OnMultipleClickListener onMultipleClickListener;
        private OnSearchClickListener onSearchClickListener;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setDialogType(int dialogType) {
            this.dialogType = dialogType;
            return this;
        }


        public Builder setItems(List<T> datas) {
            this.items = datas;
            for (T t : datas) {
                temps.add((T) t.clone());
            }
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder withSearch(boolean withSearch) {
            this.withSearch = withSearch;
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

        public Builder setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
            this.onSearchClickListener = onSearchClickListener;
            return this;
        }

        public MenuWindow build() {
            return new MenuWindow(context, this);
        }
    }


    private class DialogMenuAdapter<T extends AbsKV> extends BaseQuickAdapter<T, BaseViewHolder> {
        private static final int SELECT_PAYLOAD = 110;

        public DialogMenuAdapter() {
            super(R.layout.item_view_menu_dialog);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {

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
            if (item.getChoice()) {
                helper.setImageResource(R.id.thomas_iv_item_state, R.drawable.thomas_shape_selected);
            } else {
                helper.setImageResource(R.id.thomas_iv_item_state, R.drawable.thomas_shape_unselected);
            }
        }
    }
}
