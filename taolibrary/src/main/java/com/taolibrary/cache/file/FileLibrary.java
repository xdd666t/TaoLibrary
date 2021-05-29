package com.taolibrary.cache.file;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.taolibrary.R;
import com.taolibrary.cache.file.interfaces.IFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author 余涛
 * Description 文件操作库
 * Time 2019/1/15 10:17
 */
public class FileLibrary implements IFile {
    private Context context;
    private String authorities = "com.taolibrary.fileProvider";

    public FileLibrary(Context context) {
        this.context = context;
        init();
    }

    /************************************下载文件功能模块开始*****************************************/
    private long fileSize = 0;  //下载文件大小
    private ProgressDialog progressDialog;
    private OnDownloadFileListener onDownloadFileListener;
    private Call call;
    private int cancelIndex = 0;


    private void init() {
        progressDialog = new ProgressDialog(context, R.style.DialogRound_Tao);
    }

    @Override
    public FileLibrary setDownloadDialog(String title, String content) {
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle(title);
        progressDialog.setMessage("\n"+ content );
        progressDialog.setProgress(0);
        progressDialog.setMax(1024);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(false);
        progressDialog.setProgressNumberFormat("%1dB / %2dB");
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try{
                    cancelIndex = 1;
                    call.cancel();  //取消文件下载
                    onDownloadFileListener.cancel();
                }catch(Exception e){
                    Toast.makeText(context, "异常信息:" + e, Toast.LENGTH_LONG).show();
                }

            }
        });

        progressDialog.show();

        Point size = new Point();
        progressDialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;//获取界面的宽度像素
        int height = size.y;//获取界面的高度像素
        WindowManager.LayoutParams params = progressDialog.getWindow().getAttributes();
//        params.alpha = 1.0f;//设置弹窗透明度
//        params.height = (int)(height * 0.362);  //设置弹窗高度
        params.width = (int)(width * 0.77); //设置弹窗宽度
        params.gravity = Gravity.CENTER;//设置ProgressDialog的重心
//        params.dimAmount = 0.5f; //设置半透明背景的灰度，范围0~1，系统默认值是0.5
        progressDialog.getWindow().setAttributes(params);//把参数设置给弹窗

        return this;
    }

    @Override
    public void simpleDownloadFile(String downloadUrl, String saveFolder, final OnSimpleDownloadListener onSimpleDownloadListener) {
        download(downloadUrl, "", saveFolder, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Looper.prepare();
                try{
                    progressDialog.dismiss();
                    onSimpleDownloadListener.onResult(file, true, "");  //下载成功监听
                }catch(Exception e){
                    onSimpleDownloadListener.onResult(null, false, e.toString());  //下载失败监听
                }
                Looper.loop();
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed(Exception e) {
                Looper.prepare();
                onSimpleDownloadListener.onResult(null, false, e.toString());  //下载失败监听
                Looper.loop();
            }
        });
    }

    @Override
    public void simpleDownloadFile(String downloadUrl, String fileName, String saveFolder, final OnSimpleDownloadListener onSimpleDownloadListener) {
        download(downloadUrl, fileName, saveFolder, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Looper.prepare();
                try{
                    progressDialog.dismiss();
                    onSimpleDownloadListener.onResult(file, true, "");  //下载成功监听
                }catch(Exception e){
                    onSimpleDownloadListener.onResult(null, false, e.toString());  //下载失败监听
                }
                Looper.loop();
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed(Exception e) {
                Looper.prepare();
                onSimpleDownloadListener.onResult(null, false, e.toString());  //下载失败监听
                Looper.loop();
            }
        });
    }

    @Override
    public void downloadFile(String downloadUrl, String saveFolder, final OnDownloadFileListener onDownloadListener) {
        this.onDownloadFileListener = onDownloadListener;
        download(downloadUrl, "", saveFolder, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Looper.prepare();
                try{
                    progressDialog.dismiss();
                    onDownloadFileListener.onSuccess(file);  //下载成功监听
                }catch(Exception e){
                    Toast.makeText(context, "异常信息:" + e, Toast.LENGTH_LONG).show();
                }
                Looper.loop();
            }

            @Override
            public void onDownloading(int progress) {
                if (fileSize >= 1024*1024*100){
                    progressDialog.setProgressNumberFormat("%1dMB / %2dMB");
                    progressDialog.setMax((int)(fileSize/(1024*1024)));
                    progressDialog.setProgress(progress/(1024*1024));
                }
                if (fileSize < 1024*1024*100  && fileSize > 1024){
                    progressDialog.setProgressNumberFormat("%1dKB / %2dKB");
                    progressDialog.setMax((int)(fileSize/(1024)));
                    progressDialog.setProgress(progress/1024);
                }
                if (fileSize <= 1024){
                    progressDialog.setProgressNumberFormat("%1dB / %2dB");
                    progressDialog.setMax((int)(fileSize));
                    progressDialog.setProgress(progress);
                }
            }

            @Override
            public void onDownloadFailed(Exception e) {
                Looper.prepare();
                progressDialog.dismiss();
                onDownloadFileListener.onFail(e);  //下载失败监听
                Looper.loop();
            }
        });

    }

    @Override
    public void downloadFile(String downloadUrl, final String fileName, String saveFolder,
                             final OnDownloadFileListener onDownloadListener) {
        this.onDownloadFileListener = onDownloadListener;

        download(downloadUrl, fileName, saveFolder, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Looper.prepare();
                try{
                    progressDialog.dismiss();
                    onDownloadFileListener.onSuccess(file);  //下载成功监听
                }catch(Exception e){
                    Toast.makeText(context, "异常信息:" + e, Toast.LENGTH_LONG).show();
                }
                Looper.loop();
            }

            @Override
            public void onDownloading(int progress) {
                if (fileSize >= 1024*1024*100){
                    progressDialog.setProgressNumberFormat("%1dMB / %2dMB");
                    progressDialog.setMax((int)(fileSize/(1024*1024)));
                    progressDialog.setProgress(progress/(1024*1024));
                }
                if (fileSize < 1024*1024*100  && fileSize > 1024){
                    progressDialog.setProgressNumberFormat("%1dKB / %2dKB");
                    progressDialog.setMax((int)(fileSize/(1024)));
                    progressDialog.setProgress(progress/1024);
                }
                if (fileSize <= 1024){
                    progressDialog.setProgressNumberFormat("%1dB / %2dB");
                    progressDialog.setMax((int)(fileSize));
                    progressDialog.setProgress(progress);
                }
            }

            @Override
            public void onDownloadFailed(Exception e) {
                Looper.prepare();
                progressDialog.dismiss();
                onDownloadFileListener.onFail(e);  //下载失败监听
                Looper.loop();
            }
        });

    }

    /**
     * 原生下载方法
     * @param downloadUrl  下载连接
     * @param saveFolder  下载的文件储存目录
     * @param onDownloadListener  下载监听
     */
    private void download(final String downloadUrl, final String fileName, final String saveFolder,
                          final OnDownloadListener onDownloadListener) {

        Request request = new Request.Builder().url(downloadUrl).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDownloadListener.onDownloadFailed(e);  // 下载失败监听回调
            }

            @Override
            public void onResponse(Call call, Response response) {
                if("error".equals(getFileName(downloadUrl, onDownloadListener)) && fileName.equals("") ) {
                    Looper.prepare();
                    Toast.makeText(context, "解析链接文件名失败，请稍后再试或手动定义文件名。", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    call.cancel();
                    Looper.loop();
                }

                String myFileName = getFileName(downloadUrl, onDownloadListener); //从链接中解析文件全名（文件名+后缀）

                if (!fileName.equals("")) myFileName = fileName;  //从传入值定义文件名

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;

                File dir = new File(saveFolder); // 储存下载文件的目录
                if (!dir.exists()) dir.mkdirs();
                File file = new File(dir, myFileName);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;

                    fileSize = total;   //获取文件大小
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f);
                        onDownloadListener.onDownloading(progress);  // 下载中更新进度条
                    }
                    fos.flush();
                    onDownloadListener.onDownloadSuccess(file); // 下载完成

                } catch (Exception e) {

                    if(cancelIndex == 0 ){
                        onDownloadListener.onDownloadFailed(e);
                    }else{
                        cancelIndex = 0;
                    }

                } finally {
                    try {
                        if (is != null) is.close();
                        if (fos != null) fos.close();
                    } catch (IOException e) { }
                }
            }
        });
    }

    /**
     * download(...) 辅助类，获取链接中文件名
     * @param downloadUrl
     * @param onDownloadListener
     * @return 返回文件名或error
     */
    private String getFileName(final String downloadUrl,final OnDownloadListener onDownloadListener) {
        String myFileName = "error";
        try {
            URL myURL = new URL(downloadUrl);
            URLConnection conn = myURL.openConnection();
            conn.connect();
            if(((HttpURLConnection) conn).getResponseCode()==200){
                String urlFileName = conn.getURL().getFile();
                myFileName = urlFileName.substring(urlFileName.lastIndexOf('/')+1);
                if (!myFileName.contains(".")) {
                    return "error";
                }
            }else{
                return "error";
            }
        } catch (Exception e) {
            onDownloadListener.onDownloadFailed(e);
        }
        return myFileName;
    }


    public interface OnDownloadListener {
        /**
         * @param file 下载成功后的文件
         */
        void onDownloadSuccess(File file);

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * @param e 下载异常信息
         */
        void onDownloadFailed(Exception e);
    }
    /************************************下载文件功能模块结束*****************************************/


    @Override
    public String getFileName(String downloadUrl) {
        return downloadUrl.substring(downloadUrl.lastIndexOf('/')+1);
    }

    @Override
    public void getFileName(final String downloadUrl, final OnFileNameListener onFileNameListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String myFileName;
                try {
                    URL myURL = new URL(downloadUrl);
                    URLConnection conn = myURL.openConnection();
                    conn.connect();
                    if(((HttpURLConnection) conn).getResponseCode()==200){
                        String urlFileName = conn.getURL().getFile();
                        myFileName = urlFileName.substring(urlFileName.lastIndexOf('/')+1); //获取链接中的文件名

                        Looper.prepare();
                        onFileNameListener.getFileName(myFileName);
                        Looper.loop();
                        if (!myFileName.contains(".")) {
                            myFileName = "error：文件名获取异常";
                            Looper.prepare();
                            onFileNameListener.getFileName(myFileName);
                            Looper.loop();
                        }
                    }else{
                        myFileName = "error：网络超时";
                        Looper.prepare();
                        onFileNameListener.getFileName(myFileName);
                        Looper.loop();
                    }
                } catch (Exception e) {
                    myFileName = "异常信息：" + e;
                    Looper.prepare();
                    onFileNameListener.getFileName(myFileName);
                    Looper.loop();
                }
            }
        }).start();

    }

    @Override
    public void openFile(File file) {
        Intent intent = new Intent();
        String type = getMIMEType(file);//获取文件MIME类型
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_VIEW);//过滤作用
        try {
            //判断是否是AndroidN以及更高的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri contentUri = FileProvider.getUriForFile(context, authorities, file);
                intent.setDataAndType(contentUri, type);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//需要和低版本区别开,否则打开文件会无法识别类型
            } else {
                Uri contentUri =Uri.fromFile(file);
                intent.setDataAndType(contentUri, type);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //需要和高版本区别开,否则打开文件会无法识别类型
            }
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "打开失败：" + e, Toast.LENGTH_LONG).show();
            Log.e("YTAOLibrary", "打开文件报错：" + e);
        }
    }

    @Override
    public FileLibrary setOpenFileAuthorities(String authorities) {
        this.authorities = authorities;
        return this;
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     * @param file
     */
    private String getMIMEType(File file) {
        String type = "*/*";
        String fileName = file.getName();
        //获取文件的后缀名 将后缀字母全部转为小写字母
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (fileType == "") return type;
        for (int i = 0; i < MIME_MapTable.length; i++) {  //查找类型对应位置
            if (fileType.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private final String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

}
