package com.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author 余涛
 * Description SharedPreferences工具类封装
 * Time 2019/1/27 16:34
 */
public class SpUtils {
    private static Context mContext;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public SpUtils(Context mContext) {
        this.mContext = mContext.getApplicationContext();

        sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }


   /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_preferences";


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param key
     * @param object
     */
    public static void saveSpData(String key, Object object){
        String type = object.getClass().getSimpleName();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }

        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getSpData(String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();

        try{
            if("String".equals(type)){
                return sp.getString(key, (String)defaultObject);
            }
            else if("Integer".equals(type)){
                return sp.getInt(key, (Integer)defaultObject);
            }
            else if("Boolean".equals(type)){
                return sp.getBoolean(key, (Boolean)defaultObject);
            }
            else if("Float".equals(type)){
                return sp.getFloat(key, (Float)defaultObject);
            }
            else if("Long".equals(type)){
                return sp.getLong(key, (Long)defaultObject);
            }
        }catch(Exception e){
            return e;
        }

        return null;
    }


    /**
     * 清除指定数据
     */
    public static void clearSpOne(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除全部数据
     */
    public static void clearSpAll() {
        editor.clear().commit();
    }



}
