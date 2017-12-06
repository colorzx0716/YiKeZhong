package com.bawie.yikezhong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyFg11Adapter;
import com.bawie.yikezhong.bean.AdBean;
import com.bawie.yikezhong.bean.VideosBean;
import com.bawie.yikezhong.presenter.AdPresenter;
import com.bawie.yikezhong.presenter.VideosPresenter;
import com.bawie.yikezhong.view.AdView;
import com.bawie.yikezhong.view.VideosView;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class Fragment11 extends Fragment implements AdView, VideosView {

    private View view;
   // private XBanner fg11_xbanner;
    private List<String> imgs;
    private List<String> titles;
    private AdPresenter adPresenter;
    private XRecyclerView fg11_xrv;
    private VideosPresenter videosPresenter;


    private String page = "1";//页码
    private String type = "1";//类型
    private int p = 1;
    private SharedPreferences sp;
    private XBanner fg11_head_xbanner;
    private MyFg11Adapter myFg11Adapter;
    private List<VideosBean.DataBean> list;
    private String uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment11, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);

        initView();
        initData();

    }

    //初始化
    private void initData() {

        list = new ArrayList<>();
        adPresenter = new AdPresenter(this);
        adPresenter.getAdData();

        uid = sp.getString("uid", "146");
        videosPresenter = new VideosPresenter(this);
        videosPresenter.getVideosModel(uid,type,page);

    }

    //初始化控件
    private void initView() {
        //Xbanner控件

        View view2 = View.inflate(getActivity(), R.layout.head_item, null);
        fg11_head_xbanner = view2.findViewById(R.id.fg11_head_xbanner);

        //XRecyclerview控件
        fg11_xrv = view.findViewById(R.id.fg11_xrv);

        //加载刷新
        fg11_xrv.setLoadingMoreEnabled(true);
        fg11_xrv.setPullRefreshEnabled(true);


        //添加头部
        fg11_xrv.addHeaderView(view2);

    }

    @Override
    public void onResume() {
        super.onResume();
        fg11_head_xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        fg11_head_xbanner.stopAutoPlay();
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

        //添加数据
        imgs = new ArrayList<>();
        titles = new ArrayList<>();

        List<AdBean.DataBean> data = adBean.data;
        for (int i = 0; i < data.size(); i++) {
            imgs.add(data.get(i).icon);
            titles.add("");
        }

        //绑定数据
        fg11_head_xbanner.setData(imgs,titles);
        //Xbanner适配数据
        fg11_head_xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getContext()).load(imgs.get(position)).into((ImageView) view);
            }
        });

        // 设置XBanner的页面切换特效
        fg11_head_xbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        fg11_head_xbanner.setPageChangeDuration(2000);

    }

    @Override
    public void AdFailue(String e) {

    }

    @Override
    public void videosSuccess(List<VideosBean.DataBean> data) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        fg11_xrv.setLayoutManager(linearLayoutManager);
        myFg11Adapter = new MyFg11Adapter(getActivity(),data);
        fg11_xrv.setAdapter(myFg11Adapter);

        list.addAll(data);//添加更多

        if(myFg11Adapter == null)
        {
            myFg11Adapter = new MyFg11Adapter(getActivity(),list);
            fg11_xrv.setAdapter(myFg11Adapter);

        }else
        {
            myFg11Adapter.notifyDataSetChanged();

        }

        fg11_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                list.clear();
                videosPresenter.getVideosModel(uid,type,page);
                Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();
                fg11_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                p++;
                videosPresenter.getVideosModel(uid,type,p+"");
                Toast.makeText(getActivity(), "加载", Toast.LENGTH_SHORT).show();
                fg11_xrv.loadMoreComplete();

            }
        });
    }

    @Override
    public void videosFailure(String e) {

    }
}
