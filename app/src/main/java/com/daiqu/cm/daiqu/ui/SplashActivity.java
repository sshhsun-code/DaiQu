package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.daiqu.cm.daiqu.utils.NetAccess;

/**
 * Created by CM on 2017/7/24.
 */

public class SplashActivity extends Activity{

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler(this.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constast.NET_LOGIN_SUCCESS:
                        finish();
                        startActivity(new Intent(DaiQuApplication.getInstance(), MainActivity.class));
                        break;
                    case Constast.NET_LOGIN_FAIL:
                        finish();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        break;
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (GlobalPref.getInstance().getBoolean(Constast.REMEBER_ME,false)) {
            String phone = GlobalPref.getInstance().getString(Constast.LOGIN_PHONE_NUMBER,"");
            String password = GlobalPref.getInstance().getString(Constast.LOGIN_PASSWORD,"");
            if (phone.equals("") || password.equals("")) {
                finish();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            NetAccess.AccessLogin(phone,password,handler);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                }
            },3000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
