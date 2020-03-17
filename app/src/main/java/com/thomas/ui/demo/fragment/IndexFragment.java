package com.thomas.ui.demo.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ColorUtils;
import com.thomas.ui.ThomasPreview;
import com.thomas.ui.badge.Badge;
import com.thomas.ui.badge.BadgeView;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoLazyFragment;
import com.thomas.ui.demo.component.BottomSheetActivity;
import com.thomas.ui.demo.component.DialogActivity;
import com.thomas.ui.demo.component.TipsActivity;
import com.thomas.ui.demo.component.TitleBarActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexFragment extends DemoLazyFragment {

    @BindView(R.id.btn_dialog)
    AppCompatButton btnDialog;
    @BindView(R.id.btn_title_bar)
    AppCompatButton btnTitleBar;
    @BindView(R.id.btn_tips)
    AppCompatButton btnTips;
    @BindView(R.id.btn_bottom_sheet)
    AppCompatButton btnBottomSheet;

    BadgeView badgeView;
    @BindView(R.id.btn_photo_preview)
    AppCompatButton btnPhotoPreview;

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    private IndexFragment() {
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_index;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        applyThomasClickListener(btnTitleBar, btnDialog, btnTips, btnBottomSheet,btnPhotoPreview );


        badgeView = new BadgeView(mActivity);
        badgeView.bindTarget(btnDialog).setBadgeNumber(9).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                switch (dragState) {
                    case STATE_START:
                        break;
                    case STATE_DRAGGING:
                        break;
                    case STATE_DRAGGING_OUT_OF_RANGE:
                        break;
                    case STATE_SUCCEED:
                        badge.hide(true);
                        break;
                    case STATE_CANCELED:
                        break;
                }
            }
        });

    }

    @Override
    protected void onFirstUserVisible() {
        mContentView.setBackgroundColor(ColorUtils.getRandomColor());
    }

    @Override
    public void onThomasClick(@NonNull View view) {
        if (view == btnTitleBar) {
            ActivityUtils.startActivity(TitleBarActivity.class);
        }
        if (view == btnDialog) {
            badgeView.hide(true);
            ActivityUtils.startActivity(DialogActivity.class);
        }
        if (view == btnTips) {
            ActivityUtils.startActivity(TipsActivity.class);
        }
        if (view == btnBottomSheet) {
            ActivityUtils.startActivity(BottomSheetActivity.class);
        }

        if (view == btnPhotoPreview) {
            ThomasPreview.showPreview(mActivity, new ArrayList<>());
        }
    }
}
