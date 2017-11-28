package com.bawie.yikezhong.model;

import com.bawie.yikezhong.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 张肖肖 on 2017/11/28.
 */

public class UserInfoModel {

    public void getUserInfoData(String uid, String token, final UserInfoMessage userInfoMessage){
        new HttpUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getMyQusetUtils().getUserInfo(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        //成功
                        userInfoMessage.userinfoSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //失败
                        userInfoMessage.userinfoFailue(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //接口
    public interface UserInfoMessage{
        void userinfoSuccess(ResponseBody value);
        void userinfoFailue(Throwable e);
    }



}
