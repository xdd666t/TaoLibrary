package com.taolibrary.util.time;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 文件作者：余涛
 * 功能描述：日期工具
 * 创建时间：2019/9/22 15:43
 */
public class DateUtils {

    /**
     * 格式化时间
     * @param formatType  格式类型 eg:"yyyy-MM-dd HH-mm-ss"
     * @param time  date.getTime()
     * @return
     */
    public static String formatTime(String formatType, long time) {
        //使用common-lang包下面的DateFormatUtils类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        return simpleDateFormat.format(time);
    }

    /**
     * 通过日期格式获取Date数据
     * @param formatType  格式类型 eg:"yyyy-MM-dd HH:mm:ss"
     * @param formatData 相应的格式化数据  eg:2018-08-08 15:14:00
     * @return Data类型
     */
    public static Date getDateByString(String formatType, String formatData) {
        Date date = null;
        if(formatData == null) {
            return date;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        try {
            date = format.parse(formatData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 通过日期格式获取相应的String类型日期
     * @param formatType  格式类型 eg:"yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return 返回相应格式化时间,String类型
     */
    public static String getTimeByDate(String formatType, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        String time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * 通过相应的格式化类型数据,获取和现在的相隔时间
     * @param formatType  格式类型 eg:"yyyy-MM-dd HH:mm:ss"
     * @param formatData 相应的格式化数据  eg:2018-08-08 15:14:00
     * @return
     */
    public static String getShortTime(String formatType, String formatData) {
        String shortString = null;
        long now = Calendar.getInstance().getTimeInMillis();
        Date date = getDateByString(formatType, formatData);
        if(date == null) {
            return shortString;
        }
        long delTime = (now - date.getTime()) / 1000;
        if (delTime > 365 * 24 * 60 * 60) {
            shortString = (int) (delTime / (365 * 24 * 60 * 60)) + "年前";
        } else if (delTime > 24 * 60 * 60) {
            shortString = (int) (delTime / (24 * 60 * 60)) + "天前";
        } else if (delTime > 60 * 60) {
            shortString = (int) (delTime / (60 * 60)) + "小时前";
        } else if (delTime > 60) {
            shortString = (int) (delTime / (60)) + "分前";
        } else if (delTime > 1) {
            shortString = delTime + "秒前";
        } else {
            shortString = "1秒前";
        }
        return shortString;
    }

    /**
     * 获取星期
     * @param date
     * @return 星期信息
     */
    public static String dayForWeek(Date date){
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取星期
     * @param date
     * @return 星期信息 数字
     */
    public static int dayForWeekDig(Date date){
        int[] weekDays = { 7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取网络时间  (默认百度)
     * @param format 时间格式  eg:"yyyy-MM-dd HH:mm:ss"
     * @return 返回格式化的时间 String类型
     */
    public static String getNetworkTime(String format) {
        return getNetworkTime("http://www.baidu.com", format);
    }

    /**
     * 获取网络时间
     * @param webUrl  链接
     * @param format 时间格式 eg:"yyyy-MM-dd HH:mm:ss"
     * @return 返回格式化的时间 String类型
     */
    public static String getNetworkTime(String webUrl, String format) {
        try {
            URL url=new URL(webUrl);
            URLConnection conn=url.openConnection();
            conn.connect();
            long dateL=conn.getDate();
            Date date=new Date(dateL);
            //在这里，如果是想返回sql的Date类型则修改方法的返回类型
            //将目前获取到的网络时间util.Date转换成sql.Date的操作如下：
            // java.sql.Date date1=new java.sql.Date(date.getTime());//年 月 日
            //java.sql.Time date2=new java.sql.Time(date.getTime());//时   分    秒
            //java.sql.Timestamp date3=new java.sql.Timestamp(date.getTime());//年  月 日  时  分   秒 毫秒
            //System.out.println("输出当前时间年月日"+date1);
            // System.out.println("输出当前时间时分秒"+date2);
            //System.out.println("输出当前时间年月日时分秒毫秒"+date3);

            //以下是将时间转换成String类型并返回
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            return dateFormat.format(date);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

}
