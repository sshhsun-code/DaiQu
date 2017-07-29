package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daiqu.cm.daiqu.MainActivity;
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

    private Handler mcheckHandler;

    private HandlerThread checkThread;

    private boolean checkDone = false;

    private static int index = 1;

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
                showDialog();
                Toast.makeText(OrderInfoActivity.this,"点击了。，，，，",Toast.LENGTH_SHORT).show();
            }
        });

        initBackThread();

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
                        checkDone = true;
                        break;
                    case Constast.RECEIVING:
                        text1.setText(("请确收货"));
                        break;
                }
            }
        };
    }

    private void initBackThread() {
        checkThread = new HandlerThread("check-status");
        checkThread.start();

        mcheckHandler = new Handler(checkThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkStatus();
                if (!checkDone) {
                    mcheckHandler.sendEmptyMessageDelayed(0,3000);
                }
            }
        };
    }

    private void checkStatus() {
        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mcheckHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        checkDone = true;
        mcheckHandler.removeMessages(0);
    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(OrderInfoActivity.this, MainActivity.class);
//        startActivity(intent);
        this.finish();
    }

    private void getData(){
        if (checkDone) {
        return;
        }
        Log.e("OrderInfoActivity","第"+(index++)+"次请求数据。");
        String phone = GlobalPref.getInstance(OrderInfoActivity.this)
                .getString(Constast.LOGIN_PHONE_NUMBER,"");
        String order_serial_num = GlobalPref.getInstance(OrderInfoActivity.this)
                .getString(Constast.NEW_ORDER_NUMBER,"");
        NetAccess.getOrderStatus(phone,order_serial_num,mHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("OrderInfoActivity","-----------------------");
        checkThread.quit();
        checkDone = true;
        mcheckHandler.removeMessages(0);
        index = 1;
    }

    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage(R.string.order_ensrue_message); //设置内容
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Intent intent = new Intent(OrderInfoActivity.this,AssessmentActivity.class);
                startActivity(intent);
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }


}
