package com.bawie.yikezhong;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bawie.yikezhong.base.BaseActivity;
import com.bawie.yikezhong.base.BasePresenter;

import java.util.List;

public class FirstActivity extends BaseActivity {

    @Override
    public List<BasePresenter> initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转到登录页面
                Intent intent = new Intent(FirstActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);//3秒后跳转

    }

    //沉浸式
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&& Build.VERSION.SDK_INT>=19){
            View decorView=getWindow().getDecorView();
            int option=View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
        }
    }

}
