package com.demo.widget.view.picker;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;

import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.contrarywind.view.WheelView;

import java.util.Calendar;

/**
 * Created by xiaosongzeem on 2018/3/20.
 */

public class MyTimePickerBuilder {

    private PickerOptions mPickerOptions;

    //Required
    public MyTimePickerBuilder(Context context, OnTimeSelectListener listener) {
        mPickerOptions = new PickerOptions(PickerOptions.TYPE_PICKER_TIME);
        mPickerOptions.context = context;
        mPickerOptions.timeSelectListener = listener;
    }

    //Option
    public MyTimePickerBuilder setGravity(int gravity) {
        mPickerOptions.textGravity = gravity;
        return this;
    }

    public MyTimePickerBuilder addOnCancelClickListener(View.OnClickListener cancelListener) {
        mPickerOptions.cancelListener = cancelListener;
        return this;
    }

    /**
     * new boolean[]{true, true, true, false, false, false}
     * control the "year","month","day","hours","minutes","seconds " display or hide.
     * 分别控制“年”“月”“日”“时”“分”“秒”的显示或隐藏。
     *
     * @param type 布尔型数组，长度需要设置为6。
     * @return MyTimePickerBuilder
     */
    public MyTimePickerBuilder setType(boolean[] type) {
        mPickerOptions.type = type;
        return this;
    }

    public MyTimePickerBuilder setSubmitText(String textContentConfirm) {
        mPickerOptions.textContentConfirm = textContentConfirm;
        return this;
    }

    public MyTimePickerBuilder isDialog(boolean isDialog) {
        mPickerOptions.isDialog = isDialog;
        return this;
    }

    public MyTimePickerBuilder setCancelText(String textContentCancel) {
        mPickerOptions.textContentCancel = textContentCancel;
        return this;
    }

    public MyTimePickerBuilder setTitleText(String textContentTitle) {
        mPickerOptions.textContentTitle = textContentTitle;
        return this;
    }

    public MyTimePickerBuilder setSubmitColor(int textColorConfirm) {
        mPickerOptions.textColorConfirm = textColorConfirm;
        return this;
    }

    public MyTimePickerBuilder setCancelColor(int textColorCancel) {
        mPickerOptions.textColorCancel = textColorCancel;
        return this;
    }

    /**
     * ViewGroup 类型的容器
     *
     * @param decorView 选择器会被添加到此容器中
     * @return MyTimePickerBuilder
     */
    public MyTimePickerBuilder setDecorView(ViewGroup decorView) {
        mPickerOptions.decorView = decorView;
        return this;
    }

    public MyTimePickerBuilder setBgColor(int bgColorWheel) {
        mPickerOptions.bgColorWheel = bgColorWheel;
        return this;
    }

    public MyTimePickerBuilder setTitleBgColor(int bgColorTitle) {
        mPickerOptions.bgColorTitle = bgColorTitle;
        return this;
    }

    public MyTimePickerBuilder setTitleColor(int textColorTitle) {
        mPickerOptions.textColorTitle = textColorTitle;
        return this;
    }

    public MyTimePickerBuilder setSubCalSize(int textSizeSubmitCancel) {
        mPickerOptions.textSizeSubmitCancel = textSizeSubmitCancel;
        return this;
    }

    public MyTimePickerBuilder setTitleSize(int textSizeTitle) {
        mPickerOptions.textSizeTitle = textSizeTitle;
        return this;
    }

    public MyTimePickerBuilder setContentTextSize(int textSizeContent) {
        mPickerOptions.textSizeContent = textSizeContent;
        return this;
    }

    /**
     * 设置最大可见数目
     *
     * @param count suggest value: 3, 5, 7, 9
     */
    public MyTimePickerBuilder setItemVisibleCount(int count) {
        mPickerOptions.itemsVisibleCount = count;
        return this;
    }

    /**
     * 透明度是否渐变
     *
     * @param isAlphaGradient true of false
     */
    public MyTimePickerBuilder isAlphaGradient(boolean isAlphaGradient) {
        mPickerOptions.isAlphaGradient = isAlphaGradient;
        return this;
    }

    /**
     * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
     *
     * @param date
     * @return MyTimePickerBuilder
     */
    public MyTimePickerBuilder setDate(Calendar date) {
        mPickerOptions.date = date;
        return this;
    }

    public MyTimePickerBuilder setLayoutRes(int res, CustomListener customListener) {
        mPickerOptions.layoutRes = res;
        mPickerOptions.customListener = customListener;
        return this;
    }


    /**
     * 设置起始时间
     * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
     */

    public MyTimePickerBuilder setRangDate(Calendar startDate, Calendar endDate) {
        mPickerOptions.startDate = startDate;
        mPickerOptions.endDate = endDate;
        return this;
    }


    /**
     * 设置间距倍数,但是只能在1.0-4.0f之间
     *
     * @param lineSpacingMultiplier
     */
    public MyTimePickerBuilder setLineSpacingMultiplier(float lineSpacingMultiplier) {
        mPickerOptions.lineSpacingMultiplier = lineSpacingMultiplier;
        return this;
    }

    /**
     * 设置分割线的颜色
     *
     * @param dividerColor
     */

    public MyTimePickerBuilder setDividerColor(@ColorInt int dividerColor) {
        mPickerOptions.dividerColor = dividerColor;
        return this;
    }

    /**
     * 设置分割线的类型
     *
     * @param dividerType
     */
    public MyTimePickerBuilder setDividerType(WheelView.DividerType dividerType) {
        mPickerOptions.dividerType = dividerType;
        return this;
    }

    /**
     * {@link #setOutSideColor} instead.
     *
     * @param backgroundId color resId.
     */
    @Deprecated
    public MyTimePickerBuilder setBackgroundId(int backgroundId) {
        mPickerOptions.outSideColor = backgroundId;
        return this;
    }

    /**
     * 显示时的外部背景色颜色,默认是灰色
     *
     * @param outSideColor
     */
    public MyTimePickerBuilder setOutSideColor(@ColorInt int outSideColor) {
        mPickerOptions.outSideColor = outSideColor;
        return this;
    }

    /**
     * 设置分割线之间的文字的颜色
     *
     * @param textColorCenter
     */
    public MyTimePickerBuilder setTextColorCenter(@ColorInt int textColorCenter) {
        mPickerOptions.textColorCenter = textColorCenter;
        return this;
    }

    /**
     * 设置分割线以外文字的颜色
     *
     * @param textColorOut
     */
    public MyTimePickerBuilder setTextColorOut(@ColorInt int textColorOut) {
        mPickerOptions.textColorOut = textColorOut;
        return this;
    }

    public MyTimePickerBuilder isCyclic(boolean cyclic) {
        mPickerOptions.cyclic = cyclic;
        return this;
    }

    public MyTimePickerBuilder setOutSideCancelable(boolean cancelable) {
        mPickerOptions.cancelable = cancelable;
        return this;
    }

    public MyTimePickerBuilder setLunarCalendar(boolean lunarCalendar) {
        mPickerOptions.isLunarCalendar = lunarCalendar;
        return this;
    }


    public MyTimePickerBuilder setLabel(String label_year, String label_month, String label_day, String label_hours, String label_mins, String label_seconds) {
        mPickerOptions.label_year = label_year;
        mPickerOptions.label_month = label_month;
        mPickerOptions.label_day = label_day;
        mPickerOptions.label_hours = label_hours;
        mPickerOptions.label_minutes = label_mins;
        mPickerOptions.label_seconds = label_seconds;
        return this;
    }

    /**
     * 设置X轴倾斜角度[ -90 , 90°]
     *
     * @param x_offset_year    年
     * @param x_offset_month   月
     * @param x_offset_day     日
     * @param x_offset_hours   时
     * @param x_offset_minutes 分
     * @param x_offset_seconds 秒
     * @return
     */
    public MyTimePickerBuilder setTextXOffset(int x_offset_year, int x_offset_month, int x_offset_day,
                                            int x_offset_hours, int x_offset_minutes, int x_offset_seconds) {
        mPickerOptions.x_offset_year = x_offset_year;
        mPickerOptions.x_offset_month = x_offset_month;
        mPickerOptions.x_offset_day = x_offset_day;
        mPickerOptions.x_offset_hours = x_offset_hours;
        mPickerOptions.x_offset_minutes = x_offset_minutes;
        mPickerOptions.x_offset_seconds = x_offset_seconds;
        return this;
    }

    public MyTimePickerBuilder isCenterLabel(boolean isCenterLabel) {
        mPickerOptions.isCenterLabel = isCenterLabel;
        return this;
    }

    /**
     * @param listener 切换item项滚动停止时，实时回调监听。
     * @return
     */
    public MyTimePickerBuilder setTimeSelectChangeListener(OnTimeSelectChangeListener listener) {
        mPickerOptions.timeSelectChangeListener = listener;
        return this;
    }

    public MyTimePickerView build() {
        return new MyTimePickerView(mPickerOptions);
    }
}
