package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.PublishBean;
import com.bawie.yikezhong.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 发表段子的Model
 */

public class PublicModel {

    public void getPublicData(String uid, String content, String token, final PublishJokeMessage publishJokeMessage){
        new HttpUtils.Builder()
                .addCallAdapterFactory()
                .addConverterFactory()
                .build().getMyQusetUtils().getPublishJoke(uid,content,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublishBean publishBean) {
                        publishJokeMessage.userloginSuccess(publishBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        publishJokeMessage.userloginFailue(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface PublishJokeMessage{
        void userloginSuccess(PublishBean publishBean);
        void userloginFailue(Throwable e);
    }

}
