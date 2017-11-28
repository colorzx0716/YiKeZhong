package com.bawie.yikezhong.utils;

import com.bawie.yikezhong.Interceptor.MyInterceptor;
import com.bawie.yikezhong.api.Api;
import com.bawie.yikezhong.service.InterfaceApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 张肖肖 on 2017/11/27.
 */

public class HttpUtils {
    public static final int TIMEOUT=1000*60;
    public static HttpUtils myQusetUtils;
    private InterfaceApi questInterface;

    public HttpUtils(InterfaceApi questInterface) {
        this.questInterface = questInterface;
    }

    public  InterfaceApi getMyQusetUtils() {
        return questInterface;
    }

    public static class  Builder{
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Api.BASE_URL).client(okHttpClient);
        public  Builder addConverterFactory(){
            builder.addConverterFactory(GsonConverterFactory.create());
            return   this;
        }
        public  Builder addCallAdapterFactory(){
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            return  this;
        }
        public HttpUtils build(){
            InterfaceApi questInterface=builder.build().create(InterfaceApi.class);
            return   myQusetUtils=new HttpUtils(questInterface);
        }

    }
}
