package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/29.
 */

public class PayActivity extends Activity implements View.OnClickListener {

    private LinearLayout weChat;
    private LinearLayout zhifubao;
    private LinearLayout bank;
    private RadioButton weChat_radio;
    private RadioButton zhifubao_radio;
    private RadioButton bank_radio;

    private Button go;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);

        weChat = findViewById(R.id.weChat_pay);
        zhifubao = findViewById(R.id.zhifubao_pay);
        bank = findViewById(R.id.bank_pay);
        weChat_radio = findViewById(R.id.weChat_radio);
        zhifubao_radio = findViewById(R.id.zhifubao_radio);
        bank_radio = findViewById(R.id.bank_radio);

        go = findViewById(R.id.pay_btn);

        weChat.setOnClickListener(this);
        zhifubao.setOnClickListener(this);
        bank.setOnClickListener(this);
        weChat_radio.setClickable(false);
        weChat_radio.setChecked(true);
        zhifubao_radio.setClickable(false);
        bank_radio.setClickable(false);
        go.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.weChat_pay:
                setChecked(weChat_radio.getId());
                break;
            case R.id.zhifubao_pay:
                setChecked(zhifubao_radio.getId());
                break;
            case R.id.bank_pay:
                setChecked(bank_radio.getId());
                break;
            case R.id.pay_btn:
                finish();
                startActivity(new Intent(PayActivity.this, SendResaultActivity.class));

                break;
        }
    }

    private void setChecked(int id){
        weChat_radio.setChecked(false);
        zhifubao_radio.setChecked(false);
        bank_radio.setChecked(false);
        switch (id){
            case R.id.weChat_radio:
                weChat_radio.setChecked(true);
                break;
            case R.id.zhifubao_radio:
                zhifubao_radio.setChecked(true);
                break;
            case R.id.bank_radio:
                bank_radio.setChecked(true);
                break;
        }
    }
}
