package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/28.
 */

public class RobActivity extends Activity {

    private Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rob_activity);
        findViewById(R.id.list_item2).findViewById(R.id.rob_btn).setVisibility(View.GONE);
        findViewById(R.id.list_item2).findViewById(R.id.rob_user).setVisibility(View.VISIBLE);
        back = findViewById(R.id.order_info_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
