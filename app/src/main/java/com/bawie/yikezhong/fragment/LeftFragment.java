package com.bawie.yikezhong.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bawie.yikezhong.LoginActivity;
import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyListAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 张肖肖 on 2017/11/14.
 */

public class LeftFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView left_touxiang;
    private ListView left_lv;

    private List<String> tvs = new ArrayList<>();
    private List<Integer> imgs = new ArrayList<>();
    private MyListAdapter myListAdapter;
    private LinearLayout left_zuopin;
    private LinearLayout left_shezhi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.left_item, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initList();//添加文字
        initView();//初始化

        //头像圆形
        Glide.with(this).load(R.mipmap.touxiang).asBitmap().centerCrop().into(new BitmapImageViewTarget(left_touxiang){
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                left_touxiang.setImageDrawable(circularBitmapDrawable);
            }
        });

        //点击头像进入选择登录页面
        left_touxiang.setOnClickListener(this);
        //点击作品布局
        left_zuopin.setOnClickListener(this);
        //点击设置的图片
        left_shezhi.setOnClickListener(this);


        //listview的适配器
        myListAdapter = new MyListAdapter(getContext(),tvs,imgs);
        left_lv.setAdapter(myListAdapter);


        //点击条目跳入其他页面
       left_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             if(i == 0){
                 Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
             }

               if(i == 1){
                   Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
               }

               if(i == 2){
                   Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
               }

               if(i == 3){
                   Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
               }



           }
       });


    }

    private void initList() {

       tvs.add("我的关注");
       tvs.add("我的收藏");
       tvs.add("搜索好友");
       tvs.add("消息通知");

        imgs.add(R.mipmap.raw_guanzhu);
        imgs.add(R.mipmap.raw_shoucang);
        imgs.add(R.mipmap.raw_sousuo);
        imgs.add(R.mipmap.raw_xiaoxi);

    }

    //初始化控件
    private void initView() {
        //头像
        left_touxiang = view.findViewById(R.id.left_touxiang);
        //listview 的id
        left_lv = view.findViewById(R.id.left_lv);

        //我的作品的布局ID
        left_zuopin = view.findViewById(R.id.left_zuopin);
        //设置的布局ID
        left_shezhi = view.findViewById(R.id.left_shezhi);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_touxiang:
                //点击头像进入选择登录页面
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

                break;

            case R.id.left_zuopin:
                //点击我的作品

                break;

            case R.id.left_shezhi:
                //设置

                break;
        }
    }


    //条目点击事件




}
