package com.taolibrary.cache.file.interfaces;

import com.taolibrary.cache.file.FileLibrary;

import java.io.File;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/1/15 9:59
 */
public interface IFile {


    /**
     * 建立下载窗口,在调用下载方法前,需要先调用该方法,显示下载窗口
     * @param title  标题
     * @param content  内容
     * @return  返回FileLibrary实例
     */
    FileLibrary setDownloadDialog(String title, String content);

    /**
     * 下载文件(便捷)
     * @param downloadUrl  下载链接
     * @param saveFolder   保存下载文件夹路径
     * @param onSimpleDownloadListener 实现下载结果回调接口
     * @return
     */
    void simpleDownloadFile(String downloadUrl, String saveFolder, final OnSimpleDownloadListener onSimpleDownloadListener);

    /**
     * 下载文件，当文件名获取异常，可以使用该方法，手动输入文件名(便捷)
     * @param downloadUrl 下载链接
     * @param fileName   下载文件名
     * @param saveFolder  保存下载文件夹路径
     * @param onSimpleDownloadListener 实现下载结果回调接口
     * @return
     */
    void simpleDownloadFile(String downloadUrl, String fileName, String saveFolder, final OnSimpleDownloadListener onSimpleDownloadListener);


    /**
     * 下载文件
     * @param downloadUrl  下载链接
     * @param saveFolder   保存下载文件夹路径
     * @param onDownloadFileListener 实现下载结果回调接口
     * @return
     */
    void downloadFile(String downloadUrl, String saveFolder, final OnDownloadFileListener onDownloadFileListener);

    /**
     * 下载文件，当文件名获取异常，可以使用该方法，手动输入文件名
     * @param downloadUrl 下载链接
     * @param fileName   下载文件名
     * @param saveFolder  保存下载文件夹路径
     * @param onDownloadFileListener 实现下载结果回调接口
     * @return
     */
    void downloadFile(String downloadUrl, String fileName, String saveFolder, final OnDownloadFileListener onDownloadFileListener);

    interface OnDownloadFileListener{

        /**
         * 下载成功
         */
        void onSuccess(File file);

        /**
         * 下载失败
         */
        void onFail(Exception e);

        /**
         * 取消下载
         */
        void cancel();
    }


    interface OnSimpleDownloadListener{
        /**
         * 下载成功
         */
        void onResult(File file, boolean isSuccess, String e);
    }

    /**
     * 获取链接中的文件名(直接截取链接中的文件名.如果下载地址是映射的,截取文件名不准确)
     * @param downloadUrl  下载链接
     */
    String getFileName(final String downloadUrl);

    /**
     * 获取链接中的文件名(通过网络访问,获取真实下载链接,截取文件名)
     * @param downloadUrl  下载链接
     * @param onFileNameListener 在线程中进行,需要接口监听
     */
    void getFileName(final String downloadUrl, final OnFileNameListener onFileNameListener);

    interface OnFileNameListener{
        /**
         * 获取链接文件名
         * @param fileName
         */
        void getFileName(String fileName);
    }


    /**
     * 打开文件
     * @param file
     */
    void openFile(File file);

    /**
     * 设置打开文件authorities的配置，兼容android7.0打开文件限制
     * @param authorities
     * @return
     */
    FileLibrary setOpenFileAuthorities(String authorities);


}
