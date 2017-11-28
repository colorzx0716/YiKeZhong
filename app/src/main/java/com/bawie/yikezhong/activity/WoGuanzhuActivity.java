package com.bawie.yikezhong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.R;

/**
 * 我的关注
 */
public class WoGuanzhuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView gz_remen;
    private ImageView gz_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_guanzhu);

        initView();


        //点击事件
        gz_back.setOnClickListener(this);
        gz_remen.setOnClickListener(this);

    }

    private void initView() {
        //返回键
        gz_back = findViewById(R.id.gz_back);
        //热门关注
        gz_remen = findViewById(R.id.gz_remen);
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
