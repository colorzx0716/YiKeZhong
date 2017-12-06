package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.PublishVideo;
import com.bawie.yikezhong.service.InterfaceApi;
import com.bawie.yikezhong.utils.HUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 张肖肖 on 2017/12/6.
 */

public class PublishVideoModel {


    public void getPublishVideoData(String uid, File videoFile, File coverFile, String videoDesc, String latitude, String longitude){

        InterfaceApi interfaceApi = new HUtils.Builder()
                .addCallAdapterFactory()
                .addConverterFactory()
                .build()
                .getMyQusetUtils();

        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        build.addFormDataPart("uid",uid);
        build.addFormDataPart("videoDesc",videoDesc);
        build.addFormDataPart("latitude",latitude);
        build.addFormDataPart("longitude",longitude);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);
        build.addFormDataPart("videoFile", videoFile.getName(), requestFile);
        RequestBody re = RequestBody.create(MediaType.parse("multipart/form-data"), coverFile);
        build.addFormDataPart("coverFile", coverFile.getName(),re);
        List<MultipartBody.Part> parts = build.build().parts();
        interfaceApi.getPublishVideo(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishVideo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublishVideo publishVideo) {
                        if(publishVideo.code.equals("0")){
                            getPublishVideoMessage.getPublicVideoSuccess(publishVideo);
                        }else {
                            getPublishVideoMessage.getPublishVideoFailure(publishVideo.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private GetPublishVideoMessage getPublishVideoMessage;

    public void setGetPublishVideoMessage(GetPublishVideoMessage getPublishVideoMessage) {
        this.getPublishVideoMessage = getPublishVideoMessage;
    }

    public interface GetPublishVideoMessage{
        void getPublicVideoSuccess(PublishVideo publishVideo);
        void getPublishVideoFailure(String msg);
    }



}
