package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.HotVideosBean;
import com.bawie.yikezhong.model.HotVideosModel;
import com.bawie.yikezhong.view.HotVideosView;

/**
 * 热门视频的P层
 */

public class HotVideosPresenter extends BasePresenter implements HotVideosModel.GetHotVideoMessage {

    private HotVideosView hotVideosView;
    private HotVideosModel hotVideosModel;

    public HotVideosPresenter(HotVideosView hotVideosView) {
        super(hotVideosView);
        this.hotVideosView = hotVideosView;
        if(hotVideosModel== null){
            hotVideosModel = new HotVideosModel();
        }

        hotVideosModel.setGetHotVideoMessage(this);
    }

    //自己定义方法
    public void getHotVideosData(String page){
        hotVideosModel.getHotVideosData(page);
    }

    @Override
    public void getHotVideoSuccess(HotVideosBean videosBean) {

        hotVideosView.getHotVideoSuccess(videosBean.data);

    }

    @Override
    public void getHotVideoFailure(String msg) {
        hotVideosView.failure();
    }
}
