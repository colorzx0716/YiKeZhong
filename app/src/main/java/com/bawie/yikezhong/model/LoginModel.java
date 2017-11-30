package com.bawie.yikezhong.model;

import com.bawie.yikezhong.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 登录的model
 */
public class LoginModel {

    public void getUserLoginData(String mobile, String password, final UserLoginMessage userLoginMessage){
        new HttpUtils.Builder().
                addConverterFactory()
                .addCallAdapterFactory().build().getMyQusetUtils().getUserLogin(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }
                    @Override
                    public void onNext(ResponseBody value) {
                        userLoginMessage.userloginSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        userLoginMessage.userloginFailue(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface UserLoginMessage{
        void userloginSuccess(ResponseBody value);
        void userloginFailue(Throwable e);
    }

}
