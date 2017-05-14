package com.mvp.king.mymvpdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mvp.king.mymvpdemo.R;
import com.mvp.king.mymvpdemo.presenter.SplashPresenter;
import com.mvp.king.mymvpdemo.presenter.impl.SplashPresenterImp;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashActivity extends Activity implements SplashView{

    @InjectView(R.id.tv)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        //view 持有一个p层引用.
        SplashPresenterImp presenterImp = new SplashPresenterImp(this);
        //调用方法
        presenterImp.checkLogin();

    }



    @Override
    public void onGetLoginState(boolean isLogin) {
        if(isLogin){
            tv.setText("登录成功");
            SplashActivity.this.startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        }else{
            tv.setText("登录失败");
        }
    }
}
