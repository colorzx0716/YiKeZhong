package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.VideosBean;
import com.bawie.yikezhong.model.VideosModel;
import com.bawie.yikezhong.view.VideosView;

/**
 * Created by 张肖肖 on 2017/12/4.
 */

public class VideosPresenter extends BasePresenter{
    private  VideosView videosView;
    private VideosModel videosModel;

    public VideosPresenter(VideosView videosView) {
        super(videosView);
        this.videosView = videosView;
        if(videosModel == null){
            videosModel = new VideosModel();
        }

    }

    public void getVideosModel(String uid, String page){
        videosModel.getVideosModel(uid, page, new VideosModel.VideosMessage() {

            @Override
            public void videosSuccess(VideosBean videosBean) {
                try {
                    String code = videosBean.code;
                    if(code.equals("0")){
                        videosView.videosSuccess(videosBean.data);

                    }else{
                        videosView.videosFailure(videosBean.msg);

                    }
                }catch (Exception e){

                }
            }

            @Override
            public void videosFailure(Throwable e) {
                videosView.failure();

            }
        });

    }

}
