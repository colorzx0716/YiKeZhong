package com.bawie.yikezhong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawie.yikezhong.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by 张肖肖 on 2017/11/26.
 */

public class Fragment32 extends Fragment{

    private View view;
    private XRecyclerView fg32_xrv;

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

       /* SharedPreferences sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", "146");*/


    }



}
