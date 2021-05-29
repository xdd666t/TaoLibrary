package com.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.taolibrary.BaseFunction;

/**
 * Author 余涛
 * Description 基础Activity
 * Time 2019/1/26 15:32
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initInstance(null); //实例化一些常用类
        initView();
        initData();
    }

    protected void initInstance(Context context) {
        if(context == null) return;  //分割,下方需要用到子类的context

        new BaseFunction(context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected boolean isControlTouch() {
        return false;
    }

    private long lastClickTime = 0;
    //设置拦截的时间间隔 500毫秒
    private long RESTRICT_TIME = 500;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isControlTouch()) {
            long clickTime = System.currentTimeMillis();
            long value = clickTime - lastClickTime;   //点击间隔时间
            lastClickTime = clickTime;
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                if (value <= RESTRICT_TIME) {
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }


}
