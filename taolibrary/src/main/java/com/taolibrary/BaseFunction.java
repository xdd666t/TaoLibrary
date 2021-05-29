package com.taolibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by ytao on 2018/11/21
 * Describe: 基本功能库
 */
public class BaseFunction {
    private static Context context;
    private static BaseFunction instance;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;


    private static BaseFunction install = null;

    public static BaseFunction getInstance(Context context) {
        synchronized(BaseFunction.class) {
            if (install == null) {
                install = new BaseFunction(context);
            }
        }
        return install;
    }

    public BaseFunction(Context mContext) {
        context = mContext;

        initInstance();
    }

    /**
     * 初始化一些对象
     */
    private void initInstance() {
        sp = context.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    /**
     * 弹出Toast消息
     * @param message
     */
    public static void showToast(String message){
        Toast.makeText(context, message , Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出Toast消息
     * @param message
     * @param time 消息存在时间
     */
    public static void showToast(String message, int time) {
        Toast.makeText(context, message, time).show();
    }


/*************************************封装SharedPreferences用法******************************************/
    /**
     * 保存在手机里面的文件名
     */
    private static String FILE_NAME = "share_preferences";

    public void setSpFileName(String fileName) {
        sp = context.getApplicationContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

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
/*************************************封装SharedPreferences用法******************************************/



}
