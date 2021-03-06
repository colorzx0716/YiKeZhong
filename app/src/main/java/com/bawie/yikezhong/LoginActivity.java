package com.bawie.yikezhong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.base.BaseActivity;
import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.base.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView login_iv_back;
    private RelativeLayout login_weixin;
    private RelativeLayout login_qq;
    private TextView login_tv;
    private ImageView login_iv;
    private SharedPreferences sp;

    @Override
    public List<BasePresenter> initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        sp = getSharedPreferences("sp", MODE_PRIVATE);


        //返回键
        login_iv_back = findViewById(R.id.login_iv_back);
        //微信登录
        login_weixin = findViewById(R.id.login_weixin);

        //qq登录
        login_qq = findViewById(R.id.login_qq);

        //其他登录方式
        login_tv = findViewById(R.id.login_tv);

        //设置圆角图片
        login_iv = findViewById(R.id.login_iv);

        Glide.with(this).load(R.drawable.raw_1500977101)
                .bitmapTransform(new GlideCircleTransform(this,10))
                .crossFade(1000).into(login_iv);

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到登录页面
                boolean isfirst = sp.getBoolean("isfirst", false);
                if(isfirst){
                    //第二次
                    //跳转到登录页面
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

                }else{

                    //第一次
                    Intent intent = new Intent(LoginActivity.this,Login2Activity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

                }
            }
        });





        //设置点击事件
        login_iv_back.setOnClickListener(this);
        login_weixin.setOnClickListener(this);
        login_qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_iv_back:
                //点击返回
                finish();
                //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case R.id.login_weixin:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();

                break;

            case R.id.login_qq:
                Toast.makeText(this, "qq登录", Toast.LENGTH_SHORT).show();

                break;

          /*  case R.id.login_tv:
                //跳转到登录页面
                Intent intent = new Intent(LoginActivity.this,Login2Activity.class);
                startActivity(intent);
               //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;*/
        }

    }
}
