package com.bawie.yikezhong.Interceptor;

/**
 * Created by 张肖肖 on 2017/11/28.
 */

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 */

public class MyInterceptor implements Interceptor {
    public String TAG = "LogInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.d(TAG,"\n");
        Log.d(TAG,"----------Start----------------");
        Log.d(TAG, "| "+request.toString());
        String method=request.method();
        if("POST".equals(method)){
            FormBody.Builder sb = new FormBody.Builder();

            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.add(body.encodedName(i) , body.encodedValue(i));
                }
                body=sb.add("source","android").add("appVersion","101").build();
                request=request.newBuilder().post(body).build();

                Log.d(TAG, "| RequestParams:{"+sb.toString()+"}");
            }
        }
        Log.d(TAG, "| Response:" + content);
        Log.d(TAG,"----------End:"+duration+"毫秒----------");
        return chain.proceed(request);

    }
}
