package com.bawie.yikezhong.service;

import com.bawie.yikezhong.bean.PublishBean;
import com.bawie.yikezhong.bean.UserJoker;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 张肖肖 on 2017/11/27.
 * 接口
 *
 */

public interface InterfaceApi {

    //登录接口
    @POST("user/login")
    Observable<ResponseBody> getUserLogin(@Query("mobile") String mobile, @Query("password") String password);

    //注册接口
    @POST("user/reg")
    Observable<ResponseBody> getUserReg(@Query("mobile") String mobile,@Query("password") String password);

    //上传头像接口
    @POST("file/upload")
    Observable<ResponseBody> getFileUpload();

    //获取用户信息接口
    @POST("user/getUserInfo")
    Observable<ResponseBody> getUserInfo(@Query("uid") String uid,@Query("token") String token);

    //获取段子列表
    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<UserJoker> getUserJokes(@Field("page") String page, @Field("token") String token);

    //发布段子
    @POST("quarter/publishJoke")
    @FormUrlEncoded
    Observable<PublishBean> getPublish(@Field("uid") String uid, @Field("content") String content, @Field("token") String token);


}
