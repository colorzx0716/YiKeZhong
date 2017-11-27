package com.bawie.yikezhong.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 张肖肖 on 2017/11/27.
 * 接口
 *
 */

public interface InterfaceApi {

    @POST("user/login")
    Observable<ResponseBody> getUserLogin(@Query("mobile") String mobile, @Query("password") String password, @Query("token") String token);

    @POST("user/reg")
    Observable<ResponseBody> getUserReg(@Query("mobile") String mobile,@Query("password") String password,@Query("token") String token);

    @POST("file/upload")
    Observable<ResponseBody> getFileUpload();

}
