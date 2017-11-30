package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.AdBean;
import com.bawie.yikezhong.model.AdModel;
import com.bawie.yikezhong.view.AdView;

/**
 *广告的Presenter
 */

public class AdPresenter extends BasePresenter{


    private AdView adView;
    private AdModel adModel;

    public AdPresenter(AdView adView) {
        super(adView);
        this.adView = adView;
        if(adModel == null){
            adModel = new AdModel();
        }
    }

   public void getAdData(){
     adModel.getAdData(new AdModel.AdMessage() {
         @Override
         public void AdSuccess(AdBean adBean) {

             String code = adBean.code;
             String msg = adBean.msg;

             if(code.equals("0")){
                 //成功
                 adView.AdSuccess(adBean);

             }else{
                 //失败
                 adView.AdFailue(msg);

             }
         }

         @Override
         public void AdFailue(Throwable e) {
             adView.failure();
             System.out.println("==失败===");

         }
     });


   }




}
