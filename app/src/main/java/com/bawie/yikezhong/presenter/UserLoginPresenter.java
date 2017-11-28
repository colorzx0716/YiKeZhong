package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.model.LoginModel;
import com.bawie.yikezhong.view.LoginView;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * 登录接口的Presenter
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

    public void getUserLoginData(String mobile,String password){
        userLoginModel.getUserLoginData(mobile, password, new LoginModel.UserLoginMessage() {
            @Override
            public void userloginSuccess(ResponseBody value) {
                try {
                    String json = value.string().toString();
                    System.out.println("登录接口的请求信息： = "+json);

                    JSONObject jsonObject=new JSONObject(json);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");

                    if(code.equals("0")){
                        userLoginView.userloginSuccess(json);

                    }else {
                        userLoginView.userloginFailue(json);
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
