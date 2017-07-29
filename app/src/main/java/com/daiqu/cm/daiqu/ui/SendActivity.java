package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.daiqu.cm.daiqu.DaiQuApplication;
import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.bean.OrderInfo;
import com.daiqu.cm.daiqu.fragment.SendFragment;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.daiqu.cm.daiqu.utils.NetAccess;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CM on 2017/7/27.
 */

public class SendActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "SendActivity";

    private Button backButton;
    private EditText name_edit;
    private EditText phone_edit;
    private Spinner company_spinner;
    private Button pick_uo_address_btn;
    private EditText pick_up_address;
    private EditText pick_up_code;
    private EditText arrive_address;
    private Button arrive_address_btn;
    private TextView arrive_time_begin;
    private TextView arrive_time_end;
    private Button confirm_btn;
    private RadioGroup radioGroup;
    private RadioButton price_small_rb;
    private RadioButton price_big_rb;
    private TimePicker timePicker;

    private Handler mhandler;
    private long millisTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.send_fragment);

        backButton = findViewById(R.id.send_back);
        name_edit = findViewById(R.id.send_name_edit);
        phone_edit = findViewById(R.id.send_phone_editText);
        company_spinner = findViewById(R.id.send_company_spinner);
        pick_up_address = findViewById(R.id.send_pick_up_address_edit);
        pick_uo_address_btn = findViewById(R.id.send_pick_up_btn);
        pick_up_code = findViewById(R.id.send_code_editText);
        arrive_address = findViewById(R.id.send_arrive_address_edit);
        arrive_address_btn = findViewById(R.id.send_arrive_address_btn);
        arrive_time_begin = findViewById(R.id.send_arrive_time_edit_begin);
        arrive_time_end = findViewById(R.id.send_arrive_time_edit_end);
        confirm_btn = findViewById(R.id.send_confirm_btn);
        radioGroup = findViewById(R.id.send_price_radioGroup);

        radioGroup.check(R.id.send_price_radio_small);

        backButton.setOnClickListener(this);
        pick_up_address.setOnClickListener(this);
        arrive_address.setOnClickListener(this);
        arrive_time_begin.setOnClickListener(this);
        arrive_time_end.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);

        initData();
    }

    private void initData() {
        Log.d(TAG, "initData: ");

        GlobalPref global = GlobalPref.getInstance(this);
        if (global.getBoolean(Constast.HAS_ADDED, false)) {
            name_edit.setText(global.getString(Constast.NAME, ""));
            phone_edit.setText(global.getString(Constast.PHONE, ""));
            arrive_address.setText(global.getString(Constast.COMMON_ADDRESS, ""));
        } else {

            Intent intent = new Intent(SendActivity.this, AddInfoActivity.class);
            startActivity(intent);

        }

        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {

                }
            }
        };

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.send_pick_up_address_edit:

                break;
            case R.id.send_pick_up_btn:

                break;
            case R.id.send_arrive_address_btn:

                break;
            case R.id.send_arrive_address_edit:

                break;
            case R.id.send_back:
                finish();
//                Intent intent = new Intent();
//                intent.setClass(SendActivity.this, MainActivity.class);
//                startActivity(intent);
//                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
//                beginTransaction.replace(R.id.main_content,
//                        HomeFragment.newInstance(getString(R.string.home)));
//                beginTransaction.commit();
                break;
            case R.id.send_confirm_btn:
                showDialog();
                break;
            case R.id.send_arrive_time_edit_begin:
                DateChooseWheelViewDialog startDateChooseDialog =
                        new DateChooseWheelViewDialog(SendActivity.this,
                                new DateChooseWheelViewDialog.DateChooseInterface() {
                                    @Override
                                    public void getDateTime(String time) {
                                        arrive_time_begin.setText(time);
                                    }
                                });
                startDateChooseDialog.setDateDialogTitle("最早送达");
                startDateChooseDialog.showDateChooseDialog();
                break;
            case R.id.send_arrive_time_edit_end:
                DateChooseWheelViewDialog endDateChooseDialog =
                        new DateChooseWheelViewDialog(SendActivity.this,
                                new DateChooseWheelViewDialog.DateChooseInterface() {
                                    @Override
                                    public void getDateTime(String time) {
                                        arrive_time_end.setText(time);
                                    }
                                });
                endDateChooseDialog.setDateDialogTitle("最晚送达");
                endDateChooseDialog.showDateChooseDialog();
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SendActivity.this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage(R.string.confirm_tips); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postToServer();
                dialog.dismiss(); //关闭dialog
                Intent intent = new Intent(SendActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    private Date getDate() {
        Date curDate = new Date(millisTime);//获取当前时间
        return curDate;
    }

    private String getMillisTime() {
        millisTime = System.currentTimeMillis();
        GlobalPref.getInstance(SendActivity.this).putString(Constast.NEW_ORDER_NUMBER, millisTime + "");
        return millisTime + "";
    }

    private int getPrice() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.send_price_radio_small) {
            return 3;
        } else {
            return 5;
        }
    }

    private String getPickTime() {
        return arrive_time_begin.getText().toString() + "-" + arrive_time_end.getText().toString();
    }

    private void postToServer() {
        final OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_name(name_edit.getText().toString());
        orderInfo.setOrder_phone(phone_edit.getText().toString());
        orderInfo.setOrder_company_type(company_spinner.getSelectedItem().toString());
        orderInfo.setOrder_home_address(arrive_address.getText().toString());
        orderInfo.setOrder_pick_num(pick_up_code.getText().toString());
        orderInfo.setOrder_serial_num(getMillisTime());
        orderInfo.setOrder_price(getPrice());
        orderInfo.setOrder_pick_time(getPickTime());
        orderInfo.setOrder_pick_address(pick_up_address.getText().toString());
        orderInfo.setUserphone(GlobalPref.getInstance(SendActivity.this)
                .getString(Constast.LOGIN_PHONE_NUMBER, phone_edit.getText().toString()));
        Gson gson = new Gson();
        String info = gson.toJson(orderInfo);
        NetAccess.upOrderInfo(info, mhandler);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(TAG, "按下了back键 onKeyDown()");
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
