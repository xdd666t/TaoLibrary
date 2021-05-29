package com.taolibrary.interfaces.net;

/**
 * 文件作者：余涛
 * 功能描述：
 *      1、请求成功或失败的接口回调
 *      2、由于请求失败或成功，onFailure方法都会被回调，所以此处省略onComplete()方法
 *      3、请求成功时,onFailure返回"no error"信息
 * 创建时间：2019/8/26 17:40
 */
public interface OnCallback {
    /**
     * 请求成功后的回调
     * @param response 请求返回的数据
     * @param requestTab 请求标签，区别开不同的请求回调，如果请求时没设置标签值，则回调返回时requestTab为空字符“”
     */
    void onSuccess(String response, String requestTab);

    /**
     * 请求发生错误,回调错误信息
     * @param e
     */
    void onFailure(Throwable e, String requestTab);
}
