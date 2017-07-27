package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/27.
 */

public class OrderInfoActivity extends Activity implements View.OnClickListener {

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
        imageView1 = findViewById(R.id.list_item4).findViewById(R.id.order_info_iamge);

        text5 = findViewById(R.id.list_item5).findViewById(R.id.order_info_status);
        imageView1 = findViewById(R.id.list_item5).findViewById(R.id.order_info_iamge);

        text6 = findViewById(R.id.list_item6).findViewById(R.id.order_info_status);
        imageView1 = findViewById(R.id.list_item6).findViewById(R.id.order_info_iamge);

        text1.setText("等待接单中");
        text2.setText("已接单");
        text3.setText("请确认收货");
        text4.setText("抢单成功");
        text5.setText("已送达");
        text6.setText("已接单");

        imageView4.setImageResource(R.drawable.order_jie_text);
        imageView5.setImageResource(R.drawable.order_jie_text);
        imageView6.setImageResource(R.drawable.order_jie_text);

        findViewById(R.id.list_item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderInfoActivity.this,"点击了。，，，，",Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public void onClick(View view) {
        this.finish();
    }
}
