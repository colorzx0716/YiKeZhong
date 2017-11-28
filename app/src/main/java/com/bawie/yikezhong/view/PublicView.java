package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;

/**
 *发表段子的view
 */

public interface PublicView extends BaseView{
    void getPublicSuccess(String publishBean);
    void getPublicFailure(String e);
}
