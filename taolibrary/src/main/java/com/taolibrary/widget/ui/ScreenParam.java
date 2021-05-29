package com.taolibrary.widget.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Author 余涛
 * Description 屏幕参数
 * Time 2020/3/21 23:49
 */
public class ScreenParam {
    //屏幕宽度
    private int width;
    //屏幕高度
    private int height;
    //字体缩放(随着系统的字体缩放)
    private float fontScale;

    /*****************下述参数暂未用到********************/
    //屏幕的密度比例
    private float density;
    //屏幕的密度(暂未用到该参数)
    private int densityDpi;
    //字体的缩放因子 SP
    private float scaledDensity;

    /**
     * 指定屏幕参数，一般是设置标定设计稿的参数
     * @param width 屏幕宽度
     * @param height 屏幕高度
     */
    public ScreenParam(int width, int height) {
        this.width = width;
        this.height = height;
        this.density = 0;
        this.densityDpi = 0;
    }

    /**
     * 设置全部屏幕参数
     * @param width 屏幕宽度
     * @param height 屏幕高度
     * @param context 上下文
     */
    public ScreenParam(int width, int height, Context context) {
        this.width = width;
        this.height = height;
        this.fontScale = getSystemFontScale(context);
        this.density = getScreenDensity(context);
        this.densityDpi = getScreenDensityDpi(context);
        this.scaledDensity = getScreenScaledDensity(context);
    }

    /*
     * 获取当前屏幕参数，大小，密度和密度系数等
     * @param context
     * @return
     */
    public static ScreenParam getScreenParam(Context context) {
        // 屏幕宽度（像素）
        int width = getScreenWidth(context);
        // 屏幕高度（像素）
        int height = getScreenHeight(context);
        ScreenParam screenParam = new ScreenParam(width, height, context);
        return screenParam;
    }

    public float getFontScale() {
        return fontScale;
    }

    public void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public float getDensity() {
        return density;
    }

    public float getScaledDensity() {
        return scaledDensity;
    }

    public void setScaledDensity(float scaledDensity) {
        this.scaledDensity = scaledDensity;
    }

    /***************************************************获取一些屏幕参数************************************************/
    /**
     * 返回当前屏幕方向。
     */
    private static int getOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    private static float getSystemFontScale(Context context) {
        Resources resources = context.getResources();
        float fontScale = 1;
        if (resources != null) {
            Configuration configuration = resources.getConfiguration();
            fontScale = configuration.fontScale;
        }
        return fontScale;
    }

    /**
     * 获得屏幕宽度
     * @param context
     * @return
     */
    private static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     * @param context
     * @return
     */
    private static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得屏幕密度 <屏幕密度（0.75 / 1.0 / 1.5）>
     * @param context
     * @return
     */
    private static float getScreenDensity(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.density;
    }

    /**
     * 获得屏幕密度DPI <屏幕密度DPI（120 / 160 / 240）> 开发基准屏的DPI为160 以此计算字体缩放大小
     * @param context
     * @return
     */
    private static int getScreenDensityDpi(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.densityDpi;
    }

    /**
     * 字体的缩放因子 调节系统字体大小后会改变这个值
     * @param context
     * @return
     */
    private static float getScreenScaledDensity(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.scaledDensity;
    }
}
