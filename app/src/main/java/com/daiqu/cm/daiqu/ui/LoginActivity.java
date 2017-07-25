package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.utils.NetAccess;

import org.apache.commons.io.IOUtils;

/**
 * Created by CM on 2017/7/24.
 *
 * 登录
 */

public class LoginActivity extends Activity{

    private Button sign_in;
    private Button sign_up;

    private Handler loginhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetAccess.AccessLogin("admin","1234",loginhandler);
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

    private void initData() {
        loginhandler =  new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constast.NET_SUCCESS:
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(DaiQuApplication.getInstance(),"真实跳转",Toast.LENGTH_SHORT).show();
                        break;
                    case Constast.NET_FAIL:
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
