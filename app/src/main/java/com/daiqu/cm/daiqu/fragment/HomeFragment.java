package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;

/**
 * Created by CM on 2017/7/24.
 */

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private Button package_btn;
    private ImageView topImage;
    private ImageView bottomImage;
    private TextView tips;

    private int screenWidth = 0;
    private int screenHeight = 0;
    private static final int PADDING = 50;
    private int top_border = PADDING;
    private int left_border = PADDING;
    private int right_border = PADDING;
    private int bottom_border = PADDING;
    private boolean isMeasured = false;

    private int height_RecArea = 300;
    private int with_RecArea = 300;

    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.name,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment,container,false);
        package_btn = v.findViewById(R.id.package_btn);
        package_btn.setOnTouchListener(package_logo_TouchListener);

        topImage = v.findViewById(R.id.top_image);
        bottomImage = v.findViewById(R.id.bottom_iamge);
        tips = v.findViewById(R.id.tips);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        right_border = screenWidth - PADDING;
        bottom_border = screenHeight - PADDING - 310;

        ViewTreeObserver observer = topImage.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!isMeasured) {
                    height_RecArea = topImage.getMeasuredHeight();
                    with_RecArea = ((topImage.getMeasuredWidth() * 3))/2;
                    isMeasured = true;
                }
                return true;
            }
        });

        return v;
    }

    private View.OnTouchListener package_logo_TouchListener = new View.OnTouchListener() {
        int lastX, lastY; // 记录移动的最后的位置


        public boolean onTouch(View v, MotionEvent event) {
            // 获取Action
            int ea = event.getAction();
            if (screenHeight == 0 || screenWidth == 0) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                screenWidth = dm.widthPixels;
                screenHeight = dm.heightPixels;
                right_border = screenWidth - PADDING;
                bottom_border = screenHeight - PADDING - 180;
            }

            switch (ea) {
                case MotionEvent.ACTION_DOWN: // 按下
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE: // 移动

                    Log.d(TAG, "Move: " + event.getRawX() + ", " + event.getRawY());

                    // 移动中动态设置位置
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    int left = v.getLeft() + dx;
                    int top = v.getTop() + dy;
                    int right = v.getRight() + dx;
                    int bottom = v.getBottom() + dy;

                    //边界
                    if (left < left_border) {
                        left = left_border;
                        right = left + v.getWidth();
                    }
                    if (right > right_border) {
                        right = right_border;
                        left = right - v.getWidth();
                    }
                    if (top < top_border) {
                        top = top_border;
                        bottom = top + v.getHeight();
                    }
                    if (bottom > bottom_border) {
                        bottom = bottom_border;
                        top = bottom - v.getHeight();
                    }



                    setViewPosition(v, left, top, right, bottom);


                    // 将当前的位置再次设置
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;

                case MotionEvent.ACTION_UP: // 抬起

                    if (lastX <= with_RecArea && lastY <= height_RecArea) {
                        tips.setText("松手即发单");
                    }else if(lastX >= right_border - with_RecArea
                            && lastY >= bottom_border - height_RecArea) {
                        tips.setText("抢单------");
                    }


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


}
