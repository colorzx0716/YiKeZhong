package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.UserJoker;

/**
 *段子页面的view
 */

public interface UserJokerView extends BaseView{
    void getJokesSuccess(UserJoker userJoker);
    void getJokesFailure(String e);


}
