package com.bawie.yikezhong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bawie.yikezhong.R;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

/**
 * 预览页面
 */
public class YuLanActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout yl_rl;
    private Button yl_btn_xiayibu;
    private String uri;
    private String weidu;
    private String jingdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_lan);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        //视频地址
        Intent intent = getIntent();
        uri = intent.getStringExtra("uri");
        weidu = intent.getStringExtra("weidu");
        jingdu = intent.getStringExtra("jingdu");

        //保存在sp里面
        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        sp.edit().putString("shipinurl",uri).commit();

        System.out.println("YuLanActivity页面  ====== " + uri);

        initView();
    }

    private void initView() {
        //视频预览
        yl_rl = findViewById(R.id.yl_rl);
        //下一步
        yl_btn_xiayibu = findViewById(R.id.yl_btn_xiayibu);

        View player = View.inflate(this, R.layout.simple_player_view_player,yl_rl);

        PlayerView playerView = new PlayerView(this, player);
        playerView.setScaleType(PlayStateParams.fitparent);
        playerView.hideMenu(true);
        playerView.forbidTouch(false);
        playerView.setPlaySource(uri);
        playerView.startPlay();

        //下一步
        yl_btn_xiayibu.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yl_btn_xiayibu:
                //下一步
                Intent intent = new Intent(YuLanActivity.this,PublishCoverActivity.class);
                intent.putExtra("veruri",uri);
                intent.putExtra("weidu",weidu);
                intent.putExtra("jingdu",jingdu);
                startActivity(intent);

                break;
        }
    }
}
