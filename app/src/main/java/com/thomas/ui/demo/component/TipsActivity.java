package com.thomas.ui.demo.component;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.ui.ThomasTitleBar;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.adapter.ItemAdapter;
import com.thomas.ui.demo.base.DemoActivity;
import com.thomas.ui.quick.ThomasMessageDialog;
import com.thomas.ui.quick.ThomasTipsDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TipsActivity extends DemoActivity {
    @BindView(R.id.thomasTitleBar)
    ThomasTitleBar thomasTitleBar;
    @BindView(R.id.rv_tips)
    RecyclerView rvTips;

    private ItemAdapter adapter;
    private List<String> datas = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_tips;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

        thomasTitleBar.setListener((v, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }

            if (action == ThomasTitleBar.ACTION_RIGHT_TEXT) {
                ThomasMessageDialog.showSimpleMessage(mActivity,
                        "切换屏幕方向，来看一下弹窗的展示效果",
                        "切换", () -> {

                            if (ScreenUtils.isPortrait()) {
                                ScreenUtils.setLandscape(mActivity);
                            } else {
                                ScreenUtils.setPortrait(mActivity);
                            }
                        });
            }
        });

        datas.add("弹出完成状态框");
        datas.add("弹出完成状态框\n(自定义文字)");
        datas.add("弹出错误状态框");
        datas.add("弹出错误状态框\n(自定义文字)");
        datas.add("弹出警告状态框");
        datas.add("弹出警告状态框\n(自定义文字)");
        datas.add("弹出加载状态框");
        datas.add("弹出加载状态框\n(自定义文字)");
        datas.add("弹出自定义状态框");
        datas.add("弹出自定义状态框\n(只有图片)");
        datas.add("弹出多个状态框");

        adapter = new ItemAdapter(datas);
        rvTips.setLayoutManager(new GridLayoutManager(mActivity, 2));
        rvTips.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (position == 0) {
                    ThomasTipsDialog.showSuccess(mActivity);
                }
                if (position == 1) {
                    ThomasTipsDialog.showSuccess(mActivity, "自定义文字");
                }
                if (position == 2) {
                    ThomasTipsDialog.showError(mActivity);
                }
                if (position == 3) {
                    ThomasTipsDialog.showError(mActivity, "自定义文字");
                }
                if (position == 4) {
                    ThomasTipsDialog.showWarn(mActivity);
                }
                if (position == 5) {
                    ThomasTipsDialog.showWarn(mActivity, "自定义文字");
                }
                if (position == 6) {
                    ThomasTipsDialog.showLoading(mActivity);
                }
                if (position == 7) {
                    ThomasTipsDialog.showLoading(mActivity, "自定义文字");
                }
                if (position == 8) {
                    ThomasTipsDialog.showCustom(mActivity, R.mipmap.ic_launcher, "自定义文字");
                }
                if (position == 9) {
                    ThomasTipsDialog.showCustom(mActivity, R.mipmap.ic_launcher);
                }
                if (position == 10) {
                    ThomasTipsDialog.showLoading(mActivity, "自定义文字");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ThomasTipsDialog.showWarn(mActivity);
                        }
                    }, 500);
                }
            }
        });
    }

    @Override
    public void doBusiness() {

    }
}
