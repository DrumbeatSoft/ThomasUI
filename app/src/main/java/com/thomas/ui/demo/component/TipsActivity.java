package com.thomas.ui.demo.component;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.ui.ThomasDialog;
import com.thomas.ui.ThomasTips;
import com.thomas.ui.ThomasTitleBar;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.adapter.ItemAdapter;
import com.thomas.ui.demo.base.DemoActivity;

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
                ThomasDialog.showTips(mActivity,
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
        datas.add("弹出完成状态框(自定义文字)");
        datas.add("弹出错误状态框");
        datas.add("弹出错误状态框(自定义文字)");
        datas.add("弹出警告状态框");
        datas.add("弹出警告状态框(自定义文字)");
        datas.add("弹出加载状态框");
        datas.add("弹出加载状态框(自定义文字)");
        datas.add("弹出自定义状态框");
        datas.add("弹出自定义状态框(只有图片)");

        adapter = new ItemAdapter(datas);
        rvTips.setLayoutManager(new GridLayoutManager(mActivity,2));
        rvTips.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (position == 0) {
                    ThomasTips.showSuccess(mActivity);
                }
                if (position == 1) {
                    ThomasTips.showSuccess(mActivity, "自定义文字");
                }
                if (position == 2) {
                    ThomasTips.showError(mActivity);
                }
                if (position == 3) {
                    ThomasTips.showError(mActivity, "自定义文字");
                }
                if (position == 4) {
                    ThomasTips.showWarn(mActivity);
                }
                if (position == 5) {
                    ThomasTips.showWarn(mActivity, "自定义文字");
                }
                if (position == 6) {
                    ThomasTips.showLoading(mActivity);
                }
                if (position == 7) {
                    ThomasTips.showLoading(mActivity, "自定义文字");
                }
                if (position == 8) {
                    ThomasTips.showCustom(mActivity, R.mipmap.ic_launcher, "自定义文字");
                }
                if (position == 9) {
                    ThomasTips.showCustom(mActivity, R.mipmap.ic_launcher);
                }
            }
        });
    }

    @Override
    public void doBusiness() {

    }
}
