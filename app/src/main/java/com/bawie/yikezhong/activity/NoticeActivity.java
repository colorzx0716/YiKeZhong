package com.bawie.yikezhong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bawie.yikezhong.R;

/**
 * 通知页面
 */
public class NoticeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView tz_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        initView();


        //点击事件
       tz_back.setOnClickListener(this);


    }

    private void initView() {
        tz_back = findViewById(R.id.tz_back);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tz_back:
                finish();//返回键
                break;

        }
    }
}
