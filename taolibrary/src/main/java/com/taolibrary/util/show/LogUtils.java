package com.taolibrary.util.show;



import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/10/8 11:37
 */
public class LogUtils{

    /**
     * 增加适配器
     * @param logAdapter
     *  1、AndroidLogAdapter，即显示用的日志（输出在控制台）
     *  2、DiskLogAdapter，目前的配置是，保存在根路径下，logs_0.csv为存储名称，500kb为大小。
     *  3、如果想改变日志在本地内存中的存储路径，日志大小或者日志存储名称。可以实现logger的三个接口。自定义自己的适配器。
     */
    public static void addLogAdapter(LogAdapter logAdapter) {
        Logger.addLogAdapter(logAdapter);
    }

    public static void clearLogAdapters() {
        Logger.clearLogAdapters();
    }

    public static void initLog(String tag) {
//        Logger.addLogAdapter(new AndroidLogAdapter());  //默认适配器，加上后会打印默认tag日志
//        Logger.addLogAdapter(new DiskLogAdapter());  //将Log日志保存到文件中

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(true)   //（可选）是否显示线程信息。默认值true
        .methodCount(2)         //（可选）要显示的方法行数。默认值2
        .methodOffset(0)       // (可选) 隐藏的方法数量，若设为 1，则隐藏跳转到日志输出的方法。Default：0
//        .logStrategy()//（可选）更改要打印的日志策略。默认LogCat （即android studio的日志输出Logcat）
        .tag(tag)   //  //（可选）每个日志的全局标记。默认PRETTY_LOGGER .build
        .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void hideLog() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

}
