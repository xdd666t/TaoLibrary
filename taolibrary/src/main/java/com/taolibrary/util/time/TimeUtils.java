package com.taolibrary.util.time;

import android.os.SystemClock;

import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2020/3/23 16:30
 */
public class TimeUtils {
//    private Activity mActivity;
//    private TimeUtils(Activity activity){
//        mActivity = activity;
//    }

    /**
     * 获取网络时间
     * @return
     */
    public Date getNetDate() {
        try{
            URL url=new URL("http://www.baidu.com");//取得资源对象
            URLConnection uc=url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld=uc.getDate(); //取得网站日期时间
            Date date=new Date(ld); //转换为标准时间对象
            //分别取得时间中的小时，分钟和秒，并输出
            return date;
        }catch (Exception e){
            return null;
        }
    }


    // 这里不去采用System.currentTimeMillis()或System.nanoTime()/1000000L这一方法(java中采用这种方法),
    // 因为它产生一个当前的毫秒，这个毫秒其实就是自1970年1月1日0时起的毫秒数,这个是受机器设定的时间影响较大
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = SystemClock.uptimeMillis(); // 此方法仅用于Android
        if (time - lastClickTime < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
