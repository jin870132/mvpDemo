package com.mvp.king.mymvpdemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mvp.king.mymvpdemo.R;
import com.mvp.king.mymvpdemo.bean.UserInfo;
import com.mvp.king.mymvpdemo.presenter.impl.LoginPresenterImp;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends Activity implements LoginView{

    @InjectView(R.id.et_userName)
    EditText et_userName;
    @InjectView(R.id.et_pwd)
    EditText et_pwd;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.bt_commit)
    Button bt_commit;
    private LoginPresenterImp presenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //view 持有一个p层引用.
        presenterImp = new LoginPresenterImp(this);


        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用方法
                UserInfo userInfo = new UserInfo(et_userName.getText().toString(),et_pwd.getText().toString());
                presenterImp.checkLogin(userInfo);
            }
        });

    }



    @Override
    public void onGetLoginState(boolean isLogin) {
        if(isLogin){
            tv.setText("登录成功");
        }else{
            tv.setText("登录失败");

        }

    }
}
