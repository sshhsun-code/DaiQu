package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.daiqu.cm.daiqu.utils.NetAccess;

import java.util.regex.Pattern;

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

    private CheckBox checkBox;

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
//                /**
//                 *
//                 */
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                /**
//                 *
//                 */
                String phone =  login_name.getText().toString();
                String password =  login_password.getText().toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    Toast.makeText(DaiQuApplication.getInstance(),"账号密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() != 11 || password.length() < 8 ) {
                    Toast.makeText(DaiQuApplication.getInstance(),"请按规范填写账号密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isMatch(password)) {
                    Toast.makeText(DaiQuApplication.getInstance(),"密码中只能包含数字以及字母",Toast.LENGTH_SHORT).show();
                    return;
                }
                NetAccess.AccessLogin(phone,password,loginhandler);
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

    private boolean isMatch(String string) {
        final String regex = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string).find();
    }

    private void initView() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_password = (EditText) findViewById(R.id.login_password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }

    private void initData() {
        loginhandler =  new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constast.NET_LOGIN_SUCCESS:

                        //将登陆号码保存本地
                        GlobalPref.getInstance(LoginActivity.this)
                                .putString(Constast.LOGIN_PHONE_NUMBER,login_name.getText().toString());
                        GlobalPref.getInstance().putBoolean("REMEBER_ME",checkBox.isChecked());
                        if (checkBox.isChecked()) {
                            GlobalPref.getInstance().putString("LOGIN_PASSWORD",login_password.getText().toString());
                        }
                        Toast.makeText(DaiQuApplication.getInstance(),"真实跳转",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        break;
                    case Constast.NET_LOGIN_FAIL:
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
