package com.bawie.yikezhong;

import android.app.Application;

import com.igexin.sdk.PushManager;
import com.squareup.leakcanary.LeakCanary;



public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(),DemoPushService.class);

        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(),DemoIntentService.class);


        //检测内存泄漏
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
             }
         LeakCanary.install(this);

    }
}
