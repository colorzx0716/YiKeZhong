package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;

/**
 * 登录的View，需要继承BaseView
 */

public interface LoginView extends BaseView {
    void userloginSuccess(String string);
    void userloginFailue(String string);
}
