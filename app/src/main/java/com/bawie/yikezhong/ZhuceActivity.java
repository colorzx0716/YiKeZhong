package com.bawie.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ZhuceActivity extends FragmentActivity implements View.OnClickListener {

    private ImageView zhuce_iv_black;
    private TextView zhuce_tv_zhuce;
    private EditText zhuce_et_phone;
    private EditText zhuce_et_pwd;
    private Button zhuce_btn_zhuce;
    private TextView zhuce_tv_youke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        //返回键
        zhuce_iv_black = findViewById(R.id.zhuce_iv_black);
        //已有账号？
        zhuce_tv_zhuce = findViewById(R.id.zhuce_tv_zhuce);
        //手机号输入框
        zhuce_et_phone = findViewById(R.id.zhuce_et_phone);
        //注册密码输入框
        zhuce_et_pwd = findViewById(R.id.zhuce_et_pwd);
        //注册按钮
        zhuce_btn_zhuce = findViewById(R.id.zhuce_btn_zhuce);
        //游客登录
        zhuce_tv_youke = findViewById(R.id.zhuce_tv_youke);

        //点击事件
        zhuce_iv_black.setOnClickListener(this);
        zhuce_tv_zhuce.setOnClickListener(this);
        zhuce_btn_zhuce.setOnClickListener(this);
        zhuce_tv_youke.setOnClickListener(this);

    }

    //总点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhuce_iv_black://返回键
                finish();
              //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case R.id.zhuce_tv_zhuce://已有账号就跳转到登录页
                Intent intent = new Intent(ZhuceActivity.this,Login2Activity.class);
                 startActivity(intent);
                //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case R.id.zhuce_btn_zhuce://注册按钮

                break;

            case R.id.zhuce_tv_youke://游客登录
                Intent intent2 = new Intent(ZhuceActivity.this,MainActivity.class);
                startActivity(intent2);
                finish();

                break;

        }

    }
}
