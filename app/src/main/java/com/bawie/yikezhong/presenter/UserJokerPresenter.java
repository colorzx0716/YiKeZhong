package com.bawie.yikezhong.presenter;

import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.bean.UserJoker;
import com.bawie.yikezhong.model.JokesModel;
import com.bawie.yikezhong.view.UserJokerView;

/**
 * Created by 张肖肖 on 2017/11/28.
 */

public class UserJokerPresenter extends BasePresenter{


    private UserJokerView userJokerView;
    private JokesModel jokesModel;

    public UserJokerPresenter(UserJokerView userJokerView) {
        super(userJokerView);
        this.userJokerView = userJokerView;

        if(jokesModel == null){
            jokesModel = new JokesModel();
        }

    }


    public void getJokesData(String page,String token){
        jokesModel.getUserJokesData(page, token, new JokesModel.UserJokesMessage(){

            @Override
            public void getJokesSuccess(UserJoker value) {
                String code = value.code;
                if(code.equals("0")){
                    userJokerView.getJokesSuccess(value);
                }else {
                    userJokerView.getJokesFailure(value.msg);
                }
            }

            @Override
            public void getJokesFailure(Throwable e) {
                userJokerView.failure();//成功
            }
        });
    }






    }








