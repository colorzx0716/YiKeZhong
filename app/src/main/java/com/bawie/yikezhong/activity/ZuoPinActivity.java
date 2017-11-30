package com.bawie.yikezhong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bawie.yikezhong.R;

/**
 * 我的作品页面
 */
public class ZuoPinActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView zp_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_pin);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initView();

        //点击事件
        zp_back.setOnClickListener(this);

    }

    private void initView() {
        //返回键
        zp_back = findViewById(R.id.zp_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zp_back:
                //返回键
                finish();
                break;
        }
    }
}
