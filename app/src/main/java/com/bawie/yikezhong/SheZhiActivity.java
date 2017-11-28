package com.bawie.yikezhong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 *
 * 设置页面。
 * 退出登录和清除缓存
 *
 */
public class SheZhiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView shezhi_back;
    private Button shezhi_login_clear;

    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);

        initView();

        //点击事件总和
        shezhi_back.setOnClickListener(this);
        shezhi_login_clear.setOnClickListener(this);
    }

    private void initView() {
        //返回键
        shezhi_back = findViewById(R.id.shezhi_back);
        //退出登陆的按钮
        shezhi_login_clear = findViewById(R.id.shezhi_login_clear);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shezhi_back:
                finish();//返回
                break;

            case R.id.shezhi_login_clear:

                //清除SharedPreferences
                sp = getSharedPreferences("sp", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();

                break;
        }



    }
}
