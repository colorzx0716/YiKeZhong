package com.bawie.yikezhong.Interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;



public class My implements Interceptor {
    public String TAG = "LogInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.d(TAG,"\n");
        Log.d(TAG,"----------Start----------------");
        String method=request.method();
        if("POST".equals(method)){
            FormBody.Builder sb = new FormBody.Builder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.add(body.encodedName(i) , body.encodedValue(i));
                }
                body=sb.add("source","android").add("appVersion","101")
                        .add("token","929A310F425923598C7F3495BCAAA278")
                        .build();
                request=request.newBuilder().post(body).build();
                Log.d(TAG, "| "+request.toString());
            }else {
                MultipartBody body=(MultipartBody)request.body();
                MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
                build  .addFormDataPart("source","android");
                build  .addFormDataPart("appVersion","101");
                build  .addFormDataPart("token","929A310F425923598C7F3495BCAAA278");
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    build.addPart(part);
                }
                request =request.newBuilder().post(build.build()).build();
            }
        }

        return chain.proceed(request);
    }
}
