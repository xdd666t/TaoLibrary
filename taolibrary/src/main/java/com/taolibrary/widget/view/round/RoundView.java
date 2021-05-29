package com.taolibrary.widget.view.round;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.taolibrary.util.other.LocationCalcUtils;
import com.taolibrary.widget.view.round.helper.RoundHelper;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 作者: 余涛
 * 功能描述: 圆角View(可设置各个角度)
 * 创建时间: 2019/12/15 21:56
 */
public class RoundView extends RelativeLayout {
    RoundHelper mRoundHelper;

    /**
     * 在java代码里new的时候会用到
     */
    public RoundView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    /**
     * 在xml布局文件中使用时自动调用
     */
    public RoundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); //关闭硬件加速
        mRoundHelper = new RoundHelper();
        mRoundHelper.initAttrs(context, attrs);

        if (mRoundHelper.isClickEffect) setClickable(true); //开启按压效果,则默认开启按触
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRoundHelper.onSizeChanged(this, w, h);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(mRoundHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        mRoundHelper.onClipDraw(canvas);
        canvas.restore();
    }

    @Override
    public void draw(Canvas canvas) {
        if (mRoundHelper.isClipBg) {
            canvas.save();
            canvas.clipPath(mRoundHelper.mPathBg);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
//        Logger.d("roundView~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //如果点击为圆弧外区域,则不反馈点击
        //自我消费,不再  继续传递给Activity的onTouch方法(false的话)
        if (action == MotionEvent.ACTION_DOWN && !mRoundHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
//            Logger.d("============================");
            return true;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRoundHelper.setBgColor(true);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mRoundHelper.setBgColor(false);
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                mRoundHelper.setBgColor(false);
                invalidate();
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void invalidate() {
        if (null != mRoundHelper)
            mRoundHelper.refreshRegion(this);
        super.invalidate();
    }



    /*****************************************对外保留修改参数的方法*******************************************/
    public int getBagColor() {
        return mRoundHelper.backgroundColor;
    }

    public void setBgColor(int backgroundColor) {
        //确保保持设置的背景色
        mRoundHelper.backgroundColor = backgroundColor;
        //背景色是收尾一致的颜色构成的,没辙,因为要做渐变色，只能这样控制
        //确保第一次会刷新为设置的颜色
        mRoundHelper.setBgColor(false);
        invalidate();
    }

    public boolean isClickEffect() {
        return mRoundHelper.isClickEffect;
    }

    public void setClickEffect(boolean clickEffect) {
        mRoundHelper.isClickEffect = clickEffect;
        if (mRoundHelper.isClickEffect) setClickable(true); //开启按压效果,则默认开启按触
        invalidate();
    }

    public int getColorPress() {
        return mRoundHelper.colorPress;
    }

    public void setColorPress(int colorPress) {
        mRoundHelper.colorPress = colorPress;
        invalidate();
    }

    public int getColorPressStart() {
        return mRoundHelper.colorPressStart;
    }

    public void setColorPressStart(int colorPressStart) {
        mRoundHelper.colorPressStart = colorPressStart;
        invalidate();
    }

    public int getColorPressEnd() {
        return mRoundHelper.colorPressEnd;
    }

    public void setColorPressEnd(int colorPressEnd) {
        mRoundHelper.colorPressEnd = colorPressEnd;
        invalidate();
    }

    public String getText() {
        return mRoundHelper.text;
    }

    public void setText(String text) {
        mRoundHelper.text = text;
        invalidate();
    }

    public int getTextColor() {
        return mRoundHelper.textColor;
    }

    public void setTextColor(int textColor) {
        mRoundHelper.textColor = textColor;
        invalidate();
    }

    public float getTextSize() {
        return mRoundHelper.textSize;
    }

    public void setTextSize(float textSize) {
        mRoundHelper.textSize = textSize;
        invalidate();
    }

    public float getRoundAll() {
        return mRoundHelper.radii[0];
    }

    public void setRoundAll(float roundAll) {
        mRoundHelper.radii[0] = roundAll;
        mRoundHelper.radii[1] = roundAll;
        mRoundHelper.radii[2] = roundAll;
        mRoundHelper.radii[3] = roundAll;
        mRoundHelper.radii[4] = roundAll;
        mRoundHelper.radii[5] = roundAll;
        mRoundHelper.radii[6] = roundAll;
        mRoundHelper.radii[7] = roundAll;
        invalidate();
    }

    public float getRoundTopLeft() {
        return mRoundHelper.radii[0];
    }

    public void setRoundTopLeft(float roundTopLeft) {
        mRoundHelper.radii[0] = roundTopLeft;
        mRoundHelper.radii[1] = roundTopLeft;
        invalidate();
    }

    public float getRoundTopRight() {
        return mRoundHelper.radii[0];
    }

    public void setRoundTopRight(float roundTopRight) {
        mRoundHelper.radii[2] = roundTopRight;
        mRoundHelper.radii[3] = roundTopRight;
        invalidate();
    }

    public float getRoundBottomLeft() {
        return mRoundHelper.radii[0];
    }

    public void setRoundBottomLeft(float roundBottomLeft) {
        mRoundHelper.radii[4] = roundBottomLeft;
        mRoundHelper.radii[5] = roundBottomLeft;
        invalidate();
    }

    public float getRoundBottomRight() {
        return mRoundHelper.radii[0];
    }

    public void setRoundBottomRight(float roundBottomRight) {
        mRoundHelper.radii[6] = roundBottomRight;
        mRoundHelper.radii[7] = roundBottomRight;
        invalidate();
    }

    public boolean isCircle() {
        return mRoundHelper.isCircle;
    }

    public void setCircle(boolean circle) {
        mRoundHelper.isCircle = circle;
        invalidate();
    }

    public float getStrokeWidth() {
        return mRoundHelper.strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        mRoundHelper.strokeWidth = strokeWidth;
        invalidate();
    }

    public int getStrokeColor() {
        return mRoundHelper.strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        mRoundHelper.strokeColor = strokeColor;
        invalidate();
    }

    public boolean isClipBg() {
        return mRoundHelper.isClipBg;
    }

    public void setClipBg(boolean clipBg) {
        mRoundHelper.isClipBg = clipBg;
        invalidate();
    }

    public int getAngle() {
        return mRoundHelper.angle;
    }

    public void setAngle(int angle) {
        mRoundHelper.angle = angle;
        invalidate();
    }

    public int getColorStart() {
        return mRoundHelper.colorStart;
    }

    public void setColorStart(int colorStart) {
        mRoundHelper.colorStart = colorStart;
        invalidate();
    }

    public int getColorEnd() {
        return mRoundHelper.colorEnd;
    }

    public void setColorEnd(int colorEnd) {
        mRoundHelper.colorEnd = colorEnd;
        invalidate();
    }

    public boolean isShadow() {
        return mRoundHelper.isShadow;
    }

    public void setShadow(boolean shadow) {
        mRoundHelper.isShadow = shadow;
        invalidate();
    }

    public int getShadowColor() {
        return mRoundHelper.shadowColor;
    }

    public void setShadowColor(int shadowColor) {
        mRoundHelper.shadowColor = shadowColor;
        invalidate();
    }

    public float getShadowDx() {
        return mRoundHelper.shadowDx;
    }

    public void setShadowDx(float shadowDx) {
        mRoundHelper.shadowDx = shadowDx;
        invalidate();
    }

    public float getShadowDy() {
        return mRoundHelper.shadowDy;
    }

    public void setShadowDy(float shadowDy) {
        mRoundHelper.shadowDy = shadowDy;
        invalidate();
    }

    public float getShadowRadius() {
        return mRoundHelper.shadowRadius;
    }

    public void setShadowRadius(float shadowRadius) {
        mRoundHelper.shadowRadius = shadowRadius;
        invalidate();
    }
}
