package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;

import static com.daiqu.cm.daiqu.fragment.HomeFragment.danmuList;

/**
 * Created by CM on 2017/7/28.
 */

public class AssessmentActivity extends Activity {


    private RatingBar ratingBar;
    private Button sure_btn;
    private Button back_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_activity);

        ratingBar = findViewById(R.id.ratingBar);
        sure_btn = findViewById(R.id.assessment_sure_btn);

        ratingBar.setNumStars(5);
        ratingBar.setMax(5);

        back_btn = findViewById(R.id.order_info_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoMain();
            }
        });

        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentActivity.this, AssSuccessActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            GoMain();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void GoMain(){
        Intent intent = new Intent(AssessmentActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}
