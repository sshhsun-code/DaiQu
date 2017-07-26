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

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.utils.MessageVerification;
import com.daiqu.cm.daiqu.utils.NetAccess;

/**
 * Created by CM on 2017/7/24.
 *
 * 注册
 */

public class SignUpActivity extends Activity{

    private Button sign_up;
    private Handler mhandler;

    private EditText login_name;
    private EditText login_message;
    private EditText login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initData();
        initView();
        sign_up = (Button) findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MessageVerification.doPost();
                    }
                }).start();
                String name = TextUtils.isEmpty(login_name.getText().toString()) ? "test" : login_name.getText().toString();
                String password = TextUtils.isEmpty(login_password.getText().toString()) ? "123456" : login_password.getText().toString();
                NetAccess.AccessSignUp(name,password,mhandler);
            }
        });
    }

    private void initView() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_message = (EditText) findViewById(R.id.login_message);
        login_password = (EditText) findViewById(R.id.login_password);
    }

    private void initData() {
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constast.NET_SIGNUP_SUCCESS:
                        startActivity(new Intent(SignUpActivity.this, SignUpSuccessActivity.class));
                        break;
                    case Constast.NET_SIGNUP_FAIL:

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
