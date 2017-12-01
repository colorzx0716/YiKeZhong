package com.bawie.yikezhong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.activity.GaoDeActivity;
import com.bawie.yikezhong.base.GlideCacheUtil;

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
    private TextView she_tv_gengxin;
    private TextView she_tv_yijian;
    private TextView she_tv_map;
    private TextView she_tv_huancun;
    private TextView she_tv_hcnum;
    private GlideCacheUtil glideCacheUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initView();
        initData();

        glideCacheUtil = new GlideCacheUtil();

        //点击事件总和
        shezhi_back.setOnClickListener(this);
        shezhi_login_clear.setOnClickListener(this);
        she_tv_huancun.setOnClickListener(this);//清除缓存
        she_tv_map.setOnClickListener(this);
    }

    private void initData() {

        //获取Glide造成的缓存大小
        she_tv_hcnum.setText(glideCacheUtil.getInstance().getCacheSize(this));


    }

    private void initView() {
        //返回键
        shezhi_back = findViewById(R.id.shezhi_back);
        //退出登陆的按钮
        shezhi_login_clear = findViewById(R.id.shezhi_login_clear);

        //依次是
        she_tv_gengxin = findViewById(R.id.she_tv_gengxin);
        she_tv_yijian = findViewById(R.id.she_tv_yijian);
        //高德地图
        she_tv_map = findViewById(R.id.she_tv_map);

        she_tv_huancun = findViewById(R.id.she_tv_huancun);
        //缓存的数量
        she_tv_hcnum = findViewById(R.id.she_tv_hcnum);

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

                Intent intent = new Intent(SheZhiActivity.this,Login2Activity.class);
                startActivity(intent);

                break;

            case R.id.she_tv_huancun://清除缓存
                glideCacheUtil.clearImageDiskCache(this);
                Toast.makeText(this, "清除缓存", Toast.LENGTH_SHORT).show();
                she_tv_hcnum.setText("");
                break;

            case R.id.she_tv_map://高德地图
                Intent intent1 = new Intent(SheZhiActivity.this,GaoDeActivity.class);
                startActivity(intent1);

                break;
        }

    }
}
