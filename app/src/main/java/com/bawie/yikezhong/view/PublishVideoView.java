package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.PublishVideo;

/**
 * Created by 张肖肖 on 2017/12/6.
 */

public interface PublishVideoView extends BaseView{

    void getPublicVideoSuccess(PublishVideo publishVideo);
    void getPublishVideoFailure(String msg);

}
