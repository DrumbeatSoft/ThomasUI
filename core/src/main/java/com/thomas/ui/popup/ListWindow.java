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
import androidx.appcompat.widget.AppCompatCheckedTextView;
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
import com.thomas.ui.helper.ClickHelper;
import com.thomas.ui.helper.RecyclerViewHelper;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSearchClickListener;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.util.KeyboardUtils;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;

public class ListWindow<T extends AbsKV> extends BaseLazyPopupWindow {
    private Builder builder;
    private AppCompatTextView tvDialogCancel, tvDialogOk;
    private LinearLayoutCompat llSearch;
    private AppCompatImageButton btnDialogSearch;
    private AppCompatEditText etDialogSearch;
    private RecyclerView rvDialogContent;
    private View viewDialogDividerVertical, viewDialogDividerHorizontal, viewDialogDividerTitle;


    public static int TYPE_SINGLE_MENU = 1;
    public static int TYPE_MULTIPLE_MENU = 2;

    private final HashMap<Integer, Object> mSelectSet = new HashMap<>();
    private List<T> selectItems = new ArrayList<>();
    private List<Integer> selectedPositions = new ArrayList<>();


    private ListWindow(Context context) {
        super(context);
    }

    public ListWindow(Context context, Builder builder) {
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
    public void dismiss() {
        KeyboardUtils.close(getContentView());
        super.dismiss();
    }

    @Override
    public void showPopupWindow(View anchorView) {
        setOffsetX(anchorView.getWidth());
        setAlignBackground(false);
        setAutoLocatePopup(false);
        setPopupFadeEnable(true);
//        setAdjustInputMethod(true, FLAG_KEYBOARD_IGNORE_OVER | FLAG_KEYBOARD_ANIMATE_ALIGN);//该设置导致黑鲨手机弹窗不显示，其他没遇到
        setAdjustInputMode(FLAG_KEYBOARD_IGNORE_OVER | FLAG_KEYBOARD_ANIMATE_ALIGN);//该设置黑鲨手机弹窗可显示
//        setAdjustInputMethod(true);//该设置黑鲨手机弹窗可显示
        super.showPopupWindow(anchorView);
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


        DialogMenuAdapter<T> menuAdapter = new DialogMenuAdapter(builder.dialogType);

        rvDialogContent.setAdapter(menuAdapter);
        rvDialogContent.setLayoutManager(RecyclerViewHelper.getDefaultLayoutManager(getContext()));
        rvDialogContent.addItemDecoration(RecyclerViewHelper.getDefaultItemDecoration(getContext()));
        menuAdapter.setNewInstance(builder.items);
        menuAdapter.setSelected(builder.positions);
        menuAdapter.setEmptyView(R.layout.view_default_empty);
        menuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                T clickBean = menuAdapter.getData().get(position);
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
        });


        etDialogSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mSelectSet.clear();
                    updateData(menuAdapter, filter(builder.items, etDialogSearch.getEditableText().toString()));
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
                mSelectSet.clear();
                updateData(menuAdapter, filter(builder.items, etDialogSearch.getEditableText().toString()));
            }
        });

        btnDialogSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectSet.clear();
                if (builder.onSearchClickListener != null) {
                    builder.onSearchClickListener.onClick(etDialogSearch.getEditableText().toString(), menuAdapter);
                } else {
                    updateData(menuAdapter, filter(builder.items, etDialogSearch.getEditableText().toString()));
                }
                KeyboardUtils.close(getContentView());
            }
        });
    }

    private void updateData(DialogMenuAdapter adapter, List filter) {
        adapter.setNewInstance(filter);
    }


    private List filter(List items, String s) {
        List results = new ArrayList<>();
        if (TextUtils.isEmpty(s)) {
            results.addAll(items);
        } else {
            Pattern p = Pattern.compile(s.toUpperCase());
            for (int i = 0; i < items.size(); i++) {
                AbsKV currentBean = (AbsKV) items.get(i);
                Matcher m = p.matcher(currentBean.getKey().toUpperCase());
                if (m.find()) {
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
        private List<Integer> positions;
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
            return this;
        }

        public Builder setSelected(List<Integer> positions) {
            if (positions == null) {
                positions = new ArrayList<>();
            }
            this.positions = positions;
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

        public ListWindow build() {
            return new ListWindow(context, this);
        }
    }


    private class DialogMenuAdapter<T extends AbsKV> extends BaseQuickAdapter<T, BaseViewHolder> {

        private int dialogType;

        public DialogMenuAdapter(int dialogType) {
            super(R.layout.item_view_menu_dialog);
            this.dialogType = dialogType;
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {
            AppCompatCheckedTextView ctv = helper.findView(R.id.thomas_ctv_item_name);

            if (dialogType == TYPE_SINGLE_MENU) {
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
