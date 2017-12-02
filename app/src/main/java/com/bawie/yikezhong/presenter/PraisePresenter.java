package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.PraiseBean;
import com.bawie.yikezhong.model.PraiseModel;
import com.bawie.yikezhong.view.PraiseView;

/**
 * 段子的
 */

public class PraisePresenter extends BasePresenter{


    private final PraiseView praiseView;
    private PraiseModel praiseModel;

    public PraisePresenter(PraiseView praiseView) {
        super(praiseView);
        this.praiseView = praiseView;
        if(praiseModel == null){
            praiseModel = new PraiseModel();
        }
    }

    public void getPraiseData(String uid,String jid){
        praiseModel.getPraiseData(uid,jid, new PraiseModel.PraiseMessage() {
            @Override
            public void praiseSuccess(PraiseBean praiseBean) {
               try{

                   String code = praiseBean.code;
                   if(code.equals("0")){
                       praiseView.praiseSuccess(praiseBean);
                   }else{
                       praiseView.praiseFailure(praiseBean.msg);
                   }

               }catch (Exception e){

               }
            }

            @Override
            public void praiseFailure(Throwable e) {
                System.out.println("彻底失败" + e);
                praiseView.failure();

            }
        });

    }






}
