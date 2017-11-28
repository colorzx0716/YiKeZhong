package com.bawie.yikezhong;

import android.app.Application;

import com.bawie.yikezhong.service.DemoIntentService;
import com.bawie.yikezhong.service.DemoPushService;
import com.igexin.sdk.PushManager;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;



public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //短信验证
        MobSDK.init(this, "22a72cf54ab0c", "919393e86173db2afbe78acc24b81fea");

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
