package com.bawie.yikezhong.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.base.BaseActivity;
import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.presenter.PublicPresenter;
import com.bawie.yikezhong.view.PublicView;

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

    private PublicPresenter publicPresenter;
    private SharedPreferences sp;
    private String uid;
    private String token;

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

        sp = getSharedPreferences("sp", MODE_PRIVATE);

        initView();

        //发表段子的Presenter
        publicPresenter = new PublicPresenter(this);

        //点击事件
        dz_back.setOnClickListener(this);
        dz_tv_fabiao.setOnClickListener(this);

    }

    //初始化
    private void initView() {
        dz_back = findViewById(R.id.dz_back);
        dz_tv_fabiao = findViewById(R.id.dz_tv_fabiao);
        dz_et_content = findViewById(R.id.dz_et_content);

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
                token = sp.getString("token", "929A310F425923598C7F3495BCAAA278");

                String content = dz_et_content.getText().toString();
                System.out.println("写的内容=======" + content);


                publicPresenter.getPublicData(uid,content,token);
                finish();

                break;

        }
    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {
        System.out.println(" failure失败 ");
    }

    @Override
    public void getPublicSuccess(String publishBean) {
        System.out.println(" getPublicSuccess成功 ");


    }

    @Override
    public void getPublicFailure(String e) {
        System.out.println("getPublicFailure");
    }
}
