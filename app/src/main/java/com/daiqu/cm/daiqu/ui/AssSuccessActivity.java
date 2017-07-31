package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/28.
 */

public class AssSuccessActivity extends Activity {

    private Button back;
    private TextView backText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_success_activity);

        back = findViewById(R.id.back);
        backText = findViewById(R.id.title_text);

        back.setOnClickListener(backListener);
        backText.setOnClickListener(backListener);
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AssSuccessActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(AssSuccessActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
