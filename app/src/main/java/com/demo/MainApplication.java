package com.demo;

import android.app.Application;

import com.taolibrary.util.show.LogUtils;
import com.taolibrary.widget.ui.AutoUi;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/10/12 16:18
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AutoUi.getInstance().init(this, 750, 1334, false);
        LogUtils.initLog("ytao");
    }
}
