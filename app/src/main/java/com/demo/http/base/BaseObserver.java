package com.demo.http.base;

import android.accounts.NetworkErrorException;
import android.content.Context;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文件作者：余涛
 * 功能描述：这是对回调的处理类，因为返回格式可能存在多样，这里面这返回的逻辑的处理存在变动
 * 创建时间：2020/3/25 16:19
 */

public abstract class BaseObserver<T> implements Observer<T> {
    protected Context mContext;

    public BaseObserver() {

    }

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    /**
     * 该方法中的逻辑,根据实际返回格式而变动
     * @param response
     */
    @Override
    public void onNext(T response) {
        onRequestEnd();
        onFinish(""); //回调成功,也回调下该方法

        try {
            onSuccess(response);
        } catch (Exception e) { //成功接收到收据,处理逻辑报错
            onFinish(e.toString());
        }


//        if (baseResponseBean.isSuccess()) {//成功
//            try {
//                onSuccess(baseResponseBean);
//            } catch (Exception e) { //成功接收到收据,处理逻辑报错
//                onFailure(e.toString());
//            }
//        } else {
//            try {
//                if (!TextUtils.isEmpty(baseResponseBean.getMsg())) {
//                    onFailure(baseResponseBean.getMsg());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onError(Throwable e) {
        onRequestEnd();

        String net_timeout = "Network load timeout";
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFinish(net_timeout);
            } else {
                onFinish(e.toString());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param response
     */
    protected abstract void onSuccess(T response);

    /**
     * 不管成功失败都会走该回调，失败的错误信息，从该回调中输出(成功回调抛的异常也在该处输出)
     * 可在此方法中进行关闭加载动画操作
     *
     * @param errorMessage
     */
    protected abstract void onFinish(String errorMessage);

    /**
     * 请求开始
     */
    protected void onRequestStart() {
        showProgressDialog();
    }


    /**
     * 请求结束
     */
    protected void onRequestEnd() {
        closeProgressDialog();
    }

    /**
     * 加载弹窗（可选）
     */
    public void showProgressDialog() {

    }

    /**
     * 关闭加载弹窗（可选）
     */
    public void closeProgressDialog() {

    }

}
