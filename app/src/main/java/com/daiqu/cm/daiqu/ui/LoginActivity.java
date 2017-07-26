package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.utils.NetAccess;

/**
 * Created by CM on 2017/7/24.
 *
 * 登录
 */

public class LoginActivity extends Activity{

    private Button sign_in;
    private Button sign_up;

    private EditText login_name;
    private EditText login_password;

    private Handler loginhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        initView();
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = TextUtils.isEmpty(login_name.getText().toString()) ? "admin" : login_name.getText().toString();
                String password = TextUtils.isEmpty(login_password.getText().toString()) ? "admin" : login_password.getText().toString();
                if (phone.equals("admin") || password.equals("admin")) {  //方便测试，完成后去掉此选项，只走服务器访问来登录
                    loginhandler.sendEmptyMessage(Constast.NET_LOGIN_FAIL);
                } else {
                    NetAccess.AccessLogin(phone,password,loginhandler);
                }
            }
        });
        sign_up = (Button) findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void initView() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_password = (EditText) findViewById(R.id.login_password);
    }

    private void initData() {
        loginhandler =  new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constast.NET_LOGIN_SUCCESS:
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(DaiQuApplication.getInstance(),"真实跳转",Toast.LENGTH_SHORT).show();
                        break;
                    case Constast.NET_LOGIN_FAIL:
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(DaiQuApplication.getInstance(),"模拟跳转",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
