package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.PublishBean;
import com.bawie.yikezhong.model.PublicModel;
import com.bawie.yikezhong.view.PublicView;

import org.json.JSONObject;

/**
 * 发表段子的Presenter
 */

public class PublicPresenter extends BasePresenter{


    private PublicView publicView;
    private PublicModel publicModel;

    public PublicPresenter(PublicView publicView) {
        super(publicView);
        this.publicView = publicView;
        if(publicModel == null){
            publicModel = new PublicModel();
        }
    }

    public void getPublicData(String uid, String content, String token){
        publicModel.getPublicData(uid, content, token, new PublicModel.UserPublicMessage() {
            @Override
            public void getPublicSuccess(PublishBean value) {
                try {
                 String json = value.toString();

                    JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");

                    if(code.equals("0")){
                        publicView.getPublicSuccess(msg);
                        System.out.println(msg);

                    }else {
                        publicView.getPublicFailure(msg);
                        System.out.println(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void getPublicFailure(Throwable e) {
                publicView.failure();
            }
        });

    }




}
