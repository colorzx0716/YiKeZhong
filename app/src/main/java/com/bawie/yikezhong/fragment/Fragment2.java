package com.bawie.yikezhong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.adapter.MyFg2Adapter;
import com.bawie.yikezhong.bean.UserJoker;
import com.bawie.yikezhong.presenter.UserJokerPresenter;
import com.bawie.yikezhong.view.UserJokerView;

/**
 * 段子的主页面
 */

public class Fragment2 extends Fragment implements UserJokerView{
    private View view;
    private RecyclerView fg2_rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.fragment2, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();

    }

    private void initData() {

      /*  SharedPreferences sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        String token = sp.getString("token", "929A310F425923598C7F3495BCAAA278");
        System.out.println("token的值！！ = " + token);*/
        UserJokerPresenter userJokerPresenter = new UserJokerPresenter(this);
        userJokerPresenter.getJokesData("1","");

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

        fg2_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        MyFg2Adapter myFg2Adapter = new MyFg2Adapter(getActivity(),value.data);
        fg2_rv.setAdapter(myFg2Adapter);

    }

    @Override
    public void getJokesFailure(String e) {
        System.out.println("===========失败========");
    }
}
