package com.thomas.ui.demo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.thomas.ui.demo.R;

import java.util.List;

public class ItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ItemAdapter(List<String> data) {
        super(R.layout.item_dialog, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.item_tv_title, s);
    }
}
