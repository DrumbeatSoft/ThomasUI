package com.thomas.ui.demo.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ColorUtils;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoLazyFragment;
import com.thomas.ui.demo.component.DialogActivity;
import com.thomas.ui.demo.component.TitleBarActivity;

import butterknife.BindView;

public class IndexFragment extends DemoLazyFragment {

    @BindView(R.id.btn_dialog)
    AppCompatButton btnDialog;
    @BindView(R.id.btn_title_bar)
    AppCompatButton btnTitleBar;

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
        applyThomasClickListener(btnTitleBar,btnDialog);
    }

    @Override
    protected void onFirstUserVisible() {
        mContentView.setBackgroundColor(ColorUtils.getRandomColor());
    }

    @Override
    public void onThomasClick(@NonNull View view) {
        if (view==btnTitleBar){
            ActivityUtils.startActivity(TitleBarActivity.class);
        }
        if (view == btnDialog) {
            ActivityUtils.startActivity(DialogActivity.class);
        }

    }
}
