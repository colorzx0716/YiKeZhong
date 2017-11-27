package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.model.LoginModel;
import com.bawie.yikezhong.view.LoginView;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by 张肖肖 on 2017/11/27.
 */

public class UserLoginPresenter extends BasePresenter {
    private LoginModel userLoginModel;
    private LoginView userLoginView;

    public UserLoginPresenter(LoginView userLoginView) {
        super(userLoginView);
        this.userLoginView = userLoginView;
        if(userLoginModel == null){
            userLoginModel = new LoginModel();
        }
    }

    public void getUserLoginData(String mobile,String password,String taken){
        userLoginModel.getUserLoginData(mobile, password, taken, new LoginModel.UserLoginMessage() {
            @Override
            public void userloginSuccess(ResponseBody value) {
                try {
                    String json = value.string().toString();
                    JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");
                    if(code.equals("0")){
                        userLoginView.userloginSuccess(value.string().toString());
                    }else {
                        userLoginView.userloginFailue(value.string().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void userloginFailue(Throwable e) {
                userLoginView.failure();
            }
        });
    }
}
