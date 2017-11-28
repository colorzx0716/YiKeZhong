package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.model.UserInfoModel;
import com.bawie.yikezhong.view.UserInfoView;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by 张肖肖 on 2017/11/28.
 */

public class UserInfoPresenter extends BasePresenter{

    private UserInfoView userInfoView;
    private UserInfoModel userInfoModel;

    public UserInfoPresenter(UserInfoView userInfoView) {
        super(userInfoView);
        this.userInfoView= userInfoView;
        if(userInfoModel == null){
            userInfoModel = new UserInfoModel();
        }
    }

    public void getUserInfoData(String uid, String token){
        userInfoModel.getUserInfoData(uid, token, new UserInfoModel.UserInfoMessage() {
            @Override
            public void userinfoSuccess(ResponseBody value) {
                try {
                    String json = value.string().toString();

                    JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");

                    if(code.equals("0")){
                        userInfoView.userinfoSuccess(json);

                    }else {
                        userInfoView.userinfoFailue(json);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void userinfoFailue(Throwable e) {
                userInfoView.failure();//彻底失败

            }
        });

    }








}
