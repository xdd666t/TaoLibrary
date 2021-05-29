package com.taolibrary.http;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2020/3/23 11:11
 */
public class HttpClient {
    //设置OkHttp
    private static final int HTTP_TIME_OUT_LINK = 5; //设置连接超时时间
    private static final int HTTP_TIME_OUT_READ = 10; //设置读取超时时间
    private static final int HTTP_TIME_OUT_WRITE = 10; //设置写入超时时间
    //缓存路径 暂时用不到
    private static final String HTTP_CACHE_PATH = Environment.getExternalStorageDirectory().getPath() + "/HttpCache/" ;
    private static final long HTTP_CACHE_SIZE = 1024 * 1024 * 100; //缓存路径 缓存大小100Mb


    private static HttpClient mHttpClient;
    private Map<String, Object> mServiceMap; //存储service实例，便于重用

    private HttpClient(){
    }

    /**
     * 单例
     */
    public static synchronized HttpClient getInstance() {
        if (mHttpClient == null) {
            synchronized (HttpClient.class) {
                if (mHttpClient == null) {
                    mHttpClient = new HttpClient();
                }
            }
        }
        return mHttpClient;
    }

    /**
     * 单例
     */
    public static HttpClient with(){
        return getInstance();
    }

    /**
     * 创建APIService，返回相应的APIService，相应的APIService类上必须使用 HttpBaseUrl 注解，指定对应的BaseUrl
     * @param service
     * @param <T>
     * @return
     */
    public synchronized <T> T create(final Class<T> service) {
        T t;
        //get domain type.
        HttpBaseUrl httpBaseUrl = service.getAnnotation(HttpBaseUrl.class);
        String baseUrl = "";
        if (httpBaseUrl != null) {
            baseUrl = httpBaseUrl.value();
        }

        //缓存对象，重用
        String key =  service.getCanonicalName();
        if (mServiceMap.get(key) != null) {
            t = (T) mServiceMap.get(key);
        } else {
            t = getRetrofit(baseUrl).create(service);
            mServiceMap.put(key, t);
        }
        return t;
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create()) //JSON转换器,使用Gson来转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava适配器
                .build();
    }

    private OkHttpClient getOkHttp() {
        return new OkHttpClient.Builder()
                //设置连接超时时间
                .connectTimeout(HTTP_TIME_OUT_LINK, TimeUnit.SECONDS)
                //设置读取超时时间
                .readTimeout(HTTP_TIME_OUT_READ, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(HTTP_TIME_OUT_WRITE, TimeUnit.SECONDS)
                //默认重试一次
                .retryOnConnectionFailure(true)
                //添加请求头拦截器 此处可以添加一些公共参数
                .addInterceptor(InterceptorHelper.getHeaderInterceptor())
                //添加日志拦截器
                .addInterceptor(InterceptorHelper.getLogInterceptor())
                //添加缓存拦截器
                .addInterceptor(InterceptorHelper.getCacheInterceptor())
                //添加重试拦截器
                .addInterceptor(InterceptorHelper.getRetryInterceptor())
                // 信任Https,忽略Https证书验证
                // https认证,如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
//                            .sslSocketFactory(SSLSocketTrust.getSSLSocketFactory())
//                            .hostnameVerifier(SSLSocketTrust.getHostnameVerifier())
                //前端配置了缓存，但是后台服务端却没有合理处理缓存请求头 会导致请求失败, 默认不使用缓存
//                            .cache(cache)
                .build();
    }

    /**
     * RxJava辅助方法, 切换IO线程和主线程
     * @param context
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxIo2Main(final Context context) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}