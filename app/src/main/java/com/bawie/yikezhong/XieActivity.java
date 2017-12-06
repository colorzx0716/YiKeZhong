package com.bawie.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.activity.DuanZiActivity;
import com.bawie.yikezhong.activity.ShiPinActivity;

public class XieActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView xie_black;
    private ImageView xie_iv_img1;
    private ImageView xie_iv_img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xie);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        xie_black = findViewById(R.id.xie_black);
        xie_iv_img1 = findViewById(R.id.xie_iv_img1);
        xie_iv_img2 = findViewById(R.id.xie_iv_img2);

        //点击登录
        xie_black.setOnClickListener(this);
        xie_iv_img1.setOnClickListener(this);
        xie_iv_img2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.xie_black:
                finish();
                break;

            case R.id.xie_iv_img1:
                //点击了段子发表
                Intent intent = new Intent(XieActivity.this, DuanZiActivity.class);
                startActivity(intent);
                break;

            case R.id.xie_iv_img2:
                //点击了视频发表
                Toast.makeText(this, "视频还没做呢！", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(XieActivity.this,ShiPinActivity.class);
                startActivity(intent1);

                break;

        }

    }
}
