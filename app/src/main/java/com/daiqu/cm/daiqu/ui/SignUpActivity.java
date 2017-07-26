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
import android.widget.TextView;
import android.widget.Toast;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
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

    private TextView txt_get_message;

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
                if (TextUtils.isEmpty(login_name.getText().toString()) || login_name.getText().length() != 11) {
                    Toast.makeText(DaiQuApplication.getInstance(),"请确认手机号~",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(login_message.getText().toString()) || !login_message.getText().toString().equals(GlobalPref.getInstance().getVerificationNum())) {
                    Toast.makeText(DaiQuApplication.getInstance(),"验证码有问题，请稍后重试~",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(login_password.getText().toString()) || login_password.getText().length() > 16 || login_password.getText().length() < 8) {
                    Toast.makeText(DaiQuApplication.getInstance(),"密码格式是数字加字母，8~16位哦",Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = TextUtils.isEmpty(login_name.getText().toString()) ? "test" : login_name.getText().toString();
                String password = TextUtils.isEmpty(login_password.getText().toString()) ? "123456" : login_password.getText().toString();
                NetAccess.AccessSignUp(name,password,mhandler);
            }
        });

        txt_get_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MessageVerification.doPost(login_name.getText().toString(),mhandler);
                    }
                }).start();
            }
        });
    }

    private void initView() {
        txt_get_message = (TextView) findViewById(R.id.txt_get_message);
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
                        Toast.makeText(DaiQuApplication.getInstance(),"喵~，注册成功！",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, SignUpSuccessActivity.class));
                        break;
                    case Constast.NET_SIGNUP_FAIL:

                        break;
                    case Constast.GET_VERIFICVATION_NUM:
                        int rescode  = msg.arg1;
                        if (rescode == 200 ) {
                            Toast.makeText(DaiQuApplication.getInstance(),"短信发送成功",Toast.LENGTH_SHORT).show();
                            GlobalPref.getInstance().getVerificationNum();
                        } else {
                            Toast.makeText(DaiQuApplication.getInstance(),"短信未能成功发送",Toast.LENGTH_SHORT).show();
                        }
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
