package com.thomas.ui.photo;

import android.content.Context;
import android.view.View;

import com.thomas.ui.R;

import razerdp.basepopup.BaseLazyPopupWindow;

public class PhotoPreview extends BaseLazyPopupWindow {

    private Builder builder;

    private PhotoPreview(Context context) {
        super(context);
    }

    public PhotoPreview(Context context, Builder builder) {
        this(context);
        this.builder = builder;
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_photo_preview);
    }

    @Override
    public void onViewCreated(View contentView) {


    }

    @Override
    public void showPopupWindow(View anchorView) {
        super.showPopupWindow(anchorView);
        setPopupWindowFullScreen(true);
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public PhotoPreview build() {
            return new PhotoPreview(context, this);
        }

    }
}
