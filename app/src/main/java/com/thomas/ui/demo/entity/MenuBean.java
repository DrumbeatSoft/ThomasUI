package com.thomas.ui.demo.entity;

import com.thomas.ui.dialog.AbsKV;

public class MenuBean extends AbsKV {

    private String name;
    private String id;

    public MenuBean(String name, String id) {
        this.name = name;
        this.id = id;
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

    @Override
    protected String getKey() {
        return name;
    }

    @Override
    protected String getValue() {
        return id;
    }
}
