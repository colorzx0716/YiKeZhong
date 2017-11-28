package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.UserJoker;
import com.bawie.yikezhong.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *段子页面的model
 */

public class JokesModel {

    public void getUserJokesData(String page, String token, final UserJokesMessage userJokesMessage){

        new HttpUtils.Builder()
                .addCallAdapterFactory()
                .addConverterFactory()
                .build()
                .getMyQusetUtils()
                .getUserJokes(page,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserJoker>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserJoker userJoker) {

                        userJokesMessage.getJokesSuccess(userJoker);

                    }

                    @Override
                    public void onError(Throwable e) {

                        userJokesMessage.getJokesFailure(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //接口
    public interface UserJokesMessage{
        void getJokesSuccess(UserJoker userJoker);
        void getJokesFailure(Throwable e);

    }


}
