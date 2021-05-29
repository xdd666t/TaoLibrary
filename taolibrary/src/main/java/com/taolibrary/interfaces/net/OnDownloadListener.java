package com.taolibrary.interfaces.net;

import java.io.File;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/8/29 9:31
 */
public interface OnDownloadListener {

    /**
     * 回调下载进度信息
     * @param total
     * @param current
     */
    void onProgress(long total, long current);

    /**
     * @param file 下载成功后的文件
     */
    void onSuccess(File file);

    /**
     * 下载失败
     * @param e
     */
    void onFailure(Exception e);
}
