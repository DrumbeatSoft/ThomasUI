package com.thomas.ui.demo.component;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.core.utils.TimeUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.ui.ThomasTitleBar;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.adapter.ItemAdapter;
import com.thomas.ui.demo.base.DemoActivity;
import com.thomas.ui.demo.entity.MenuBean;
import com.thomas.ui.listener.OnDateClickListener;
import com.thomas.ui.listener.OnMultipleClickListener;
import com.thomas.ui.listener.OnSearchClickListener;
import com.thomas.ui.listener.OnSingleClickListener;
import com.thomas.ui.quick.ThomasDateDialog;
import com.thomas.ui.quick.ThomasListDialog;
import com.thomas.ui.quick.ThomasMessageDialog;
import com.thomas.ui.quick.ThomasPopup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class DialogActivity extends DemoActivity {

    @BindView(R.id.thomasTitleBar)
    ThomasTitleBar thomasTitleBar;
    @BindView(R.id.rv_dialog)
    RecyclerView rvDialog;

    private ItemAdapter adapter;
    private List<String> datas = new ArrayList<>();
    private List<MenuBean> lessDatas = new ArrayList<>();
    private List<MenuBean> manyDatas = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_dialog;
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
                ThomasPopup.showMenu(thomasTitleBar.getRightTextView(), manyDatas, new OnSingleClickListener() {
                    @Override
                    public void onClick(int position, String key, String value) {
                        ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);
                    }
                });
            }
        });

        datas.add("提示弹窗\n(强提示)");
        datas.add("提示弹窗\n(有交互)");
        datas.add("提示弹窗\n(有两个按钮交互)");
        datas.add("消息弹窗\n(有两个按钮,只有一个有交互)");
        datas.add("消息弹窗\n(带有标题的,有两个按钮,只有一个有交互)");
        datas.add("消息弹窗\n(带有标题的,有两个按钮交互)");
        datas.add("提示弹窗\n(有很长的内容)");
        datas.add("消息弹窗\n(有很长的内容)");
        datas.add("与View关联的弹窗");
        datas.add("菜单弹窗\n(数据比较少)");
        datas.add("菜单弹窗\n(有很多数据)");
        datas.add("与View关联的弹窗\n（带搜索）");
        datas.add("单选弹窗\n(数据比较少)");
        datas.add("单选弹窗\n(有很多数据)");
        datas.add("单选弹窗\n(带标题和自定义按钮文字的)");
        datas.add("多选弹窗\n(数据比较少)");
        datas.add("多选弹窗\n(有很多数据)");
        datas.add("多选弹窗\n(带标题和自定义按钮文字的)");
        datas.add("日期弹窗\n(带标题)");
        datas.add("日期弹窗\n(不带标题,有“日”选项)");
        adapter = new ItemAdapter(datas);
        rvDialog.setLayoutManager(new GridLayoutManager(mActivity, 3));
        rvDialog.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    ThomasMessageDialog.showSimpleMessage(mActivity, "提示弹窗，点击按钮之后弹窗消失,没有任何响应事件，沒有标题，内容显示居中");
                }
                if (position == 1) {
                    ThomasMessageDialog.showSimpleMessage(mActivity,
                            "提示弹窗，沒有标题，内容显示居中，可以自定义按钮的文字和响应事件",
                            "自定义文字", () -> ToastUtils.showShort("响应了按钮的点击事件"));
                }
                if (position == 2) {
                    ThomasMessageDialog.showMessage(mActivity,
                            "提示弹窗，沒有标题，内容显示居右，有两个按钮，可以自定义按钮的文字和响应事件",
                            "自定义文字\n(确定)", () -> ToastUtils.showShort("响应了确定按钮的点击事件"),
                            "自定义文字\n(取消)", () -> ToastUtils.showShort("响应了取消按钮的点击事件"));
                }

                if (position == 3) {
                    ThomasMessageDialog.showMessage(mActivity, "我是消息弹窗的内容",
                            "自定义按钮", () -> ToastUtils.showShort("响应了按钮的点击事件"));
                }
                if (position == 4) {
                    ThomasMessageDialog.showMessage(mActivity, "我是消息弹窗的标题", "我是消息弹窗的内容",
                            "自定义按钮", () -> ToastUtils.showShort("响应了按钮的点击事件"));
                }
                if (position == 5) {
                    ThomasMessageDialog.showMessage(mActivity, "我是消息弹窗的标题", "我是消息弹窗的内容",
                            "自定义按钮\n(确定)", () -> ToastUtils.showShort("响应了确定按钮的点击事件"),
                            "自定义文字\n(取消)", () -> ToastUtils.showShort("响应了取消按钮的点击事件"));
                }

                if (position == 6) {
                    ThomasMessageDialog.showSimpleMessage(mActivity, "我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容，我是很长的提示弹窗的内容。");

                }

                if (position == 7) {
                    ThomasMessageDialog.showMessage(mActivity, "我是消息弹窗的标题", "我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容，我是很长的消息弹窗的内容。",
                            "自定义按钮\n(确定)", () -> ToastUtils.showShort("响应了确定按钮的点击事件"),
                            "自定义文字\n(取消)", () -> ToastUtils.showShort("响应了取消按钮的点击事件"));
                }

                if (position == 8) {
                    ThomasPopup.showMenu(view, lessDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    });
                }
                if (position == 9) {
                    ThomasListDialog.showMenu(mActivity, lessDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    });

                }

                if (position == 10) {
                    ThomasListDialog.showMenu(mActivity, manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    });

                }
                if (position == 11) {
                    ThomasPopup.showSingleWithSearch(view, "输入关键字", manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("点击了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    }, new OnSearchClickListener() {
                        @Override
                        public void onClick(String key, BaseQuickAdapter adapter) {
                            ToastUtils.showShort(key);
                            adapter.setNewInstance(manyDatas);
                        }
                    });
                }

                if (position == 12) {
                    ThomasListDialog.showSingle(mActivity, lessDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("选择了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    });

                }

                if (position == 13) {
                    ThomasListDialog.showSingle(mActivity, "有许多选项", manyDatas, new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("选择了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    });

                }

                if (position == 14) {
                    ThomasListDialog.showSingle(mActivity, "单选标题", lessDatas, "就他了", "再想想", new OnSingleClickListener() {
                        @Override
                        public void onClick(int position, String key, String value) {
                            ToastUtils.showLong("选择了第" + position + "条数据，key=" + key + ",value=" + value);
                        }
                    });

                }

                if (position == 15) {
                    ThomasListDialog.showMultiple(mActivity, lessDatas, new OnMultipleClickListener<MenuBean>() {
                        @Override
                        public void onClick(List<Integer> positions, List<MenuBean> selectItems) {
                            ToastUtils.showLong("共选择了" + selectItems.size() + "条数据");
                        }

                    });

                }

                if (position == 16) {
                    List<Integer> pos = new ArrayList<>();
                    pos.add(0);
                    pos.add(4);
                    ThomasListDialog.showMultiple(mActivity, "有许多选项", manyDatas, pos,new OnMultipleClickListener<MenuBean>() {
                        @Override
                        public void onClick(List<Integer> positions, List<MenuBean> selectItems) {
                            ToastUtils.showLong("共选择了" + selectItems.size() + "条数据");
                        }
                    });

                }

                if (position == 17) {
                    ThomasListDialog.showMultiple(mActivity, "多选标题", lessDatas, "就他们了", "再想想", new OnMultipleClickListener<MenuBean>() {
                        @Override
                        public void onClick(List<Integer> positions, List<MenuBean> selectItems) {
                            ToastUtils.showLong("共选择了" + TextUtils.join(",", positions) + "--" + selectItems.size() + "条数据");
                        }
                    });

                }

                if (position == 18) {
                    ThomasDateDialog.showDateDialog(mActivity, "选择时间", false, new OnDateClickListener() {
                        @Override
                        public void onClick(Date selectDate) {
                            ToastUtils.showLong("选择了" + TimeUtils.date2String(selectDate, "yyyy-MM"));
                        }
                    });

                }

                if (position == 19) {
                    ThomasDateDialog.showDateDialog(mActivity, null, TimeUtils.string2Date("2020-02-02", "yyyy-MM-dd"), true, new OnDateClickListener() {
                        @Override
                        public void onClick(Date selectDate) {
                            ToastUtils.showLong("选择了" + TimeUtils.date2String(selectDate, "yyyy-MM-dd"));
                        }
                    });

                }
            }
        });
    }

    @Override
    public void doBusiness() {
        for (int i = 0; i < 4; i++) {
            lessDatas.add(new MenuBean("thomas_" + i, "00" + i));
        }

        for (int i = 0; i < 12000; i++) {
            manyDatas.add(new MenuBean("thomas_" + i, "00" + i));
        }
    }

}
