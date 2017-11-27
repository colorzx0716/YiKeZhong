package com.bawie.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 忘记密码的页面，也是短信登录的页面
 */
public class MobileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mobile_iv_black;
    private TextView mobile_tv_yiyou;
    private EditText mobile_et_phone;
    private Button mobile_btn_yanzheng;
    private EditText mobile_et_yzm;
    private Button mobile_btn_login;
    private TextView mobile_tv_login;

    private EventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        intView();

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MobileActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //提交验证码成功
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MobileActivity.this, " 提交验证码成功", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //获取验证码成功
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MobileActivity.this, " 获取验证码成功", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                        }
                    }else{
                        ((Throwable)data).printStackTrace();
                    }
                }
            }

        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);


        //点击
        mobile_iv_black.setOnClickListener(this);
        mobile_tv_yiyou.setOnClickListener(this);
        mobile_btn_yanzheng.setOnClickListener(this);
        mobile_btn_login.setOnClickListener(this);
        mobile_tv_login.setOnClickListener(this);


    }

    //初始化
    private void intView() {
        //返回键
        mobile_iv_black = findViewById(R.id.mobile_iv_black);
        //已有账号？
        mobile_tv_yiyou = findViewById(R.id.mobile_tv_yiyou);
        //手机号码输入框
        mobile_et_phone = findViewById(R.id.mobile_et_phone);
        //获取验证码的按钮
        mobile_btn_yanzheng = findViewById(R.id.mobile_btn_yanzheng);
        //验证码的输入框
        mobile_et_yzm = findViewById(R.id.mobile_et_yzm);
        //登录的按钮
        mobile_btn_login = findViewById(R.id.mobile_btn_login);
        //游客登录
        mobile_tv_login = findViewById(R.id.mobile_tv_login);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mobile_iv_black://返回键
                finish();
                break;
            case R.id.mobile_tv_yiyou://已有账号的字符
                //跳转到登录页
                Intent intent = new Intent(MobileActivity.this,Login2Activity.class);
                 startActivity(intent);

                break;
            case R.id.mobile_btn_yanzheng://验证码的按钮
                //点击验证码的按钮
                if (TextUtils.isEmpty(mobile_et_phone.getText().toString())) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                SMSSDK.getVerificationCode("86", mobile_et_phone.getText().toString());

                break;

            case R.id.mobile_btn_login://登录按钮
                verify();//自己创建的方法
                SMSSDK.submitVerificationCode("86", mobile_et_phone.getText().toString(), mobile_et_yzm.getText().toString());
                Intent intent2 = new Intent(MobileActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.mobile_tv_login://游客登录
                Intent intent1 = new Intent(MobileActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;

        }

    }

    //在onDestroy中注销sdk
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    //自己建立的方法
    private void verify() {
        if (TextUtils.isEmpty(mobile_et_phone.getText().toString())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mobile_et_yzm.getText().toString())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
    }


}
