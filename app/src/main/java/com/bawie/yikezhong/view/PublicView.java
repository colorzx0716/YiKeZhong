package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.PublishBean;

/**
 *发表段子的view
 */

public interface PublicView extends BaseView{
    void userloginSuccess(PublishBean publishBean);
    void userloginFailue(String e);
}
