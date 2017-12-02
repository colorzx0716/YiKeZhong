package com.bawie.yikezhong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.bean.AdBean;
import com.bawie.yikezhong.presenter.AdPresenter;
import com.bawie.yikezhong.view.AdView;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class Fragment11 extends Fragment implements AdView {

    private View view;
    private XBanner fg11_xbanner;
    private List<String> imgs;
    private List<String> titles;
    private AdPresenter adPresenter;
    private XRecyclerView fg11_xrv;

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

        adPresenter = new AdPresenter(this);
        adPresenter.getAdData();

    }

    //初始化控件
    private void initView() {
        //Xbanner控件
        fg11_xbanner = view.findViewById(R.id.fg11_xbanner);
        //XRecyclerview控件
        fg11_xrv = view.findViewById(R.id.fg11_xrv);

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

    @Override
    public void success() {

    }

    @Override
    public void failure() {
        System.out.println("彻底失败");

    }

    @Override
    public void AdSuccess(AdBean adBean) {

        imgs = new ArrayList<>();
        titles = new ArrayList<>();


        List<AdBean.DataBean> data = adBean.data;
        for (int i = 0; i < data.size(); i++) {
            imgs.add(data.get(i).icon);
            titles.add("");
        }

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

    @Override
    public void AdFailue(String e) {
        Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();


    }
}
