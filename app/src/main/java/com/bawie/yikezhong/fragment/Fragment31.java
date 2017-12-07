package com.bawie.yikezhong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyFg31Adapter;
import com.bawie.yikezhong.base.SpacesItemDecoration;
import com.bawie.yikezhong.bean.HotVideosBean;
import com.bawie.yikezhong.presenter.HotVideosPresenter;
import com.bawie.yikezhong.view.HotVideosView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张肖肖 on 2017/11/26.
 */

public class Fragment31 extends Fragment implements HotVideosView {
    private View view;
    private XRecyclerView fg31_xrv;
    private MyFg31Adapter myFg31Adapter;
    private HotVideosPresenter hotVideosPresenter;
    private List<HotVideosBean.DataBean> list;
    private int p = 1;

    private String page = "1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.fragment31, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //新建list
        list = new ArrayList<>();
        //XRecyclerview控件
        fg31_xrv = view.findViewById(R.id.fg31_xrv);

        hotVideosPresenter = new HotVideosPresenter(this);
        hotVideosPresenter.getHotVideosData(page);

        //刷新加载
        //加载刷新
        fg31_xrv.setLoadingMoreEnabled(true);
        fg31_xrv.setPullRefreshEnabled(true);

    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }

    @Override
    public void getHotVideoSuccess(List<HotVideosBean.DataBean> data) {

        myFg31Adapter = new MyFg31Adapter(getContext(),data);
        fg31_xrv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        fg31_xrv.setAdapter(myFg31Adapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        fg31_xrv.addItemDecoration(decoration);
        fg31_xrv.setRefreshProgressStyle(10);
        fg31_xrv.setLoadingMoreProgressStyle(10);


        list.addAll(data);//添加更多

        if(myFg31Adapter == null)
        {
            myFg31Adapter = new MyFg31Adapter(getActivity(),list);
            fg31_xrv.setAdapter(myFg31Adapter);

        }else
        {
            myFg31Adapter.notifyDataSetChanged();
           // myFg31Adapter.notifyItemInserted(1);

        }

        //刷新加载监听
        fg31_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                list.clear();
                hotVideosPresenter.getHotVideosData(page);
                Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();
                fg31_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                p++;
                hotVideosPresenter.getHotVideosData(p+"");
                Toast.makeText(getActivity(), "加载", Toast.LENGTH_SHORT).show();
                fg31_xrv.loadMoreComplete();

            }
        });


    }

    @Override
    public void getHotVideoFailure(String msg) {
        hotVideosPresenter.getHotVideoFailure(msg);
    }
}
