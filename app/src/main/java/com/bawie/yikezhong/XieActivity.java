package com.bawie.yikezhong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(this, "点击了图片1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.xie_iv_img2:
                Toast.makeText(this, "点击了图片2", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
