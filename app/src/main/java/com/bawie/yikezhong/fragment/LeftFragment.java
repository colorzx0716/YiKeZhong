package com.bawie.yikezhong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.LoginActivity;
import com.bawie.yikezhong.R;
import com.bawie.yikezhong.SheZhiActivity;
import com.bawie.yikezhong.activity.CollectionActivity;
import com.bawie.yikezhong.activity.NoticeActivity;
import com.bawie.yikezhong.activity.SouSuoActivity;
import com.bawie.yikezhong.activity.WoGuanzhuActivity;
import com.bawie.yikezhong.adapter.MyListAdapter;
import com.bawie.yikezhong.bean.UserBean;
import com.bawie.yikezhong.presenter.UserInfoPresenter;
import com.bawie.yikezhong.view.UserInfoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 张肖肖 on 2017/11/14.
 */

public class LeftFragment extends Fragment implements View.OnClickListener, UserInfoView {

    private View view;
    private ImageView left_touxiang;
    private ListView left_lv;

    private List<String> tvs = new ArrayList<>();
    private List<Integer> imgs = new ArrayList<>();
    private MyListAdapter myListAdapter;
    private LinearLayout left_zuopin;
    private LinearLayout left_shezhi;
    private SharedPreferences sp;
    private TextView left_tv_nicheng;


    private UserInfoPresenter userInfoPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.left_item, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        sp = getContext().getSharedPreferences("sp", Context.MODE_PRIVATE);
        initList();//添加文字
        initView();//初始化


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
                 //我的关注
                 Intent intent = new Intent(getContext(), WoGuanzhuActivity.class);
                 startActivity(intent);

             }

               if(i == 1){
                   //我的收藏
                   Intent intent = new Intent(getContext(),CollectionActivity.class);
                   startActivity(intent);

               }

               if(i == 2){
                 //搜索好友
                   Intent intent = new Intent(getContext(), SouSuoActivity.class);
                   startActivity(intent);

               }

               if(i == 3){
                  //消息通知
                   Intent intent = new Intent(getContext(), NoticeActivity.class);
                   startActivity(intent);
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
        //昵称
        left_tv_nicheng = view.findViewById(R.id.left_tv_nicheng);
        //我的作品的布局ID
        left_zuopin = view.findViewById(R.id.left_zuopin);
        //设置的布局ID
        left_shezhi = view.findViewById(R.id.left_shezhi);

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

        //=================
        //用户信息的Presenter
        String uid = sp.getString("uid", "146");
        String token = sp.getString("token", "929A310F425923598C7F3495BCAAA278");

        userInfoPresenter = new UserInfoPresenter(this);
        userInfoPresenter.getUserInfoData(uid,token);



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
                Intent intent2 = new Intent(getContext(), WoGuanzhuActivity.class);
                startActivity(intent2);

                break;

            case R.id.left_shezhi:
                //设置
                Intent intent1 = new Intent(getContext(), SheZhiActivity.class);
                startActivity(intent1);
                break;

        }
    }


    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }

    @Override
    public void userinfoSuccess(String string) {
   Toast.makeText(getContext(), "====获取用户信息成功====", Toast.LENGTH_SHORT).show();
        String result = string.toString();

        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(result, UserBean.class);
        //属性
        //头像
        String icon = userBean.data.icon;
        //昵称
        String nickname = userBean.data.nickname;

        //==================

        boolean isfirst = sp.getBoolean("isfirst", false);
        if(isfirst){

            Glide.with(this).load(icon).asBitmap().centerCrop().into(new BitmapImageViewTarget(left_touxiang){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    left_touxiang.setImageDrawable(circularBitmapDrawable);
                }
            });
            //昵称
            left_tv_nicheng.setText(nickname);

            //====================
        }

    }

    @Override
    public void userinfoFailue(String string) {
        Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
    }
}
