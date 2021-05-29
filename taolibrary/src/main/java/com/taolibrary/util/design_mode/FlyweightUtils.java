package com.taolibrary.util.design_mode;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/6/28 9:39
 */
public class FlyweightUtils {
    /**
     * 经典享元模式方法
     * 场景：解决某些场景频繁生成实例问题；使用泛型，节省写判断代码
     */
    private static Map<String, Object> flyweightMap;
    private static  <T> T classicFlyweight(Class<T> clazz) {
        T t;

        if (flyweightMap == null)
            flyweightMap = new HashMap<>();
        String key = clazz.getName();
        if (flyweightMap.get(key) != null) {
            t = (T) flyweightMap.get(key);
        }else {
            try {
                t = clazz.newInstance();
                flyweightMap.put(key, t);
            } catch (Exception e) {
                t = null;
            }
        }

        return t;
    }
}
