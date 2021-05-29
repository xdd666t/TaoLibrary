package com.yt.matisse;

import android.os.Environment;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2020/6/3 10:56
 */
public class Constant {

    public static final int DURATION = 600 * 1000;  //录制视频最长时间限制
    public static final int MIN_DURATION = 3 * 1000;  //录制最短视频时间限制
    //视频保存路径
    public static final String VIDEO_PATH = Environment.getExternalStorageDirectory().getPath() + "/matisse/video";

}
