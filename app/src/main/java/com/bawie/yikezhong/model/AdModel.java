package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.AdBean;
import com.bawie.yikezhong.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 广告的Model
 */

public class AdModel {

    public void getAdData(final AdMessage adMessage){
        new HttpUtils.Builder().
                addConverterFactory()
                .addCallAdapterFactory().build()
                .getMyQusetUtils()
                .getAd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<AdBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(AdBean adBean) {
                       adMessage.AdSuccess(adBean);

                   }

                   @Override
                   public void onError(Throwable e) {
                       adMessage.AdFailue(e);
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }

    //接口
    public interface AdMessage{
        void AdSuccess(AdBean adBean);
        void AdFailue(Throwable e);
    }

}
