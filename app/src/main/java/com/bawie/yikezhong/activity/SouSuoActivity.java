package com.bawie.yikezhong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bawie.yikezhong.R;

/**
 * 搜索好友的页面
 */
public class SouSuoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ss_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();


        //点击事件
        ss_back.setOnClickListener(this);

    }


    private void initView() {
        ss_back = findViewById(R.id.ss_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ss_back:
                finish();//返回键
                break;
        }
    }
}
