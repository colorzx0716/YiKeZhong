package com.bawie.yikezhong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.base.GlideLoader;
import com.bawie.yikezhong.bean.PublishVideo;
import com.bawie.yikezhong.presenter.PublishVideoPresenter;
import com.bawie.yikezhong.view.PublishVideoView;
import com.bumptech.glide.Glide;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布视频
 *
 */
public class PublishCoverActivity extends AppCompatActivity implements View.OnClickListener, PublishVideoView {

    private Button pc_btn_cover;
    private Button pc_btn_public;
    private ImageView pc_iv_head;
    public static final int REQUEST_CODE = 1000;

    private String uri;
    private String weidu;
    private String jingdu;

    private File cover;
   // private File video;

    private ArrayList<String> path = new ArrayList<>();
    private EditText pc_et_desc;
    private String uid;
    private PublishVideoPresenter publishVideoPresenter;
    private String s;
    private File fileVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_cover);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initView();

        publishVideoPresenter = new PublishVideoPresenter(this);

    }

    private void initView() {

        //视频地址
        Intent intent = getIntent();
        uri = intent.getStringExtra("uri");
        weidu = intent.getStringExtra("weidu");
        jingdu = intent.getStringExtra("jingdu");

        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        s = sp.getString("shipinurl", "");

        System.out.println("PublishCoverActivity页面 ====== " + uri);
        System.out.println("PublishCoverActivity页面sp的地址 ====== " + s);
       // video = new File(uri);
       // System.out.println("=======传过来的视频路径======="+ video);
        //设置封面按钮
        pc_btn_cover = findViewById(R.id.pc_btn_cover);
        //发布按钮
        pc_btn_public = findViewById(R.id.pc_btn_public);
        //头像
        pc_iv_head = findViewById(R.id.pc_iv_head);
        //简介
        pc_et_desc = findViewById(R.id.pc_et_desc);

        //点击事件
        pc_btn_cover.setOnClickListener(this);
        pc_btn_public.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pc_btn_cover:
                //图片选择按钮
                ImageConfig imageConfig
                        = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.blue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.blue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        .singleSelect()
                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(PublishCoverActivity.this, imageConfig); // 开启图片选择器

                break;
            case R.id.pc_btn_public:
                //发布按钮
                String desc = pc_et_desc.getText().toString();
               // System.out.println(video+"             "+cover);
                SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
                uid = sp.getString("uid", "146");

                if(path != null){
                    fileVideo = new File(s);
                    publishVideoPresenter.getPublishVideoData(uid, fileVideo,cover,desc,weidu,jingdu);
                }

                Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String s : pathList) {
                Glide.with(this).load(s).into(pc_iv_head);
                cover = new File(s);
                System.out.println("s ======================================= " + s);
            }
            path.clear();
            path.addAll(pathList);
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {
        System.out.println("failure失败");
    }

    @Override
    public void getPublicVideoSuccess(PublishVideo publishVideo) {

    }

    @Override
    public void getPublishVideoFailure(String msg) {
        System.out.println("getPublishVideoFailure失败");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
