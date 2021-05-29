package com.taolibrary.util.data;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 文件作者：余涛
 * 功能描述：实现深克隆
 * 创建时间：2020/4/15 16:55
 */
public class CloneUtil {

    private CloneUtil() { }

    public static <T extends Serializable> T clone(T object){
        try {
            // 说明：调用ByteArrayOutputStream或ByteArrayInputStream对象的close方法没有任何意义
            // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外资源(如文件流)的释放
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}