package com.bawie.yikezhong.Interceptor;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张肖肖 on 2017/11/27.
 */

public class TokenInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        //添加请求头
        Request.Builder requestBuilder = original.newBuilder()
                .header("User-Agent", "OkHttp Example");

        //请求体定制：统一添加token参数
        if(original.body() instanceof FormBody){
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oidFormBody = (FormBody) original.body();
            for (int i = 0;i<oidFormBody.size();i++){
                //取出原先boby的数据  存入新的boby里
                newFormBody.addEncoded(oidFormBody.encodedName(i),oidFormBody.encodedValue(i));
            }
            newFormBody.add("token","929A310F425923598C7F3495BCAAA278");
            newFormBody.add("source","android");
            newFormBody.add("appVersion","");
            requestBuilder.method(original.method(),newFormBody.build());

        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
