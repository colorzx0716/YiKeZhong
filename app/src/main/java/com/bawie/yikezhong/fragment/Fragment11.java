package com.bawie.yikezhong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawie.yikezhong.R;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class Fragment11 extends Fragment {

    private View view;
    private XBanner fg11_xbanner;
    private List<String> imgs;
    private List<String> titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.fragment11, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();


    }

    //初始化
    private void initData() {
        imgs = new ArrayList<>();
        titles = new ArrayList<>();

        imgs.add("http://img15.3lian.com/2015/a1/16/d/205.jpg");
        imgs.add("https://cdn.duitang.com/uploads/item/201506/20/20150620013123_uirKf.jpeg");
        imgs.add("http://img2.100bt.com/upload/ttq/20131208/1386496341645_middle.jpg");
        titles.add("");
        titles.add("");
        titles.add("");

        //绑定数据
        fg11_xbanner.setData(imgs,titles);
        //Xbanner适配数据
        fg11_xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(imgs.get(position)).into((ImageView) view);
            }
        });

        // 设置XBanner的页面切换特效
        fg11_xbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        fg11_xbanner.setPageChangeDuration(2000);


    }

    //初始化控件
    private void initView() {
        fg11_xbanner = view.findViewById(R.id.fg11_xbanner);
    }


    @Override
    public void onResume() {
        super.onResume();
        fg11_xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        fg11_xbanner.stopAutoPlay();
    }
}
