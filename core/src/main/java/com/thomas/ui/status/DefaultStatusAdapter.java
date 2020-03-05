package com.thomas.ui.status;

import android.view.View;

public class DefaultStatusAdapter implements TLoading.Adapter {
    @Override
    public View getView(TLoading.Holder holder, View convertView, int status) {
        DefalutStatusView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView != null && convertView instanceof DefalutStatusView) {
            loadingStatusView = (DefalutStatusView) convertView;
        }
        if (loadingStatusView == null) {
            loadingStatusView = new DefalutStatusView(holder.getContext(), holder.getRetryTask());
        }
        loadingStatusView.setStatus(status);
        return loadingStatusView;
    }
}
