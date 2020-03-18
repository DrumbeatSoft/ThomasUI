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
import java.util.List;

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
        applyThomasClickListener(btnTitleBar, btnDialog, btnTips, btnBottomSheet, btnPhotoPreview);


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
            List<String> images = new ArrayList<>();
            images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584521945350&di=aa55ffa00b79a45a0cca484ca4345ecd&imgtype=0&src=http%3A%2F%2Fcdn.qiancipai.com%2F190305170514872174.jpg");
           images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584521945350&di=63882d24f52d47c2edd1515b28adb3df&imgtype=0&src=http%3A%2F%2Ffile.digitaling.com%2FeImg%2Fuimages%2F20160512%2F1463021628937068.jpg");
            ThomasPreview.showPreview(mActivity, 2,images);
        }
    }
}
