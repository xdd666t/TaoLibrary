package com.demo.frame.network;

import com.demo.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/2/14 16:17
 */
public interface Request {

    // 填上需要访问的服务器地址
    public static String HOST = "https://api.douban.com/v2/";

    @GET("book/search")
    Observable<TestBean> getTest(@Query("q") String name,
                                 @Query("start") int start,
                                 @Query("count") int count);

}
