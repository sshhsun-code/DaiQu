package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/24.
 */

public class SplashActivity extends Activity{

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //测试按钮，方便界面跳转
        findViewById(R.id.sign_in_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        });

        findViewById(R.id.sign_in_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
        handler = new Handler(this.getMainLooper());
    }

    @Override
    protected void onStart() {
        super.onStart();
        //测试时由按钮控制跳转，开发完成后删除按钮代码，由此段代码控制跳转。
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            }
//        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
