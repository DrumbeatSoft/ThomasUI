package com.thomas.ui.dialog;


import androidx.annotation.NonNull;

public abstract class AbsKV implements Cloneable {

    private boolean isChoice = false;

    protected abstract String getKey();

    protected abstract String getValue();

    void setChoice(boolean isChoice) {
        this.isChoice = isChoice;
    }

    boolean getChoice() {
        return isChoice;
    }

    @NonNull
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


}
