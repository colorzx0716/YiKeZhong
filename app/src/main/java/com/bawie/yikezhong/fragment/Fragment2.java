package com.bawie.yikezhong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawie.yikezhong.Login2Activity;
import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyFg2Adapter;
import com.bawie.yikezhong.bean.UserJoker;
import com.bawie.yikezhong.presenter.UserJokerPresenter;
import com.bawie.yikezhong.view.UserJokerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 段子的主页面
 */

public class Fragment2 extends Fragment implements UserJokerView{
    private View view;
    private XRecyclerView fg2_rv;

    private String page = "1";
    private int p = 1;

    private UserJokerPresenter userJokerPresenter;
    private MyFg2Adapter myFg2Adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<UserJoker.DataBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment2, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
    }


    private void initData() {
        list = new ArrayList<>();

        userJokerPresenter = new UserJokerPresenter(this);
        userJokerPresenter.getJokesData(page,"");

        //加载刷新
        fg2_rv.setLoadingMoreEnabled(true);
        fg2_rv.setPullRefreshEnabled(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        fg2_rv.setLayoutManager(linearLayoutManager);

    }

    private void initView() {
        fg2_rv = view.findViewById(R.id.fg2_rv);

    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }

    @Override
    public void getJokesSuccess(UserJoker value) {
        System.out.println("=========成功========");

       String code = value.code;
       if(code.equals("2")){
           //超时就跳转到登录页面
           Intent intent = new Intent(getActivity(), Login2Activity.class);
           getActivity().startActivity(intent);
           getActivity().finish();
       }

       /* myFg2Adapter = new MyFg2Adapter(getActivity(),value.data);
        fg2_rv.setAdapter(myFg2Adapter);*/

        //加载更多的监听器
        list.addAll(value.data);

        if(myFg2Adapter == null)
        {
          myFg2Adapter = new MyFg2Adapter(getActivity(),list);
          fg2_rv.setAdapter(myFg2Adapter);

        }else
        {
            myFg2Adapter.notifyDataSetChanged();

        }

        fg2_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                list.clear();
                userJokerPresenter.getJokesData(page,"");
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                fg2_rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                p++;
                userJokerPresenter.getJokesData(p+"","");
                Toast.makeText(getActivity(), "加载", Toast.LENGTH_SHORT).show();
                fg2_rv.loadMoreComplete();

            }
        });

    }

    @Override
    public void getJokesFailure(String e) {
        System.out.println("===========失败========");
    }

}
