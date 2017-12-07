package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.HotVideosBean;

import java.util.List;

/**
 * 热门视频的层V
 */

public interface HotVideosView extends BaseView{

    void getHotVideoSuccess(List<HotVideosBean.DataBean> data);
    void getHotVideoFailure(String msg);

}
