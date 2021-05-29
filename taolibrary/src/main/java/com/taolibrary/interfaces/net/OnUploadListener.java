package com.taolibrary.interfaces.net;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/8/29 9:32
 */
public interface OnUploadListener {
    void onProgress(long total, long current);

    void onResponse(String response, String requestTab);
}
