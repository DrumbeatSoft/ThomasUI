package com.thomas.ui.demo.entity;

import com.thomas.ui.dialog.AbsKV;

public class MenuBean extends AbsKV {

    private String name;
    private String id;
    private int resId;

    public MenuBean(String name, String id) {
        this.name = name;
        this.id = id;
        this.resId = 0;
    }

    public MenuBean(String name, String id, int resId) {
        this.name = name;
        this.id = id;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public String getValue() {
        return id;
    }

    @Override
    protected int getResId() {
        return resId;
    }
}
