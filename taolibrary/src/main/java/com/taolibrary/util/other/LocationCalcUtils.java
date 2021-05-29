package com.taolibrary.util.other;

/**
 * 文件作者：余涛
 * 功能描述：位置计算
 * 创建时间：2020/4/15 16:55
 */
public class LocationCalcUtils {

    /**
     * 赤道半径
     */
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * Description : 通过经纬度获取距离(单位：米)
     * 经纬度：Longitude + "," +Latitude  (逗号隔开)
     * @param origin      出发点
     * @param destination 目的地
     * @return double
     * @author honghh
     * @date 2019/2/13 0013 15:50
     */
    public static double getDistance(String origin, String destination) {
        String[] temp = origin.split(",");
        String[] temp2 = destination.split(",");

        double radLat1 = rad(Double.parseDouble(temp[1]));
        double radLat2 = rad(Double.parseDouble(temp2[1]));
        double a = radLat1 - radLat2;
        double b = rad(Double.parseDouble(temp[0])) - rad(Double.parseDouble(temp2[0]));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        // 保留两位小数
        s = Math.round(s * 100d) / 100d;
        s = s * 1000;
        return s;
    }

    /**
     * Description : 通过经纬度获取距离(单位：米)
     * @param originLon      出发点经度
     * @param originLat      出发点纬度
     * @param destinationLon 目的地经度
     * @param destinationLat 目的地纬度
     * @return double
     * @author honghh
     * @date 2019/2/15 0015 9:14
     */
    public static double getDistance(String originLon, String originLat, String destinationLon, String destinationLat) {
        double radLat1 = rad(Double.parseDouble(originLat));
        double radLat2 = rad(Double.parseDouble(destinationLat));
        double a = radLat1 - radLat2;
        double b = rad(Double.parseDouble(originLon)) - rad(Double.parseDouble(destinationLon));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        // 保留两位小数
        s = Math.round(s * 100d) / 100d;
        s = s * 1000;
        return s;
    }


}
