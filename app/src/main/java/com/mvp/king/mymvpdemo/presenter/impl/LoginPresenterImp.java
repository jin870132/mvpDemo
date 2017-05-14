package com.mvp.king.mymvpdemo.presenter.impl;

import com.mvp.king.mymvpdemo.bean.UserInfo;
import com.mvp.king.mymvpdemo.presenter.LoginPresenter;
import com.mvp.king.mymvpdemo.view.LoginView;

/**
 * Created by king on 2017/4/26.
 */

public class LoginPresenterImp implements LoginPresenter {
    private LoginView view;

    public LoginPresenterImp(LoginView splashView) {
        this.view = splashView;
    }

    @Override
    public void checkLogin(UserInfo userInfo) {
        if(userInfo.userName.equals("admin")&&userInfo.pwd.equals("123")){
            view.onGetLoginState(true);
        }else{
            view.onGetLoginState(false);
        }
    }
}
