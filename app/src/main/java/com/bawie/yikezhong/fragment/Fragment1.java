package com.bawie.yikezhong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawie.yikezhong.R;

/**
 * Created by 张肖肖 on 2017/11/23.
 */

public class Fragment1 extends Fragment implements View.OnClickListener {
    private View view;
    private TextView fg1_guanzhu;
    private TextView fg1_remen;
    private View fg1_v_guanzhu;
    private View fg1_v_remen;
    private LinearLayout fg1_lt1;
    private LinearLayout fg1_lt2;
    private FragmentManager fm;

    private Fragment11 fragment11;
    private Fragment12 fragment12;

    private static int flag;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment1, null);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();

        //Fragment的初始化
        fm = getFragmentManager();

        //默认热门页面
        //热门

        if(flag == 0){
            fg1_remen.setTextColor(Color.parseColor("#33ccff"));
            fg1_v_remen.setBackgroundColor(Color.parseColor("#33ccff"));
            showFragment(1);
        }else if(flag == 1){
            //关注
            fg1_guanzhu.setTextColor(Color.parseColor("#33ccff"));
            fg1_v_guanzhu.setBackgroundColor(Color.parseColor("#33ccff"));
            showFragment(2);
        }





        //getChildFragmentManager().beginTransaction().replace(R.id.fg1_fl,new Fragment11()).commit();
    }

    //初始化控件，点击事件
    private void initViews() {
        //文字
        fg1_guanzhu = view.findViewById(R.id.fg1_guanzhu);
        fg1_remen = view.findViewById(R.id.fg1_remen);
        //下划线
        fg1_v_guanzhu = view.findViewById(R.id.fg1_v_guanzhu);
        fg1_v_remen = view.findViewById(R.id.fg1_v_remen);
        //布局id
        fg1_lt1 = view.findViewById(R.id.fg1_lt1);
        fg1_lt2 = view.findViewById(R.id.fg1_lt2);

        //点击布局切换Fragment
        fg1_lt1.setOnClickListener(this);
        fg1_lt2.setOnClickListener(this);

    }

    //显示Fragment的方法
    private void showFragment(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        //想要显示一个Fragment，先隐藏所有的Fragment，防止重叠
        hideFragment(ft);

        switch (index){
            case 1:
                //如果Fragment11已经存在则将其显示出来
                if(fragment11 != null){
                    ft.show(fragment11);
                }else{
                    //否则是第一次切换Fragment11，注意添加后是会显示出来的，replace方法也是先remove后add
                    fragment11 = new Fragment11();
                    ft.add(R.id.fg1_fl,fragment11);
                }

                break;

            case 2:
                if(fragment12 != null){
                    ft.show(fragment12);
                }else{
                    fragment12 = new Fragment12();
                    ft.add(R.id.fg1_fl,fragment12);
                }

                break;
        }
        ft.commit();//提交

    }

    //隐藏Fragment的方法
    private void hideFragment(FragmentTransaction ft) {
        if(fragment11 != null){
            ft.hide(fragment11);
        }

        if(fragment12 != null){
            ft.hide(fragment12);
        }

    }

    @Override
    public void onClick(View view) {

        clearState();//清除状态

        switch (view.getId()){
            case R.id.fg1_lt1:
                flag = 0;
                //热门
                fg1_remen.setTextColor(Color.parseColor("#33ccff"));
                fg1_v_remen.setBackgroundColor(Color.parseColor("#33ccff"));
                showFragment(1);

               // getChildFragmentManager().beginTransaction().replace(R.id.fg1_fl,new Fragment11()).commit();

                break;
            case R.id.fg1_lt2:
                flag = 1;
                //关注
                fg1_guanzhu.setTextColor(Color.parseColor("#33ccff"));
                fg1_v_guanzhu.setBackgroundColor(Color.parseColor("#33ccff"));
                showFragment(2);

               // getChildFragmentManager().beginTransaction().replace(R.id.fg1_fl,new Fragment12()).commit();
                break;
        }
    }

    //清除状态
    private void clearState() {
        //关注
        fg1_guanzhu.setTextColor(Color.parseColor("#000000"));
        fg1_v_guanzhu.setBackgroundColor(Color.parseColor("#ffffff"));
        //热门
        fg1_remen.setTextColor(Color.parseColor("#000000"));
        fg1_v_remen.setBackgroundColor(Color.parseColor("#ffffff"));
    }


}
