package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/24.
 *
 * 注册成功界面
 */

public class SignUpSuccessActivity extends Activity{

    private Button sign_up_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_done);
        sign_up_done = (Button) findViewById(R.id.sign_up_done);
        sign_up_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(DaiQuApplication.getInstance(), MainActivity.class));
            }
        });
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
