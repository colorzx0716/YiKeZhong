package com.bawie.yikezhong.base;

/**
 * Created by 张肖肖 on 2017/11/27.
 * 解绑
 */

public class BasePresenter<V> {
    private V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void deatach(){
        this.mView = null;
    }
}
