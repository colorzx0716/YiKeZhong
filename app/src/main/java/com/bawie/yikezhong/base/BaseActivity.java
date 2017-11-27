package com.bawie.yikezhong.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bawie.yikezhong.R;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract List<BasePresenter> initPresenter();//初始化数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        List<BasePresenter> basePresenters = initPresenter();
        if(basePresenters != null){
            for (BasePresenter basePresenter : basePresenters) {
                basePresenter.deatach();
            }
        }
    }
}
