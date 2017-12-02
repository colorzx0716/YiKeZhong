package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.PraiseBean;
import com.bawie.yikezhong.utils.HUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张肖肖 on 2017/12/2.
 */

public class PraiseModel {

    public void getPraiseData(String uid, String jid, final PraiseMessage praiseMessage){
        new HUtils.Builder()
                .addConverterFactory()
                .addCallAdapterFactory().build().getMyQusetUtils().getPraise(uid,jid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PraiseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PraiseBean praiseBean) {
                        praiseMessage.praiseSuccess(praiseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        praiseMessage.praiseFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface PraiseMessage{
        void praiseSuccess(PraiseBean praiseBean);
        void praiseFailure(Throwable e);

    }

}
