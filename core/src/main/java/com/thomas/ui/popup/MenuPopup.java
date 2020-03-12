package com.thomas.ui.popup;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.R;
import com.thomas.ui.dialog.AbsKV;
import com.thomas.ui.helper.RecyclerViewHelper;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnSingleClickListener;

import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;

/**
 * 菜单popupWindow
 */
public class MenuPopup extends BaseLazyPopupWindow {
    private Builder builder;
    private RecyclerView rvDialogContent;

    public MenuPopup(Context context) {
        super(context);
    }

    private MenuPopup(Context context, Builder builder) {
        this(context);
        this.builder = builder;


        if (ScreenHelper.isLandscape(context)) {
            //横屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
            setMaxWidth(ScreenHelper.getScreenWidth(context) / 4);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 4);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 4);
        } else {
            //竖屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
            setMaxWidth(ScreenHelper.getScreenWidth(context) / 2);
            setMinWidth((ScreenHelper.getScreenWidth(context) / 3) * 2);
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_menu_popup);
    }


    @Override
    public void showPopupWindow(View anchorView) {
        setOffsetX(anchorView.getWidth());
        setAlignBackground(false);
        setAutoLocatePopup(true);
        super.showPopupWindow(anchorView);

    }


    @Override
    public void onAnchorTop() {
        super.onAnchorTop();
        Toast.makeText(getContext(), "onAnchorTop", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnchorBottom() {
        super.onAnchorBottom();
        Toast.makeText(getContext(), "onAnchorBottom", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onViewCreated(View contentView) {
        rvDialogContent = findViewById(R.id.rv_dialog_content);

        DialogMenuAdapter adapter = new DialogMenuAdapter();

        rvDialogContent.setAdapter(adapter);
        rvDialogContent.setLayoutManager(RecyclerViewHelper.getDefaultLayoutManager(getContext()));
        rvDialogContent.addItemDecoration(RecyclerViewHelper.getDefaultItemDecoration(getContext()));
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
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation(false));
        return set;
    }

    public static class Builder<T extends AbsKV> {
        private Context context;
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

        public MenuPopup build() {
            return new MenuPopup(context, this);
        }
    }

    private class DialogMenuAdapter<T extends AbsKV> extends BaseQuickAdapter<T, BaseViewHolder> {

        public DialogMenuAdapter() {
            super(R.layout.item_view_menu_popup);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, T item) {
            helper.setText(R.id.thomas_tv_item_name, item.getKey());
        }
    }
}
