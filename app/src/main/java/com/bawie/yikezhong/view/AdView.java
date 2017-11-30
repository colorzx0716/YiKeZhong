package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.AdBean;

/**
 *广告的View
 */

public interface AdView extends BaseView{

    void AdSuccess(AdBean adBean);
    void AdFailue(String e);

}
