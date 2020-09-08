package com.thomas.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.appcompat.widget.AppCompatTextView;

import com.thomas.ui.R;
import com.thomas.ui.date.DateType;
import com.thomas.ui.date.WheelView;
import com.thomas.ui.helper.ClickHelper;
import com.thomas.ui.helper.ScreenHelper;
import com.thomas.ui.listener.OnDateClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;

public class DateDialog extends BaseLazyPopupWindow {


    private AppCompatTextView tvDialogTitle, tvDialogCancel, tvDialogOk;

    private WheelView wvDay, wvYear, wvMonth;

    private View viewDialogDivider;

    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;

    private Builder builder;


    private DateDialog(Context context) {
        super(context);
    }


    private DateDialog(Context context, Builder builder) {
        this(context);
        this.builder = builder;

        if (ScreenHelper.isLandscape(context)) {
            //横屏
            setMaxHeight((ScreenHelper.getScreenHeight(context) / 3) * 2);
            setMaxWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 4);
        } else {
            //竖屏
            setMaxHeight(ScreenHelper.getScreenHeight(context) / 3);
            setMaxWidth((ScreenHelper.getScreenWidth(context) / 3) * 2);
            setMinWidth(ScreenHelper.getScreenWidth(context) / 3);
            setMinHeight(ScreenHelper.getScreenHeight(context) / 4);
        }

    }

    @Override
    public void onViewCreated(View contentView) {
        tvDialogTitle = findViewById(R.id.thomas_tv_title);
        tvDialogCancel = findViewById(R.id.thomas_btn_cancel);
        tvDialogOk = findViewById(R.id.thomas_btn_ok);
        viewDialogDivider = findViewById(R.id.thomas_divider_horizontal);
        wvYear = findViewById(R.id.mpvDialogYear);
        wvMonth = findViewById(R.id.mpvDialogMonth);
        wvDay = findViewById(R.id.mpvDialogDay);

        if (TextUtils.isEmpty(builder.title)) {
            tvDialogTitle.setVisibility(View.GONE);
            viewDialogDivider.setVisibility(View.GONE);
        } else {
            tvDialogTitle.setVisibility(View.VISIBLE);
            tvDialogTitle.setText(builder.title);
        }

        tvDialogCancel.setText(TextUtils.isEmpty(builder.cancel) ? getContext().getString(android.R.string.cancel) : builder.cancel);
        tvDialogOk.setText(TextUtils.isEmpty(builder.ok) ? getContext().getString(android.R.string.ok) : builder.ok);
        ClickHelper.applyPressedViewAlpha(tvDialogOk, tvDialogCancel);

        if (builder.hasDay) {
            wvDay.setVisibility(View.VISIBLE);
        } else {
            wvDay.setVisibility(View.GONE);
        }

        ClickHelper.applySingleDebouncing(tvDialogOk, v -> {
            dismiss();
            if (builder.onDateClickListener != null) {
                builder.onDateClickListener.onClick(getDate(mCurrentYear, mCurrentMonth, mCurrentDay));
            }

        });
        ClickHelper.applySingleDebouncing(tvDialogCancel, v -> dismiss());


        wvYear.setOnSelectListener((view, data) -> {
            mCurrentYear = Integer.valueOf(data);
            updateDay(mCurrentYear, mCurrentMonth);
        });
        wvMonth.setOnSelectListener((view, data) -> {
            mCurrentMonth = Integer.valueOf(data);
            updateDay(mCurrentYear, mCurrentMonth);
        });

        wvDay.setOnSelectListener((view, data) -> mCurrentDay = Integer.valueOf(data));
        Calendar mCalendar = Calendar.getInstance();
        List<String> mDataYear = new ArrayList<>();
        List<String> mDataMonth = new ArrayList<>();


        if (builder.startYear > builder.endYear) {
            throw new RuntimeException("startYear的值不能大于endYear的值");
        }

        for (int i = builder.startYear; i <= builder.endYear; i++) {
            mDataYear.add(String.valueOf(i));
        }

        if (builder.selectedDate != null) {
            mCalendar.setTime(builder.selectedDate);
        }

        wvYear.setData(mDataYear);
        mCurrentYear = mCalendar.get(Calendar.YEAR);
        wvYear.setDefaultValue(String.valueOf(mCurrentYear), DateType.YEAR, "0");
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                mDataMonth.add("0" + i);
            } else {
                mDataMonth.add(String.valueOf(i));
            }
        }
        wvMonth.setData(mDataMonth);
        mCurrentMonth = mCalendar.get(Calendar.MONTH) + 1;
        wvMonth.setDefaultValue(String.valueOf(mCurrentMonth), DateType.MONTH, "-1");

        if (builder.hasDay) {
            mCurrentDay = mCalendar.get(Calendar.DAY_OF_MONTH);
            updateDay(mCurrentYear, mCurrentMonth);
        } else {
            mCurrentDay = 1;
        }
    }

    private void updateDay(int year, int month) {
        List<String> mDataDay = new ArrayList<>();
        int daySize = getDayByYearMonth(year, month);
        addTimeData(mDataDay, daySize + 1, 32);
        wvDay.setData(mDataDay);
        wvDay.setDefaultValue(String.valueOf(mCurrentDay), DateType.DAY, "-1");
    }

    private int getDayByYearMonth(int year, int month) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month - 1);
        return mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void addTimeData(List<String> list, int size, int equal) {
        for (int i = 1; i < size; i++) {
            if (i < 10) {
                list.add("0" + i);
            } else if (i == equal) {
                list.add("00");
            } else {
                list.add(String.valueOf(i));
            }
        }
    }

    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        return calendar.getTime();
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_date_dialog);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toDismiss();
    }


    @Override
    public void showPopupWindow() {
        super.showPopupWindow();
        setClipChildren(false);
        setPopupGravity(Gravity.CENTER);
    }

    public static class Builder {
        private Context context;
        private String title;
        private int startYear;
        private int endYear;
        private Date selectedDate;
        private boolean hasDay;
        private String ok;
        private String cancel;
        private OnDateClickListener onDateClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setStartYear(int startYear) {
            this.startYear = startYear;
            return this;
        }

        public Builder setEndYear(int endYear) {
            this.endYear = endYear;
            return this;
        }

        public Builder setSelectedDate(Date selectedDate) {
            this.selectedDate = selectedDate;
            return this;
        }

        public Builder setCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setSure(String ok) {
            this.ok = ok;
            return this;
        }

        public Builder setOnDateClickListener(OnDateClickListener onDateClickListener) {
            this.onDateClickListener = onDateClickListener;
            return this;
        }

        public DateDialog build() {
            return new DateDialog(context, this);
        }

        public Builder supportDay(boolean hasDay) {
            this.hasDay = hasDay;
            return this;
        }
    }


}
