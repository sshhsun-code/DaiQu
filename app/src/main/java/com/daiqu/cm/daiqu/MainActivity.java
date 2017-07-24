package com.daiqu.cm.daiqu;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daiqu.cm.daiqu.fragment.FaxianFragment;
import com.daiqu.cm.daiqu.fragment.HomeFragment;
import com.daiqu.cm.daiqu.fragment.MyFragment;


public class MainActivity extends Activity{

    private static final String TAG = "MainActivity";

    private static boolean isHome = true;

    Button package_logo;
    LinearLayout bottom_bar_my;
    LinearLayout bottom_bar_home;
    LinearLayout bottom_bar_faxian;

    TextView bottom_bar_my_text;
    TextView bottom_bar_home_text;
    TextView bottom_bar_faxian_text;

    private HomeFragment mHomeFragment;
    private FaxianFragment mFaxianFragment;
    private MyFragment mMyFragment;

    DisplayMetrics dm;
    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

//        package_logo = findViewById(R.id.package_btn);
//        package_logo.setOnTouchListener(package_logo_TouchListener);

        bottom_bar_my = findViewById(R.id.bottom_bar_my);
        bottom_bar_home = findViewById(R.id.bottom_bar_home);
        bottom_bar_faxian = findViewById(R.id.bottom_bar_faxian);
        bottom_bar_my.setOnClickListener(bottom_bar_clickListener);
        bottom_bar_home.setOnClickListener(bottom_bar_clickListener);
        bottom_bar_faxian.setOnClickListener(bottom_bar_clickListener);

        bottom_bar_my_text = findViewById(R.id.bottom_bar_my_text);
        bottom_bar_home_text = findViewById(R.id.bottom_bar_home_text);
        bottom_bar_faxian_text = findViewById(R.id.bottom_bar_faxian_text);


    }

    private View.OnClickListener bottom_bar_clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            enlarge_selected(view.getId());
            switch (view.getId()){
                case R.id.bottom_bar_my:
                    setBottomTextColor(bottom_bar_my_text);

                    if(mMyFragment == null) {
                        mMyFragment = MyFragment.newInstance(getString(R.string.my));
                    }
                    Log.d(TAG, "mMyFragment: " + mMyFragment);
                    beginTransaction.replace(R.id.main_content, mMyFragment);
                    break;
                case R.id.bottom_bar_home:
                    setBottomTextColor(bottom_bar_home_text);

                    if(mHomeFragment == null) {
                        mHomeFragment = HomeFragment.newInstance(getString(R.string.home));
                    }
                    Log.d(TAG, "mHomeFragment: " + mHomeFragment);
                    beginTransaction.replace(R.id.main_content, mHomeFragment);
                    break;
                case R.id.bottom_bar_faxian:
                    setBottomTextColor(bottom_bar_faxian_text);
                    if(mFaxianFragment == null) {
                        mFaxianFragment = FaxianFragment.newInstance(getString(R.string.faxian));
                    }
                    Log.d(TAG, "mFaxianFragment: " + mFaxianFragment);
                    beginTransaction.replace(R.id.main_content, mFaxianFragment);
                    break;
            }
            beginTransaction.commit();
        }
    };

    /**
     * 放大选中的view
     * 选中择将margin变为0,达到放大的视觉效果
     * 方法略低效，待优化
     * @param id
     */
    private void enlarge_selected(int id){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                40,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        lp.setMargins(2, 2, 2, 2);
        bottom_bar_home.setLayoutParams(lp);
        bottom_bar_faxian.setLayoutParams(lp);
        bottom_bar_my.setLayoutParams(lp);
        //设置放大的params
        LinearLayout.LayoutParams lpBig = new LinearLayout.LayoutParams(
                40,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        lpBig.setMargins(0, 0, 0, 0);
        switch (id) {
            case R.id.bottom_bar_my:
                bottom_bar_my.setLayoutParams(lpBig);
                break;
            case R.id.bottom_bar_home:
                bottom_bar_home.setLayoutParams(lpBig);
                break;
            case R.id.bottom_bar_faxian:
                bottom_bar_faxian.setLayoutParams(lpBig);
                break;
        }
    }

    private int min(int a, int b, int c){
        int m = a;
        if (m > b) m = b;
        if (m > c) m = c;
        return m;
    }

    /**
     * 设置textview字体颜色
     * @param textView
     */
    private void setBottomTextColor(TextView textView){
        clearAllTextColor();
        textView.setTextColor(this.getResources().getColor(R.color.colorAccent));
    }

    /**
     * 所有底部导航栏textview字体颜色恢复默认
     */
    private void clearAllTextColor(){
        bottom_bar_my_text.setTextColor(this.getResources().getColor(R.color.Black));
        bottom_bar_home_text.setTextColor(this.getResources().getColor(R.color.Black));
        bottom_bar_faxian_text.setTextColor(this.getResources().getColor(R.color.Black));
    }

    private View.OnTouchListener package_logo_TouchListener = new View.OnTouchListener() {
        int lastX, lastY; // 记录移动的最后的位置


        public boolean onTouch(View v, MotionEvent event) {
            // 获取Action
            int ea = event.getAction();
            switch (ea) {
                case MotionEvent.ACTION_DOWN: // 按下
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    screenWidth = v.getWidth();
                    screenHeight = v.getHeight();
//                    Toast.makeText(MainActivity.this, "ACTION_DOWN：" + lastX
//                            + "," + lastY, Toast.LENGTH_SHORT).show();
                    break;

                case MotionEvent.ACTION_MOVE: // 移动

                    Log.d(TAG, "Move: " + event.getRawX() + ", " + event.getRawY());


                    // 移动中动态设置位置
                    int dy = (int) event.getRawY() - lastY;
                    int left = v.getLeft();
                    int top = v.getTop() + dy;
                    int right = v.getRight();
                    int bottom = v.getBottom() + dy;
//                    if (top < 0) {
//                        top = 0;
//                        bottom = top + v.getHeight();
//                    }
//                    if (bottom > screenHeight) {
//                        bottom = screenHeight;
//                        top = bottom - v.getHeight();
//                    }

                    setViewPosition(v, left, top, right, bottom);


                    // 将当前的位置再次设置
                    lastY = (int) event.getRawY();
                    break;

                case MotionEvent.ACTION_UP: // 抬起
                    break;
            }
            return false;
        }
    };

    /**
     * 设置view的位置
     * @param v
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    private void setViewPosition(View v, int left, int top, int right, int bottom){
        v.layout(left,top,right,bottom);
    }

    /**
     * set the default fagment
     * the content id should not be same with the parent content id
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        HomeFragment homeFragment = mHomeFragment.newInstance(getString(R.string.home));
        transaction.replace(R.id.main_content, homeFragment).commit();

    }

}
