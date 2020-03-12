package com.thomas.ui.listener;

import com.thomas.ui.entity.AbsKV;

import java.util.List;

public interface OnMultipleClickListener<T extends AbsKV> {
    void onClick(List<T> selectItems);
}
