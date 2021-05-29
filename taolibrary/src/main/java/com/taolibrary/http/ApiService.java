package com.taolibrary.http;

import com.taolibrary.http.base.BaseResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2020/3/23 10:16
 */
@HttpBaseUrl(ApiService.BASE_URL)
public interface ApiService {
    String BASE_URL = "https://test.com";

    //获取考勤记录
    @GET("times/api/getCheckInRecord.do")
    Observable<BaseResponseBean<String>> getCheckInRecord(@QueryMap Map<String, String> map);


}
