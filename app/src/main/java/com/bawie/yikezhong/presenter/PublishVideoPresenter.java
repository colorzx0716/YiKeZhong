package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.PublishVideo;
import com.bawie.yikezhong.model.PublishVideoModel;
import com.bawie.yikezhong.view.PublishVideoView;

import java.io.File;

/**
 * Created by 张肖肖 on 2017/12/6.
 */

public class PublishVideoPresenter extends BasePresenter implements PublishVideoModel.GetPublishVideoMessage {

    private PublishVideoModel publishVideoModel;
    private PublishVideoView publishVideoView;

    public PublishVideoPresenter(PublishVideoView publishVideoView) {
        super(publishVideoView);
        if(publishVideoModel == null){
            publishVideoModel = new PublishVideoModel();
        }
        publishVideoModel.setGetPublishVideoMessage(this);
    }


    public void getPublishVideoData(String uid, File videoFile, File coverFile, String videoDesc, String latitude, String longitude){

        publishVideoModel.getPublishVideoData(uid,videoFile,coverFile,videoDesc,latitude,longitude);

    }


    @Override
    public void getPublicVideoSuccess(PublishVideo publishVideo) {
        publishVideoView.getPublicVideoSuccess(publishVideo);
    }

    @Override
    public void getPublishVideoFailure(String msg) {
        publishVideoView.failure();
    }
}
