package com.mvp.king.mymvpdemo.presenter.impl;

import com.mvp.king.mymvpdemo.presenter.SplashPresenter;
import com.mvp.king.mymvpdemo.view.SplashView;

/**
 * Created by king on 2017/4/26.
 */

public class SplashPresenterImp implements SplashPresenter {
    private SplashView splashView;

    public SplashPresenterImp(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkLogin() {
        splashView.onGetLoginState(true);
    }
}
