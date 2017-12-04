package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.VideosBean;
import com.bawie.yikezhong.utils.HUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张肖肖 on 2017/12/4.
 */

public class VideosModel {

    public void getVideosModel(String uid, String page,final VideosMessage videosMessage){

        new HUtils.Builder()
                .addCallAdapterFactory()
                .addConverterFactory()
                .build().getMyQusetUtils().getVideos(uid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideosBean videosBean) {
                        videosMessage.videosSuccess(videosBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("失败onError==== " + e);
                        videosMessage.videosFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface VideosMessage{
        void videosSuccess(VideosBean videosBean);
        void videosFailure(Throwable e);
    }

}
