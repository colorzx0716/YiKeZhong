package com.bawie.yikezhong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.R;

/**
 * 我的关注
 */
public class WoGuanzhuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView gz_remen;
    private ImageView gz_back;
    private ListView gz_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_guanzhu);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initView();

        initData();

        //点击事件
        gz_back.setOnClickListener(this);
        gz_remen.setOnClickListener(this);

    }

    //初始化
    private void initData() {






    }

    private void initView() {
        //返回键
        gz_back = findViewById(R.id.gz_back);
        //热门关注
        gz_remen = findViewById(R.id.gz_remen);
        //关注列表
        gz_lv = findViewById(R.id.gz_lv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gz_back:
                //返回键
                finish();
                break;
            case R.id.gz_remen:
                Toast.makeText(this, "点击了热门关注", Toast.LENGTH_SHORT).show();
                break;
            
        }
    }
}
