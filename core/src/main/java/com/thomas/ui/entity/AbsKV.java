package com.thomas.ui.entity;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public abstract class AbsKV implements Cloneable {

    private boolean isChoice = false;

    public abstract String getKey();

    public abstract String getValue();

    public abstract @DrawableRes int getResId();

    public void setChoice(boolean isChoice) {
        this.isChoice = isChoice;
    }

    public boolean getChoice() {
        return isChoice;
    }

    @NonNull
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


}