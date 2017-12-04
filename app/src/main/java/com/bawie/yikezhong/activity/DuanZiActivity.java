package com.bawie.yikezhong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyFg2sAdapter;
import com.bawie.yikezhong.base.BaseActivity;
import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.base.GlideLoader;
import com.bawie.yikezhong.bean.PublishBean;
import com.bawie.yikezhong.presenter.PublicPresenter;
import com.bawie.yikezhong.view.PublicView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 发表段子的页面
 *
 */
public class DuanZiActivity extends BaseActivity implements View.OnClickListener, PublicView {

    private TextView dz_back;
    private TextView dz_tv_fabiao;
    private EditText dz_et_content;

    //地址路径
    private ArrayList<String> path = new ArrayList<>();

    private SharedPreferences sp;
    private String uid;
    private ImageView dz_iv_jia;
    public static final int REQUEST_CODE = 1000;
    private RecyclerView dz_rv;
    private MyFg2sAdapter adapter;
    private PublicPresenter publicPresenter;

    @Override
    public List<BasePresenter> initPresenter() {
        List<BasePresenter> list = new ArrayList<>();
        list.add(publicPresenter);
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duan_zi);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        sp = getSharedPreferences("sp", MODE_PRIVATE);

        initView();

        //发表段子的Presenter
        publicPresenter = new PublicPresenter(this);

        //点击事件
        dz_back.setOnClickListener(this);
        dz_tv_fabiao.setOnClickListener(this);
        dz_iv_jia.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        dz_rv.setLayoutManager(gridLayoutManager);
        adapter = new MyFg2sAdapter(this, path);
        dz_rv.setAdapter(adapter);

    }

    //初始化
    private void initView() {
        //返回
        dz_back = findViewById(R.id.dz_back);
        //发表
        dz_tv_fabiao = findViewById(R.id.dz_tv_fabiao);
        //输入框
        dz_et_content = findViewById(R.id.dz_et_content);
        //图片添加符号
        dz_iv_jia = findViewById(R.id.dz_iv_jia);
        //recyclerview
        dz_rv = findViewById(R.id.dz_rv);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dz_back:
                //返回键
                finish();
                break;

            case R.id.dz_tv_fabiao:
                //发表文字
                uid = sp.getString("uid", "146");
                String token = sp.getString("token", "929A310F425923598C7F3495BCAAA278");

                String content = dz_et_content.getText().toString();

                publicPresenter.getPublishJokeData(uid,content,token);
                System.out.println("发表的段子 = " + content);

                finish();
                break;

            case R.id.dz_iv_jia:
                //图片添加-点击事件
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
//                        .crop()
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

                ImageSelector.open(DuanZiActivity.this, imageConfig); // 开启图片选择器
                //=========

                break;

        }
    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {
        System.out.println(" failure彻底========失败 ");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }

            path.clear();
            path.addAll(pathList);
            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public void userloginSuccess(PublishBean publishBean) {
        System.out.println("发表段子成功了 ====== " + publishBean);
    }

    @Override
    public void userloginFailue(String e) {
        System.out.println("发表段子失败了 = " + e);

    }
}
