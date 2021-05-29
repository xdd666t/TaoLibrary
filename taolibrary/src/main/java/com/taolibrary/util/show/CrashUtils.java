package com.taolibrary.util.show;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/10/11 14:52
 */
public class CrashUtils implements Thread.UncaughtExceptionHandler {

    private static CrashUtils sInstance = null;
    private Context mContext;
    private Map<String, String> mMessage = new HashMap<>();     // 保存手机信息和异常信息
    private String savePath = null;

    //不允许手动实例化
    private CrashUtils() {
    }

    public static CrashUtils getInstance() {
        if (sInstance == null) {
            synchronized (CrashUtils.class) {
                if (sInstance == null) {
                    synchronized (CrashUtils.class) {
                        sInstance = new CrashUtils();
                    }
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化默认异常捕获
     *
     * @param context context
     */
    public void init(Context context) {
        mContext = context;
        // 将此类设为默认异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 初始化默认异常捕获
     *
     * @param context context
     */
    public void init(Context context, String saveFile) {
        mContext = context;
        this.savePath = saveFile;
        // 将此类设为默认异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        collectErrorMessages();
        saveErrorMessages(e);

        new Thread() {// 在主线程中弹出提示
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "系统发生异常，即将退出。。。", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(3000);
        } catch (Exception e1) {
            Logger.d(e1);
        }
        //退出程序；退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
        System.exit(0);
        //从操作系统中结束掉当前程序的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    /**
     * 发生产生异常的硬件信息
     */
    private void collectErrorMessages() {
        PackageManager pm = mContext.getPackageManager();
        try {
            //添加系统信息到错误日志中
            mMessage.put("Android版本：", Build.VERSION.RELEASE);
            mMessage.put("设备厂商：", Build.BRAND);
            mMessage.put("设备型号：", Build.MODEL);
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = TextUtils.isEmpty(pi.versionName) ? "null" : pi.versionName;
                String versionCode = "" + pi.versionCode;
                mMessage.put("versionName：", versionName);
                mMessage.put("versionCode：", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存产生的异常错误信息
     * @param e Throwable
     */
    private void saveErrorMessages(Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n设备信息>>>>>>>>>>\n\n");
        for (Map.Entry<String, String> entry : mMessage.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append(value).append("\n");
        }
        sb.append("\n\n\n具体异常信息>>>>>>>>>>\n\n");
        //获取异常信息
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        pw.close();
        String result = writer.toString();
        sb.append(result);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date());
        String fileName = time + " 异常日志.txt";
        // 有无SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath() + "/TaoCrash/";
            if (savePath != null) {
                path = savePath;  //自定义保存文件夹路径
            }
            File dir = new File(path);
            if (!dir.exists()) dir.mkdirs();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
