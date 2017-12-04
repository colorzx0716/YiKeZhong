package com.bawie.yikezhong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyFg32Adapter;
import com.bawie.yikezhong.bean.VideosBean;
import com.bawie.yikezhong.presenter.VideosPresenter;
import com.bawie.yikezhong.view.VideosView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/11/26.
 */

public class Fragment32 extends Fragment implements VideosView {


    private View view;
    private XRecyclerView fg32_xrv;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private VideosPresenter videosPresenter;

    private String page = "1";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.fragment32, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fg32_xrv = view.findViewById(R.id.fg32_xrv);

        SharedPreferences sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", "146");

        videosPresenter = new VideosPresenter(this);
        videosPresenter.getVideosModel(uid,page);

    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }

    @Override
    public void videosSuccess(List<VideosBean.DataBean> data) {


        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);

        fg32_xrv.setLayoutManager(staggeredGridLayoutManager);
        MyFg32Adapter myFg32Adapter = new MyFg32Adapter(getActivity(),data);
        fg32_xrv.setAdapter(myFg32Adapter);

    }

    @Override
    public void videosFailure(String e) {

    }




}
