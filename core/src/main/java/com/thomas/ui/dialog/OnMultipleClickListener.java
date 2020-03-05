package com.thomas.ui.dialog;

import java.util.List;

public interface OnMultipleClickListener<T extends AbsKV> {
    void onClick(List<T> selectItems);
}
