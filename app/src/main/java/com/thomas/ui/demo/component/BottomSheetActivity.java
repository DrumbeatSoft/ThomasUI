package com.thomas.ui.demo.component;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.ui.ThomasBottomSheet;
import com.thomas.ui.ThomasDialog;
import com.thomas.ui.ThomasTitleBar;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.adapter.ItemAdapter;
import com.thomas.ui.demo.base.DemoActivity;
import com.thomas.ui.demo.entity.MenuBean;
import com.thomas.ui.dialog.OnSingleClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BottomSheetActivity extends DemoActivity {

    @BindView(R.id.thomasTitleBar)
    ThomasTitleBar thomasTitleBar;
    @BindView(R.id.rv_bottom_sheet)
    RecyclerView rvBottomSheet;

    private ItemAdapter adapter;
    private List<String> datas = new ArrayList<>();
    private List<MenuBean> lessDatas = new ArrayList<>();
    private List<MenuBean> manyDatas = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_bottom_sheet;
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

        datas.add("弹出底部列表(居左显示)");
        datas.add("弹出底部列表(多数据)");
        datas.add("弹出底部列表(带标题)");
        datas.add("弹出底部列表(带取消)");
        datas.add("弹出底部列表(带标题和取消)");
        datas.add("弹出警告状态框(自定义文字)");
        datas.add("弹出加载状态框");
        datas.add("弹出加载状态框(自定义文字)");
        datas.add("弹出自定义状态框");
        datas.add("弹出自定义状态框(只有图片)");

        adapter = new ItemAdapter(datas);
        rvBottomSheet.setLayoutManager(new GridLayoutManager(mActivity, 2));
        rvBottomSheet.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (position == 0) {
                    ThomasBottomSheet.showBottomSheetNormal(mActivity, Gravity.START, lessDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);

                        }
                    });
                }
                if (position == 1) {
                    ThomasBottomSheet.showBottomSheetNormal(mActivity, manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);

                        }
                    });
                }
                if (position == 2) {
                    ThomasBottomSheet.showBottomSheetWithTitle(mActivity, "测试标题", manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);

                        }
                    });
                }
                if (position == 3) {
                    ThomasBottomSheet.showBottomSheetWithCancel(mActivity, "取消文字", manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);

                        }
                    });
                }
                if (position == 4) {
                    ThomasBottomSheet.showBottomSheetAll(mActivity, "测试标题","取消文字", manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);

                        }
                    });
                }
                if (position == 5) {
                }
                if (position == 6) {
                }
                if (position == 7) {
                }
                if (position == 8) {
                }
                if (position == 9) {
                }
            }
        });
    }

    @Override
    public void doBusiness() {
        for (int i = 0; i < 4; i++) {
            lessDatas.add(new MenuBean("thomas_" + i, "00" + i));
        }

        for (int i = 0; i < 80; i++) {
            manyDatas.add(new MenuBean("thomas_" + i, "00" + i));
        }
    }
}
