package com.taolibrary.util.screen;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2020/5/5 14:44
 */
public class ViewUtils {


    /**
     * 设置EditText和输入框之间的距离
     * @param activity
     * @param paddingBottom
     */
    public static void setInputPadding(Activity activity, final int paddingBottom) {
        final View rootView = activity.findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                //r.top 是状态栏高度
                int screenHeight = rootView.getRootView().getHeight();
                int softHeight = screenHeight - r.bottom ;
                if (softHeight>100){//当输入法高度大于100判定为输入法打开了
                    rootView.scrollTo(0, softHeight+paddingBottom);
                }else {//否则判断为输入法隐藏了
                    rootView.scrollTo(0, 0);
                }
            }
        });
    }
}
