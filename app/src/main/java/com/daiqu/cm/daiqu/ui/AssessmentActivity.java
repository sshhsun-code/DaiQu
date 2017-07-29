package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/28.
 */

public class AssessmentActivity extends Activity {


    private RatingBar ratingBar;
    private Button sure_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_activity);

        ratingBar = findViewById(R.id.ratingBar);
        sure_btn = findViewById(R.id.assessment_sure_btn);

        ratingBar.setNumStars(5);
        ratingBar.setMax(5);

        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentActivity.this, AssSuccessActivity.class);
                startActivity(intent);
            }
        });

    }
}
