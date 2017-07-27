package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;


/**
 * Created by CM on 2017/7/27.
 */

public class SendResaultActivity extends Activity{

    private Button back;
    private TextView back_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_resault_activity);

        back = findViewById(R.id.send_back);
        back_text = findViewById(R.id.send_title_text);

        back.setOnClickListener(clickListener);
        back_text.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SendResaultActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}
