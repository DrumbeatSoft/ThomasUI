package com.thomas.ui.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.thomas.ui.R;
import com.thomas.ui.photo.PhotoView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.util.animation.AlphaConfig;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;

public class PhotoPreview extends BaseLazyPopupWindow {

    private Builder builder;
    private ViewPager2 vpContent;
    private AppCompatTextView tvTag;

    private PhotoPreview(Context context) {
        super(context);
    }

    public PhotoPreview(Context context, Builder builder) {
        this(context);
        this.builder = builder;
        setOutSideDismiss(true);
        setOutSideTouchable(true);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_photo_preview);
    }

    @Override
    public void onViewCreated(View contentView) {
        vpContent = findViewById(R.id.vp_content);
        tvTag = findViewById(R.id.tv_tag);
        vpContent.setAdapter(new PhotoAdapter(builder.pictures));
        if (builder.pictures.size() == 1) {
            tvTag.setVisibility(View.GONE);
        } else {
            tvTag.setVisibility(View.VISIBLE);
        }
        vpContent.setCurrentItem(builder.position, false);
        vpContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvTag.setText((position + 1) + "/" + builder.pictures.size());
            }

            @Override
            public void onPageSelected(int position) {
                tvTag.setText((position + 1) + "/" + builder.pictures.size());
            }
        });
    }

    @Override
    public void showPopupWindow() {
        super.showPopupWindow();
        setOverlayStatusbar(true);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation().withAlpha(AlphaConfig.OUT).toDismiss();
    }


    public static class Builder {
        private Context context;
        private List<String> pictures = new ArrayList<>();
        private int position;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setPictures(List<String> pictures) {
            this.pictures = pictures;
            return this;
        }

        public Builder setPictures(String picture) {
            this.pictures.add(picture);
            return this;
        }

        public Builder setCurrent(int position) {
            this.position = position;
            return this;
        }

        public PhotoPreview build() {
            return new PhotoPreview(context, this);
        }


    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

        List<String> pictures;

        public PhotoAdapter(List<String> pictures) {
            this.pictures = pictures;
        }

        @NonNull
        @Override
        public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_photo, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
            Glide.with(holder.itemView).load(pictures.get(position)).into(holder.photoView);
            holder.photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return pictures.size();
        }

        class PhotoViewHolder extends RecyclerView.ViewHolder {
            PhotoView photoView;

            public PhotoViewHolder(@NonNull View itemView) {
                super(itemView);
                photoView = itemView.findViewById(R.id.photo_view);
            }
        }
    }
}
