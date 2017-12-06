package com.bawie.yikezhong.service;

import com.bawie.yikezhong.bean.AdBean;
import com.bawie.yikezhong.bean.FollowBean;
import com.bawie.yikezhong.bean.PraiseBean;
import com.bawie.yikezhong.bean.PublishBean;
import com.bawie.yikezhong.bean.PublishVideo;
import com.bawie.yikezhong.bean.UserJoker;
import com.bawie.yikezhong.bean.VideosBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    //获取用户信息接口
    @POST("user/getUserInfo")
    Observable<ResponseBody> getUserInfo(@Query("uid") String uid,@Query("token") String token);

    //获取段子列表
    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<UserJoker> getUserJokes(@Field("page") String page, @Field("token") String token);

    //发布段子和图片
    @POST("quarter/publishJoke")
    @FormUrlEncoded
    Observable<PublishBean> getPublishJoke(@Field("uid") String uid,@Field("content") String content,@Field("token") String token);

    //广告接口
    @GET("quarter/getAd")
    Observable<AdBean> getAd();

    //关注端口
    @POST("quarter/follow")
    @FormUrlEncoded
    Observable<FollowBean> getFollow(@Field("uid") String uid, @Field("followId") String content, @Field("token") String token);


    //段子点赞
    @POST("quarter/jokePraise")
    @FormUrlEncoded
    Observable<PraiseBean> getPraise(@Field("uid") String uid, @Field("jid") String jid);


    //获取视频列表
    @POST("quarter/getVideos")
    @FormUrlEncoded
    Observable<VideosBean> getVideos(@Field("uid") String uid,@Field("type") String type, @Field("page") String page);

    //发布视频
    @POST("quarter/publishVideo")
    @Multipart
    Observable<PublishVideo> getPublishVideo(@Part() List<MultipartBody.Part> videoFile);

    //热门视频
    @POST("quarter/getHotVideos")
    @FormUrlEncoded
    Observable<PublishVideo> getHotVideos(@Field("page") String page);

    //附近视频
    @POST("quarter/getNearVideos")
    @FormUrlEncoded
    Observable<PublishVideo> getNearVideos(@Field("page") String page,@Field("latitude") String latitude,@Field("longitude") String longitude);


}
