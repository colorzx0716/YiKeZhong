package com.bawie.yikezhong.model;

import com.bawie.yikezhong.bean.HotVideosBean;
import com.bawie.yikezhong.utils.HUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 热门视频的model
 */

public class HotVideosModel {

    public void getHotVideosData(String page){

        new HUtils.Builder()
                .addConverterFactory()
                .addCallAdapterFactory()
                .build().getMyQusetUtils()
                .getHotVideos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<HotVideosBean>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(HotVideosBean videosBean) {

                      if(videosBean.code.equals("0")){
                          getHotVideoMessage.getHotVideoSuccess(videosBean);
                      }else{
                          getHotVideoMessage.getHotVideoFailure(videosBean.msg);
                      }

                  }

                  @Override
                  public void onError(Throwable e) {
                      System.out.println("onError失败 = " + e);
                  }

                  @Override
                  public void onComplete() {

                  }
              });

    }

    private GetHotVideoMessage getHotVideoMessage;

    public void setGetHotVideoMessage(GetHotVideoMessage getHotVideoMessage) {
        this.getHotVideoMessage = getHotVideoMessage;
    }

    public interface GetHotVideoMessage{
        void getHotVideoSuccess(HotVideosBean videosBean);
        void getHotVideoFailure(String msg);
    }

}
