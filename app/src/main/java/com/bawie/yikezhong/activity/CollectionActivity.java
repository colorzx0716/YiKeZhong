package com.bawie.yikezhong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bawie.yikezhong.R;

/**
 * 我的收藏
 */
public class CollectionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView cc_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initView();
        //点击事件
        cc_back.setOnClickListener(this);

    }

    private void initView() {
        cc_back = findViewById(R.id.cc_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cc_back:
                finish();//返回键
                break;
        }
    }
}
