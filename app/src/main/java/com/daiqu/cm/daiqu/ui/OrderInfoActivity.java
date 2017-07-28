package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.daiqu.cm.daiqu.utils.NetAccess;

/**
 * Created by CM on 2017/7/27.
 */

public class OrderInfoActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "OrderInfoActivity";

    private Button back;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;

    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_info_activity);
        back = findViewById(R.id.order_info_back);
        back.setOnClickListener(this);

        text1 = findViewById(R.id.list_item1).findViewById(R.id.order_info_status);
        imageView1 = findViewById(R.id.list_item1).findViewById(R.id.order_info_iamge);

        text2 = findViewById(R.id.list_item2).findViewById(R.id.order_info_status);
        imageView2 = findViewById(R.id.list_item2).findViewById(R.id.order_info_iamge);

        text3 = findViewById(R.id.list_item3).findViewById(R.id.order_info_status);
        imageView3 = findViewById(R.id.list_item3).findViewById(R.id.order_info_iamge);

        text4 = findViewById(R.id.list_item4).findViewById(R.id.order_info_status);
        imageView4 = findViewById(R.id.list_item4).findViewById(R.id.order_info_iamge);

        text5 = findViewById(R.id.list_item5).findViewById(R.id.order_info_status);
        imageView5 = findViewById(R.id.list_item5).findViewById(R.id.order_info_iamge);

        text6 = findViewById(R.id.list_item6).findViewById(R.id.order_info_status);
        imageView6 = findViewById(R.id.list_item6).findViewById(R.id.order_info_iamge);

        text1.setText("等待接单中");
        text2.setText("已接单");
        text3.setText("请确认收货");
        text4.setText("抢单成功");
        text5.setText("已送达");
        text6.setText("已接单");

        imageView4.setImageResource(R.drawable.order_jie_text);
        imageView5.setImageResource(R.drawable.order_jie_text);
        imageView6.setImageResource(R.drawable.order_jie_text);

        findViewById(R.id.list_item1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderInfoActivity.this,AssessmentActivity.class);
                startActivity(intent);
                Toast.makeText(OrderInfoActivity.this,"点击了。，，，，",Toast.LENGTH_SHORT).show();
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: " + msg.what);
                switch (msg.what) {

                    case Constast.WAITTING:
                        text1.setText("等待接单中");
                        break;
                    case Constast.PICKED:
                        text1.setText("已接单");
                        break;
                    case Constast.DONE:
                        text1.setText("已完成");
                        break;
                    case Constast.RECEIVING:
                        text1.setText(("请确收货"));
                        break;
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        getData();
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }

    private void getData(){
        String phone = GlobalPref.getInstance(OrderInfoActivity.this)
                .getString(Constast.LOGIN_PHONE_NUMBER,"");
        String order_serial_num = GlobalPref.getInstance(OrderInfoActivity.this)
                .getString(Constast.NEW_ORDER_NUMBER,"");
        NetAccess.getOrderStatus(phone,order_serial_num,mHandler);
    }
}
