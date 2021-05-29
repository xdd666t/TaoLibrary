package com.taolibrary.widget.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Author 余涛
 * Description 自动适配UI界面
 * Time 2020/3/21 23:49
 */
public class AutoUi{
    //上下文环境
    private Context mContext;
    //设计稿的屏幕参数
    private ScreenParam mDesignScreenParam;
    //当前视图屏幕参数
    private ScreenParam mCurScreenParam;
    //适配类实例
    private static AutoUi instance;
    //适配方式
    private int asCancel = -1;  //取消适配
    private int asWidth = 0;  //按照宽度
    private int asHeight = 1; //按照高度
    private int asWidthAndHeight = 2; //水平方向按照宽度适配，垂直方向按照高度适配
    private boolean isFontScale = false; //字体大小根据系统的“字体大小”设置选项来进行缩放  false：不开启  true：开启

    /**
     * 实例化：获取AutoUi适配类单例
     * @return
     */
    public static AutoUi getInstance() {
        if (instance == null){
            synchronized (AutoUi.class) {
                if (instance == null){
                    instance = new AutoUi();
                }
            }
        }

        return instance;
    }

    /**
     * 实例化：获取AutoUi适配类单例
     * @return
     */
    public static AutoUi with() {
        return getInstance();
    }

    private AutoUi() { }

    /**
     * 请在Application里初始化设计稿的屏幕参数
     * @param context 上下文
     * @param width   屏幕宽度参数（如果参数小于等于0，则自动设置默认参数）
     * @param height  屏幕长度参数（如果参数小于等于0，则自动设置默认参数）
     * @param isFontScale  字体大小根据系统的“字体大小”设置选项来进行缩放  false：不开启  true：开启
     * 默认参数：默认按照: 长/宽：1334/750 ----> iPhone 6的尺寸稿设计
     */
    public void init(Context context, int width, int height, boolean isFontScale) {
        mContext = context;
        this.isFontScale = isFontScale;
        if (width <= 0 || height <= 0) {
            this.mDesignScreenParam =  new ScreenParam(750, 1334);;
        }else {
            this.mDesignScreenParam = new ScreenParam(width, height);
        }
        initScreenParam();
    }


    /**
     * 水平方向按照宽度适配，垂直方向按照高度适配
     * 推荐：小程序界面适配就是按照屏幕宽度适配，一般情况可以使用该种适配
     * @param view
     */
    public void asWidth(View view) {
        setupAll(view, asWidth);
    }

    /**
     * 按照屏幕高度适配
     * 不支持下滑的界面,可采用高度适配: 可保持布局不会超出屏幕
     * @param view
     */
    public void asHeight(View view) {
        setupAll(view, asHeight);
    }

    /**
     * 水平方向按照宽度适配，垂直方向按照高度适配
     * eg：保留该种适配，某些情况，该种适配视觉效果较好
     * @param view
     */
    public void asWidthAndHeight(View view) {
        setupAll(view, asWidthAndHeight);
    }

    /**
     * 取消已经适配过的view
     * 说明：这是一个较为重要的方法，如果已经将所有布局适配了，其中某个模块并不想适配了，
     *       可以调用这个方法，将某个适配的ViewGroup或View还原。
     * 场景：该方法使用场景，一般是还原某个适配过的第三方控件，项目中遇到过这种问题，所以特地写个取消适配方法
     * @param view
     */
    public void asCancel(View view) {
        setupAll(view, asCancel);
    }

    public void asDensity() {
        // iphone 6 设计稿尺寸
        // DESIGN_WIDTH = 750f;
        // DESIGN_HEIGHT = 1334f;
        // DESTGN_INCH = 4.7f;
        // BIG_SCREEN_FACTOR = 0.5f;
        asDensity(mContext, mDesignScreenParam.getWidth(), mDesignScreenParam.getHeight(), 4.7f, 0.5f);
    }


    /**
     * 修改density的值，进行的屏幕适配
     * 重置屏幕密度，必须在View重绘前处理。
     * 此方法需要在以下两个地方注册：
     * 1. Application的onCreat、onConfigurationChanged；
     * 2. Activity中的onCreat(setContentView之前)、onConfigurationChanged；
     * Activity的情况推荐在BaseActivity中注册，能够更好的根据生命周期走，
     * 也可以在ActivityLifecycleCallbacks中处理，来应对所有类型的activity。
     * @param context       上下文
     * @param width         设计稿宽
     * @param height        设计稿高
     * @param inch          设计稿设备尺寸（例如4.7寸、5.0寸）
     * @param screen_factor 屏幕调节因子，范围0~1，用于调节感官体验度。
     */
    public void asDensity(Context context, final float width, final float height, final float inch, final float screen_factor) {
        if (context == null)
            return;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        //确定放大缩小比率
        float rate = Math.min(dm.widthPixels, dm.heightPixels) / Math.min(width, height);
        //确定参照屏幕密度比率
        float referenceDensity = (float) Math.sqrt(width * width + height * height) / inch / DisplayMetrics.DENSITY_DEFAULT;
        //确定相对屏幕密度比率
        float relativeDensity = referenceDensity * rate;
        //确定最终屏幕密度比率，相对屏幕密度比率和系统屏幕密度比率作对比取值
        float systemDensity = Resources.getSystem().getDisplayMetrics().density;
        if (relativeDensity > systemDensity)
            relativeDensity = systemDensity + (relativeDensity - systemDensity) * screen_factor;
        dm.density = relativeDensity;
        dm.scaledDensity = relativeDensity;
        dm.densityDpi = (int) (relativeDensity * DisplayMetrics.DENSITY_DEFAULT);
    }




    /**
     * 初始化设计稿屏幕参数和当前机型实际的屏幕参数
     */
    private void initScreenParam() {
        try{
            if (null == mContext) {
                throw new Exception("请在init方法中设置上下文");
            }
            mCurScreenParam = ScreenParam.getScreenParam(mContext);
        }catch (Exception e){
            Log.e("taolibrary：界面适配报错", e.toString());
        }
    }

    /**
     * 遍历适配所有view
     * @param view
     * @param type
     */
    private void setupAll(View view, int type) {
        if (null == view) return;

        // 设置视图适配 仅仅针对view
        setupView(view, type);
        // 设置padding适配
        setPadding(view, type);
        // 设置margin适配
        setMargin(view, type);
        // 设置text字体大小适配 EditText是TextView的子类，这个同样适配EditText的字体大小
        if (view instanceof TextView) { //过滤掉非TextView相关类
            TextView textView = (TextView) view;
            //TextView.getTextSize()单位为：px
            setTextSize(textView, textView.getTextSize(), type);
        }
        // 遍历ViewGroup的子View适配 适配所有子view
        if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            if (count > 0) {
                for (int i = 0; i < count; ++i) {
                    setupAll(((ViewGroup) view).getChildAt(i), type);
                }
            }
        }
    }

    /**
     * 适配View
     * @param view
     * @param type
     */
    private void setupView(View view, int type) {
        float width = view.getLayoutParams().width;
        float height = view.getLayoutParams().height;

        Size size = getScaledSize(width, height, type); //获取适配后的长宽
        setViewSize(view, size.width, size.height); //设置view尺寸
        //requestLayout方法会使View的onMeasure、onLayout、onDraw方法被调用
        view.requestLayout();

    }

    /**
     * 获取适配后的长宽
     * @param width
     * @param height
     * @param type
     * @return
     */
    private Size getScaledSize(float width, float height, int type) {
        Size temp = new Size(0, 0);
        //测量的宽度比  当前屏幕/尺寸稿
        float scaleW =  (float) mCurScreenParam.getWidth() / (float) mDesignScreenParam.getWidth();
        //测量的高度比 当前屏幕/尺寸稿
        float scaleH = (float) mCurScreenParam.getHeight() / (float) mDesignScreenParam.getHeight();

        switch (type) {
            case -1://取消适配
                temp.width = width / scaleW;
                temp.height = height / scaleW;
                break;
            case 0: //宽度适配
                temp.width = width * scaleW;
                temp.height = height * scaleW;
                break;
            case 1: //高度适配
                temp.width = width * scaleH;
                temp.height = height * scaleH;
                break;
            case 2: //水平方向按照宽度适配，垂直方向按照高度适配
                temp.width = width * scaleW;
                temp.height = height * scaleH;
                break;
        }

        return temp;
    }

    /**
     * 适配Padding
     * @param view
     * @param type
     */
    private void setPadding(View view, int type) {
        float left = view.getPaddingLeft();
        float top = view.getPaddingTop();
        float right = view.getPaddingRight();
        float bottom = view.getPaddingBottom();
        Size xy = getScaledSize(left, top, type);
        Size wh = getScaledSize(right, bottom, type);
        view.setPadding((int) xy.width, (int) xy.height, (int) wh.width, (int) wh.height);
    }

    /**
     * 适配Margin
     * @param view
     * @param type
     */
    private void setMargin(View view, int type) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layoutParams;
            float left = params.leftMargin;
            float top = params.topMargin;
            float right = params.rightMargin;
            float bottom = params.bottomMargin;
            Size xy = getScaledSize(left, top, type);  //获取适配后 左上外边距
            Size wh = getScaledSize(right, bottom, type); //获取适配后 右下上外边距
            setViewMargin(view, (int) xy.width, (int) xy.height, (int) wh.width, (int) wh.height);
        }
    }


    /**
     * 适配文字大小
     * @param textView
     * @param size 适配单位px
     */
    private void setTextSize(TextView textView, float size, int type) {
        //字体适配，直接按照宽度去伸缩
        float scaleW =  (float) mCurScreenParam.getWidth() / (float) mDesignScreenParam.getWidth();
        float fontScale = 1; //系统文字缩放比率
        if (isFontScale) {
            fontScale = mCurScreenParam.getFontScale();
        }
        float scaleRate = scaleW * fontScale;  //缩放比率
        if (type == asCancel) { //TextView.setTextSize();默认单位为：sp 需要转一下
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Math.round(size / scaleRate));
        }else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Math.round(size * scaleRate ));
        }
    }

    /**
     * 尺寸的内部类
     */
    class Size implements Serializable {
        public float width;
        public float height;

        public Size(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }


    /***************************************对view的一些设置*******************************************************/
    /**
     * 设置view的尺寸
     */
    private void setViewSize(View view, float width, float height) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (width > 0)
            params.width = (int) width;
        if (height > 0)
            params.height = (int) height;
    }

    /**
     * 设置margin
     */
    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
}

