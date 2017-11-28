package com.bawie.yikezhong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.base.BaseActivity;
import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.LoginBean;
import com.bawie.yikezhong.presenter.UserLoginPresenter;
import com.bawie.yikezhong.view.LoginView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Login2Activity extends BaseActivity implements LoginView,View.OnClickListener {

    private ImageView login2_iv_black;
    private TextView login2_tv_zhuce;
    private EditText login2_et_pwd;
    private EditText login2_et_phone;
    private Button login2_btn_login;
    private TextView login2_tv_pwd;
    private TextView login2_tv_login;
   /* private FragmentManager manager;
    private FragmentTransaction transaction;*/
    private ImageView login2_iv_top;

    private UserLoginPresenter userLoginPresenter;
    private SharedPreferences sp;

    @Override
    public List<BasePresenter> initPresenter() {
        List<BasePresenter> list = new ArrayList<>();
        list.add(userLoginPresenter);
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        sp = getSharedPreferences("sp", MODE_PRIVATE);

        //圆角图片
        login2_iv_top = findViewById(R.id.login2_iv_top);

        //返回按键
        login2_iv_black = findViewById(R.id.login2_iv_black);
        //注册账号
        login2_tv_zhuce = findViewById(R.id.login2_tv_zhuce);
        //账号
        login2_et_phone = findViewById(R.id.login2_et_phone);
        //密码
        login2_et_pwd = findViewById(R.id.login2_et_pwd);
        //登录按钮
        login2_btn_login = findViewById(R.id.login2_btn_login);
        //忘记密码
        login2_tv_pwd = findViewById(R.id.login2_tv_pwd);
        //游客登录
        login2_tv_login = findViewById(R.id.login2_tv_login);


        Glide.with(this).load(R.drawable.raw_1500977101)
                .bitmapTransform(new GlideCircleTransform(this,10))
                .into(login2_iv_top);

        //登录接口的Presenter
        userLoginPresenter = new UserLoginPresenter(this);

        //点击事件
        login2_iv_black.setOnClickListener(this);
        login2_tv_zhuce.setOnClickListener(this);
        login2_btn_login.setOnClickListener(this);
        login2_tv_pwd.setOnClickListener(this);
        login2_tv_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.login2_iv_black:
                //返回键
                finish();
                //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;

            case R.id.login2_tv_zhuce:
                //注册账号---跳转到其他页面
                Intent intent = new Intent(Login2Activity.this,ZhuceActivity.class);
                startActivity(intent);
                //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;

            case R.id.login2_btn_login:
                //登录按钮
                String pass = login2_et_phone.getText().toString();
                String user = login2_et_pwd.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
                    Toast.makeText(this, "密码或者用户名为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                sp.edit().putBoolean("isfirst",false).commit();
                userLoginPresenter.getUserLoginData(login2_et_phone.getText().toString(), login2_et_pwd.getText().toString());

                break;

            case R.id.login2_tv_pwd:
                //忘记密码,点击跳转到短信验证的页面
               Intent intent3 = new Intent(Login2Activity.this,MobileActivity.class);
                startActivity(intent3);

                break;

            case R.id.login2_tv_login:
                //游客登录
                Intent intent2 = new Intent(Login2Activity.this,MainActivity.class);
                startActivity(intent2);
                finish();
                //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;

        }

    }


    @Override
    public void success() {

    }

    @Override
    public void failure() {

        Toast.makeText(this, "失败了", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void userloginSuccess(String string) {
        Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();

        String result = string.toString();

        System.out.println("Login2页面的请求" + result);
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(result, LoginBean.class);

        String uid = String.valueOf(loginBean.data.uid);
        String token = loginBean.data.token;

        //保存在SharedPreferences里面
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isfirst",true);
        edit.putString("uid",uid);
        edit.putString("token",token);
        edit.commit();//提交


        Intent intent3 = new Intent(Login2Activity.this,MainActivity.class);
        startActivity(intent3);
        finish();
        //第一个参数为启动时动画效果，第二个参数为退出时动画效果
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    @Override
    public void userloginFailue(String string) {

        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();

    }
}
