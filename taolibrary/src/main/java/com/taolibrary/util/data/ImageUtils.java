package com.taolibrary.util.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.taolibrary.util.data.sub.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件作者：余涛
 * 功能描述：文件操作
 * 创建时间：2019/11/20 19:07
 */
public class ImageUtils {


    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path 图片本地路径
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }



    /**
     * base64字符串转化成图片
     */
    public static String GenerateImage(String imgStr) {

        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) { //图像数据为空
            return "";
        }

        try {
            //Base64解码
            byte[] b = Base64Utils.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            // 新生成的jpg图片
            // 新图片的文件夹, 如果没有, 就创建
            String dirPath = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/1测试/";
            File fileDir = new File(dirPath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            // 文件夹现在存在了, 可以在此文件夹下创建图片了
            String imgFilePath = dirPath + System.currentTimeMillis() + ".jpg";
            File file = new File(imgFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFilePath;
        } catch (Exception e) {
            return "";
        }
    }




    /**
     * 在ImageView里展示指定路径的图片
     *
     * @param path      本地路径
     * @param imageView ImageView
     */
    public static void ShowPic2View(Context context, String path, ImageView imageView) {
        File localFile;
        FileInputStream localStream;
        Bitmap bitmap;
        localFile = new File(path);
        if (!localFile.exists()) {

        } else {
            try {
                localStream = new FileInputStream(localFile);
                bitmap = BitmapFactory.decodeStream(localStream);
                imageView.setImageBitmap(bitmap);
                //                if (localStream != null) {
                localStream.close();
                //                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 删除手机里指定路径的图片
     *
     * @param context Context
     * @param imgPath 本地路径
     */
    public static void DeletePicFromMobile(Context context, String imgPath) {
        try {
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = MediaStore.Images.Media.query(resolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=?", new String[]{imgPath}, null);
            boolean result;
            if (cursor.moveToFirst()) {
                long id = cursor.getLong(0);
                Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Uri uri = ContentUris.withAppendedId(contentUri, id);
                int count = context.getContentResolver().delete(uri, null, null);
                result = count == 1;
            } else {
                File file = new File(imgPath);
                result = file.delete();
            }

            if (result) {

            }
        } catch (Exception e) {

        }
    }



}

