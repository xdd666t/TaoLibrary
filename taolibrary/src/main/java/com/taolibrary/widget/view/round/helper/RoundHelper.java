package com.taolibrary.widget.view.round.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.taolibrary.R;

/**
 * 作者: 余涛
 * 功能描述: RoundView的功能实现helper类
 * 创建时间: 2019/12/22 23:50
 */
public class RoundHelper {
    //设置渐变的角度  正常/按压开始颜色--->正常/按压结束开始颜色
    //设置背景
    public int backgroundColor;
    //如果设置了背景颜色 渐变色失效  ||  如果设置了按压颜色 渐变按压色失效
    public boolean isClickEffect; //是否开启按压效果
    public int angle;
    public int colorStart;
    public int colorEnd;
    public boolean clickEffect;
    public int colorPress;
    public int colorPressStart;
    public int colorPressEnd;
    //设置临时颜色变量  开始--->结束
    private int colorTempStart;
    private int colorTempEnd;
    //设置文字,颜色,大小
    public String text;
    public int textColor;
    public float textSize;
    //储存矩形的参数,android设置渐变背景
    private LinearGradient bgGradient;
    //设置圆角
    public float[] radii = new float[8]; //topLeft:0,1  topRight:2,3  bottomRight:4,5  bottomLeft:6,7
    private float roundAll = 0;
    private float roundTopLeft = 0;
    private float roundTopRight = 0;
    private float roundBottomLeft = 0;
    private float roundBottomRight = 0;
    public boolean isCircle = false; // 圆形
    //设置边框
    public float strokeWidth = 0;
    public int strokeColor = 0;
    //圆角裁边
    public boolean isClipBg = false; //是否裁剪背景

    //关于阴影的一些设置
    public boolean isShadow; //是否开启阴影
    public int shadowColor;  //阴影颜色
    public float shadowDx;  //阴影偏离X轴
    public float shadowDy;  //阴影偏离Y轴
    public float shadowRadius;  //阴影半径
    public int shadowViewBg;  //设置阴影时,控件背景

    //默认画笔
    private Paint mPaintBg; //背景
    private Paint mShadowPaintBg; //背景
    private Paint mPaintStroke; //边框
    private Paint mPaintText; //文字
    //设置路径
    public Path mPathBg; //外部需要用
    private Path mPathStroke;

    public Region mAreaRegion;  // 内容区域(外部需要用)
    public RectF mLayer;        // 画布图层大小(外部需要用)
    private View mView;

    public void initAttrs(Context context, AttributeSet attrs) {
        //获取自定义属性
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
            //设置背景
            backgroundColor = typedArray.getColor(R.styleable.RoundView_backgroudColor, Color.TRANSPARENT);
            shadowViewBg = backgroundColor;
            //设置圆角
            isClipBg = typedArray.getBoolean(R.styleable.RoundView_isClipBg, false);
            isCircle = typedArray.getBoolean(R.styleable.RoundView_isCircle, false);
            roundAll = typedArray.getDimension(R.styleable.RoundView_roundAll, 0);
            roundTopLeft = typedArray.getDimension(R.styleable.RoundView_roundTopLeft, 0);
            roundTopRight = typedArray.getDimension(R.styleable.RoundView_roundTopRight, 0);
            roundBottomRight = typedArray.getDimension(R.styleable.RoundView_roundBottomRight, 0);
            roundBottomLeft = typedArray.getDimension(R.styleable.RoundView_roundBottomLeft, 0);
            radii[0] = roundAll == 0 ? roundTopLeft : roundAll;  //如此赋值,使每个数组index相对应的角更明确
            radii[1] = roundAll == 0 ? roundTopLeft : roundAll;
            radii[2] = roundAll == 0 ? roundTopRight : roundAll;
            radii[3] = roundAll == 0 ? roundTopRight : roundAll;
            radii[4] = roundAll == 0 ? roundBottomRight : roundAll;
            radii[5] = roundAll == 0 ? roundBottomRight : roundAll;
            radii[6] = roundAll == 0 ? roundBottomLeft : roundAll;
            radii[7] = roundAll == 0 ? roundBottomLeft : roundAll;
            //设置关于渐变的
            isClickEffect = typedArray.getBoolean(R.styleable.RoundView_isClickEffect, false);
            angle = typedArray.getInt(R.styleable.RoundView_angle, 1);
            colorStart = typedArray.getColor(R.styleable.RoundView_colorStart, Color.WHITE);
            colorEnd = typedArray.getColor(R.styleable.RoundView_colorEnd, Color.WHITE);
            clickEffect = typedArray.getBoolean(R.styleable.RoundView_isClickEffect, false);
            colorPress = typedArray.getColor(R.styleable.RoundView_colorPress, Color.parseColor("#E6E6E6"));
            colorPressStart = typedArray.getColor(R.styleable.RoundView_colorPressStart, Color.TRANSPARENT);
            colorPressEnd = typedArray.getColor(R.styleable.RoundView_colorPressEnd, Color.TRANSPARENT);
            //设置文字
            text = typedArray.getString(R.styleable.RoundView_text);
            textColor = typedArray.getColor(R.styleable.RoundView_textColor, Color.parseColor("#333333"));
            textSize = typedArray.getDimension(R.styleable.RoundView_textSize, 32);
            //边框线
            strokeWidth = typedArray.getDimension(R.styleable.RoundView_stroke_width, 0);
            strokeColor = typedArray.getColor(R.styleable.RoundView_stroke_color, Color.TRANSPARENT);

            //设置阴影
            isShadow = typedArray.getBoolean(R.styleable.RoundView_isShadow, false);
            shadowRadius = typedArray.getDimension(R.styleable.RoundView_shadowRadius, 10);
            shadowDx = typedArray.getDimension(R.styleable.RoundView_shadowDx, 0);
            shadowDy = typedArray.getDimension(R.styleable.RoundView_shadowDy, 0);
            shadowColor = typedArray.getColor(R.styleable.RoundView_shadow_color, Color.GRAY);

            setBgColor(false); //设置背景色
            typedArray.recycle(); //刷新
        }
        initDraw();
    }


    /**
     * 初始化一些绘制操作
     */
    private void initDraw() {
        //设置背景路径绘制参数
        mPathBg = new Path();
        mPathBg.setFillType(Path.FillType.EVEN_ODD);
        //设置边框路径绘制参数
        mPathStroke = new Path();
        mPathStroke.setFillType(Path.FillType.EVEN_ODD);
        //设置背景画笔
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);//设置抗锯齿
        mPaintBg.setDither(true); //设置防抖动
        mPaintBg.setStyle(Paint.Style.FILL);
        mPaintBg.setColor(Color.WHITE); //默认颜色设置为白色
        // 设置背景画笔
        mShadowPaintBg = new Paint();
        mShadowPaintBg.setAntiAlias(true);//设置抗锯齿
        mShadowPaintBg.setDither(true); //设置防抖动
        mShadowPaintBg.setStyle(Paint.Style.FILL);
        mShadowPaintBg.setColor(Color.WHITE); //默认颜色设置为白色

        //设置边框画笔
        mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaintStroke.setAntiAlias(true);//设置抗锯齿
        mPaintStroke.setDither(true); //设置防抖动
        //设置绘制文字画笔
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        mLayer = new RectF();
        mAreaRegion = new Region();
    }


    /**
     * 尺寸改变
     * @param view
     * @param w
     * @param h
     */
    public void onSizeChanged(View view, int w, int h) {
        mView = view;
        setBgGradient(w, h); //设置背景渐变色

        mLayer.set(0, 0, w, h);
        refreshRegion(view);
    }


    /**
     * 刷新区域数据
     * @param view
     */
    public void refreshRegion(View view) {
        int w = (int) mLayer.width();
        int h = (int) mLayer.height();
        RectF areas = getArea(view);
        mPathBg.reset();
        if (isCircle) {
            float d = areas.width() >= areas.height() ? areas.height() : areas.width();
            float  r = d / 2;
            PointF center = new PointF(w / 2, h / 2);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
                mPathBg.addCircle(center.x, center.y, r, Path.Direction.CW);

                mPathBg.moveTo(0, 0);  // 通过空操作让Path区域占满画布
                mPathBg.moveTo(w, h);
            } else {
                float y = h / 2 - r;
                mPathBg.moveTo(areas.left, y);
                mPathBg.addCircle(center.x, y + r, r, Path.Direction.CW);
            }
        } else {
            mPathBg.addRoundRect(areas, radii, Path.Direction.CW);
        }
        Region clip = new Region((int) areas.left, (int) areas.top,
                (int) areas.right, (int) areas.bottom);

        mAreaRegion.setPath(mPathBg, clip);  //取俩者路径的交集,获取实际展示给用户的面积
    }

    /**
     * 绘制逻辑方法
     * @param canvas
     */
    public void onClipDraw(Canvas canvas) {
        //开启阴影内部关闭此类功能：边框，渐变，内部显示图片
        if (isShadow) { //绘制阴影和PorterDuff模式冲突，如果绘制阴影，便不能绘制渐变色，边框
            //radius:模糊半径，radius越大越模糊，越小越清晰，但是如果radius设置为0，则阴影消失不见
            //dx:阴影的横向偏移距离，正值向右偏移，负值向左偏移
            //dy:阴影的纵向偏移距离，正值向下偏移，负值向上偏移
            //color: 绘制阴影的画笔颜色，即阴影的颜色（对图片阴影无效）
            mShadowPaintBg.setColor(shadowViewBg);
            mShadowPaintBg.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
            canvas.drawPath(mPathBg, mShadowPaintBg);  //渲染背景
        } else {
            if (strokeWidth > 0) {  //设置描边
                if (bgGradient != null) { //边框不设置渐变色
                    mPaintBg.setShader(null);
                }
                // 支持半透明描边，将与描边区域重叠的内容裁剪掉
                //设置了setXfermode的paint即为前景src,原本存在的vie视图为背景dst
                mPaintStroke.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                mPaintStroke.setStrokeWidth(strokeWidth * 2);
                mPaintStroke.setStyle(Paint.Style.STROKE);
                canvas.drawPath(mPathBg, mPaintStroke);
                // 绘制描边,描边的宽度向内外扩展
                mPaintStroke.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                mPaintStroke.setColor(strokeColor);
                mPaintStroke.setStyle(Paint.Style.STROKE);
                canvas.drawPath(mPathBg, mPaintStroke);
            }

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                //android api 19(4.4) 不支持弧边,渐变色等功能  绘制边框里面的实体 KITKAT
                mPaintBg.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                canvas.drawPath(mPathBg, mPaintBg);  //渲染背景
            } else {
                //切割边框外多余部分
                mPaintBg.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                final Path path = new Path();
                path.addRect(0, 0, (int) mLayer.width(), (int) mLayer.height(), Path.Direction.CW);
                path.op(mPathBg, Path.Op.DIFFERENCE);  //选取不同的区域
                canvas.drawPath(path, mPaintBg);  //将不同的区域截掉(android 19没法裁剪边框外部区域)

                //设置边框内的背景色或渐变色之类
                mPaintBg.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
                if (bgGradient != null) { //设置渐变色
                    mPaintBg.setShader(bgGradient);
                }
                canvas.drawPath(mPathBg, mPaintBg);  //渲染背景
            }
        }


        //设置文字
        //绘制文字
        if (!TextUtils.isEmpty(text)) {
            mPaintText.setTextSize(textSize);
            mPaintText.setColor(textColor);
            mPaintText.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetricsInt fontMetrics = mPaintText.getFontMetricsInt();
            RectF rectF = getArea(mView);
            float baseline = rectF.top + (rectF.bottom - rectF.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(text, canvas.getWidth() / 2, baseline, mPaintText);
        }
    }



    /*****************************************设置渐变色*******************************************/
    private void setBgGradient(int w, int h) {
        //设置渐变角度
        switch (angle) {
            case 1:
                bgGradient = new LinearGradient(0, 0, w, 0, new int[]{colorTempStart, colorTempEnd}, null, Shader.TileMode.CLAMP);
                break;
            case 2:
                bgGradient = new LinearGradient(0, 0, 0, w, new int[]{colorTempStart, colorTempEnd}, null, Shader.TileMode.CLAMP);
                break;
            case 3:
                bgGradient = new LinearGradient(w, 0, 0, 0, new int[]{colorTempStart, colorTempEnd}, null, Shader.TileMode.CLAMP);
                break;
            case 4:
                bgGradient = new LinearGradient(0, w, 0, 0, new int[]{colorTempStart, colorTempEnd}, null, Shader.TileMode.CLAMP);
                break;
        }
        mPaintBg.setShader(bgGradient);
    }

/*****************************************设置背景颜色*******************************************/
    /**
     * 设置绘制的背景颜色
     *
     * @param isPress 是否是按压状态
     */
    public void setBgColor(boolean isPress) {
        if (isPress && isClickEffect) { //如果表示是按下或开启了按压效果,则变色
            //如果设置了按压色,渐变按压色失效
            if ( (colorPressStart == Color.TRANSPARENT && colorPressEnd == Color.TRANSPARENT)
                    || colorPress != Color.parseColor("#E6E6E6")) {
                colorTempStart = colorPress;
                colorTempEnd = colorPress;

                shadowViewBg = colorPress;  //兼容下设置阴影时，按压颜色
            }else {
                colorTempStart = colorPressStart;
                colorTempEnd = colorPressEnd;
            }
        }else {
            if (backgroundColor != Color.TRANSPARENT) { //如果设置了背景色,渐变色失效
                colorTempStart = backgroundColor;
                colorTempEnd = backgroundColor;

                shadowViewBg = backgroundColor;  //恢复下背景色
            }else {
                colorTempStart = colorStart;
                colorTempEnd = colorEnd;

                shadowViewBg = colorStart; //恢复按压前的颜色
            }
        }

        if (mLayer != null) {
            int w = (int) mLayer.width();
            int h = (int) mLayer.height();
            setBgGradient(w, h);
        }
    }


    private RectF getArea(View view) {
        int w = (int) mLayer.width();
        int h = (int) mLayer.height();
        RectF areas = new RectF();
        areas.left = view.getPaddingLeft();
        areas.top = view.getPaddingTop();
        areas.right = w - view.getPaddingRight();
        areas.bottom = h - view.getPaddingBottom();
        return areas;
    }


}
