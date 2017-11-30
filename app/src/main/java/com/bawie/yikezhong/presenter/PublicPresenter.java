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

    public void getPublishJokeData(String uid, String content, String token){
        publicModel.getPublicData(uid, content, token, new PublicModel.PublishJokeMessage() {
            @Override
            public void userloginSuccess(PublishBean value) {

                try {
                    String json = value.toString();
                    System.out.println("登录接口的请求信息： = "+json);

                    JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");

                    if(code.equals("0")){
                        publicView.userloginSuccess(value);

                    }else {
                        publicView.userloginFailue(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void userloginFailue(Throwable e) {
                publicView.failure();//彻底失败
            }
        });


    }








}
