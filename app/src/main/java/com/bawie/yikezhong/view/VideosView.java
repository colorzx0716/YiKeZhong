package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.VideosBean;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/12/4.
 */

public interface VideosView extends BaseView{

    void videosSuccess(List<VideosBean.DataBean> data);
    void videosFailure(String e);


}
