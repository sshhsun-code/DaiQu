package com.daiqu.cm.daiqu;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.daiqu.cm.daiqu.fragment.FaxianFragment;
import com.daiqu.cm.daiqu.fragment.HomeFragment;
import com.daiqu.cm.daiqu.fragment.MyFragment;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.daiqu.cm.daiqu.inter.GoOtherActivity;
import com.daiqu.cm.daiqu.ui.AddInfoActivity;
import com.daiqu.cm.daiqu.ui.SendActivity;


public class MainActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener,GoOtherActivity {

    private static final String TAG = "MainActivity";

    private static boolean isHome = true;


    TextView bottom_bar_my_text;
    TextView bottom_bar_home_text;
    TextView bottom_bar_faxian_text;

    BottomNavigationBar bottomNavigationBar;

    private HomeFragment mHomeFragment;
    private FaxianFragment mFaxianFragment;
    private MyFragment mMyFragment;

    DisplayMetrics dm;
    int screenWidth;
    int screenHeight;

    public static boolean needHome = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

//        package_logo = findViewById(R.id.package_btn);
//        package_logo.setOnTouchListener(package_logo_TouchListener);

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_cat,R.string.home))
                .addItem(new BottomNavigationItem(R.drawable.faxian_cat,R.string.faxian))
                .addItem(new BottomNavigationItem(R.drawable.my_cat,R.string.my))
                .setFirstSelectedPosition(0)
                .setActiveColor(R.color.selectedRed)
                .setBarBackgroundColor(R.color.bottom_bar_white)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
                setDefaultFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
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




    /**
     * set the default fragment
     * the content id should not be same with the parent content id
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        HomeFragment homeFragment = mHomeFragment.newInstance(getString(R.string.home));
        transaction.replace(R.id.main_content, homeFragment).commit();
        needHome = true;
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        switch (position){
            case 0:
                if(mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance(getString(R.string.home));
                }
                Log.d(TAG, "mHomeFragment: " + mHomeFragment);
                beginTransaction.replace(R.id.main_content, mHomeFragment);
                break;
            case 1:
                if(mFaxianFragment == null) {
                    mFaxianFragment = FaxianFragment.newInstance(getString(R.string.faxian));
                }
                Log.d(TAG, "mFaxianFragment: " + mFaxianFragment);
                beginTransaction.replace(R.id.main_content, mFaxianFragment);
                break;
            case 2:
                if(mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance(getString(R.string.my));
                }
                Log.d(TAG, "mMyFragment: " + mMyFragment);
                beginTransaction.replace(R.id.main_content, mMyFragment);
                break;
        }
        beginTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void goOhter(Activity activity) {
        Log.d(TAG, "goOhter: " + activity);
        Intent intent = new Intent();

        intent.setClass(MainActivity.this,activity.getClass());

        startActivity(intent);

    }

    /**
     * 对外提供一个回到MainActivity否展示页的方法
     */
    public static void setFragment(boolean needHome){
        needHome = needHome;
    }


    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // 处理连按退出
            // System.currentTimeMillis()无论何时调用，肯定大于2000
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Log.d(TAG, "按下了back键 onKeyDown()");
                setDefaultFragment();
                Toast.makeText(getApplicationContext(), "再按一次,退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }



}
