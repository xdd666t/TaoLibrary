package com.taolibrary.base;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.taolibrary.R;

/**
 * Author 余涛
 * Description 一些系统dialog控件调用
 * Time 2019/11/21 23:49
 */
public abstract class BaseDialog extends Dialog {
    protected abstract void initView();
    protected abstract void initData();
    protected Context mContext;


    public BaseDialog(@NonNull Context context) {
        super(context, R.style.Dialog_Tao);
        init(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }



    private void init(Context context) {
        mContext = context;
        initView();
        initData();
    }

}
