package com.bawie.yikezhong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawie.yikezhong.R;

/**
 * Created by 张肖肖 on 2017/11/23.
 * 这个要改成和Fragment1页面一样，不要忘记了！！！！！
 */

public class Fragment3 extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout fg3_lt1;
    private LinearLayout fg3_lt2;
    private TextView fg3_remen;
    private TextView fg3_fujin;
    private View fg3_v_remen;
    private View fg3_v_fujin;

    private static int flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.fragment3, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //布局
        fg3_lt1 = view.findViewById(R.id.fg3_lt1);
        fg3_lt2 = view.findViewById(R.id.fg3_lt2);

        //文字
        fg3_remen = view.findViewById(R.id.fg3_remen);
        fg3_fujin = view.findViewById(R.id.fg3_fujin);

        //下划线
        fg3_v_remen = view.findViewById(R.id.fg3_v_remen);
        fg3_v_fujin = view.findViewById(R.id.fg3_v_fujin);

        //点击布局切换
        fg3_lt1.setOnClickListener(this);
        fg3_lt2.setOnClickListener(this);

        //默认热门页面
        //热门
        if(flag == 0){

            fg3_remen.setTextColor(Color.parseColor("#33ccff"));
            fg3_v_remen.setBackgroundColor(Color.parseColor("#33ccff"));
            getChildFragmentManager().beginTransaction().replace(R.id.fg3_fl,new Fragment31()).commit();

        }else if(flag == 1){

            //附近
            fg3_fujin.setTextColor(Color.parseColor("#33ccff"));
            fg3_v_fujin.setBackgroundColor(Color.parseColor("#33ccff"));
            getChildFragmentManager().beginTransaction().replace(R.id.fg3_fl,new Fragment32()).commit();
        }



    }

    //点击布局切换事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fg3_lt1:
                //热门
                flag = 0;
                fg3_remen.setTextColor(Color.parseColor("#33ccff"));
                fg3_v_remen.setBackgroundColor(Color.parseColor("#33ccff"));
                fg3_fujin.setTextColor(Color.parseColor("#000000"));
                fg3_v_fujin.setBackgroundColor(Color.parseColor("#ffffff"));

                getChildFragmentManager().beginTransaction().replace(R.id.fg3_fl,new Fragment31()).commit();

                break;
            case R.id.fg3_lt2:
                flag = 1;
                //附近
                fg3_fujin.setTextColor(Color.parseColor("#33ccff"));
                fg3_v_fujin.setBackgroundColor(Color.parseColor("#33ccff"));
                fg3_remen.setTextColor(Color.parseColor("#000000"));
                fg3_v_remen.setBackgroundColor(Color.parseColor("#ffffff"));

                getChildFragmentManager().beginTransaction().replace(R.id.fg3_fl,new Fragment32()).commit();

                break;
        }
    }
}
