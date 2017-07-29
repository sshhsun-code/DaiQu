package com.daiqu.cm.daiqu.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.daiqu.cm.daiqu.inter.GoOtherActivity;
import com.daiqu.cm.daiqu.ui.AssSuccessActivity;
import com.daiqu.cm.daiqu.ui.RobActivity;
import com.daiqu.cm.daiqu.ui.SendActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by CM on 2017/7/24.
 */

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private Button package_btn;
    private ImageView topImage;
    private ImageView bottomImage;
    private TextView tips;
    private DanmakuView danmakuView;

    private RelativeLayout guide;

    //弹幕
    private DanmakuContext danmakuContext;
    private boolean showDanmaku;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    //屏幕边界值
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
    private int beginX, beginY;

    private SendFragment mSendFragment;

    public GoOtherActivity goOtherActivity;


    public static ArrayList<String> danmuList = new ArrayList<String>();

    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.NAME,s);
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

        guide = v.findViewById(R.id.guide);
        guide.setAlpha(0.8f);

        if (GlobalPref.getInstance().getBoolean("IS_FIRST_HOME",true)){
            guide.setVisibility(View.VISIBLE);
            guide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guide.setVisibility(View.GONE);
                    GlobalPref.getInstance().putBoolean("IS_FIRST_HOME",false);
                }
            });
        }

        initDanmuList();

        danmakuView = v.findViewById(R.id.danmaku_view);
        danmakuView.enableDanmakuDrawingCache(true);

        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });

        //设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);

        danmakuContext = DanmakuContext.create();
        danmakuContext.setScrollSpeedFactor(1.2f)
                .preventOverlapping(overlappingEnablePair);//设置弹幕滚动速度系数,只对滚动弹幕有效

        danmakuView.prepare(parser, danmakuContext);


        //屏幕尺寸测量，确定边界值、识别区域
        DisplayMetrics dm = getResources().getDisplayMetrics();
            screenWidth = dm.widthPixels;
            screenHeight = dm.heightPixels;
            right_border = screenWidth - PADDING;
            bottom_border = screenHeight - PADDING - 310;
            beginX = screenWidth / 2;
            beginY = screenHeight / 2;
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

    private void initDanmuList(){
        danmuList.add("陈喵喵~刚刚发送了一个订单，价值一包小鱼干，快去抢单吧");
        danmuList.add("王喵喵~刚刚抢到了一个订单，又可以赚小鱼干了，好开心 (*^__^*)");
        danmuList.add("孙喵喵~刚刚送达了一个订单，收到了一大包小鱼干…(⊙_⊙;)… ○");
        danmuList.add("解喵喵的订单有人接单了，终于可以懒懒的躺倒摇摇床上等快递了O__O");
        danmuList.add("钟喵喵的订单开始配送了，准备好小鱼干迎接★~★ ");
        danmuList.add("赵喵喵~刚刚发送了一个订单，价值一包小鱼干，快去抢单吧");
        danmuList.add("孙喵喵~刚刚抢到了一个订单，又可以赚小鱼干了，好开心 (*^__^*)");
        danmuList.add("曹喵喵~刚刚送达了一个订单，收到了一大包小鱼干…(⊙_⊙;)… ○");
        danmuList.add("李喵喵的订单有人接单了，终于可以懒懒的躺倒摇摇床上等快递了O__O");
        danmuList.add("欧喵喵的订单开始配送了，准备好小鱼干迎接★~★ ");
        danmuList.add("管喵喵~刚刚发送了一个订单，价值一包小鱼干，快去抢单吧");
        danmuList.add("梁喵喵~刚刚抢到了一个订单，又可以赚小鱼干了，好开心 (*^__^*)");
        danmuList.add("魏喵喵~刚刚送达了一个订单，收到了一大包小鱼干…(⊙_⊙;)… ○");
        danmuList.add("乌喵喵的订单有人接单了，终于可以懒懒的躺倒摇摇床上等快递了O__O");
        danmuList.add("徐喵喵的订单开始配送了，准备好小鱼干迎接★~★ ");

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
                        goOtherActivity.goOhter(new SendActivity());
//                        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
//                        tips.setText("松手即发单");
//                        if(mSendFragment == null) {
//                            mSendFragment = SendFragment.newInstance();
//                        }
//                        Log.d(TAG, "mMyFragment: " + mSendFragment);
//                        beginTransaction.replace(R.id.main_content, mSendFragment);
//                        beginTransaction.commit();
                        break;
                    }else if(lastX >= right_border - with_RecArea
                            && lastY >= bottom_border - height_RecArea) {
                        tips.setText("抢单------");

                        showDialog();
                    }else {
                        left = beginX-(v.getWidth()/2);
                        setViewPosition(v,left, beginY - v.getHeight(),left+v.getWidth(),beginY);
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

    /**
     * 向弹幕View中添加一条弹幕
     * @param content
     *          弹幕的具体内容
     * @param  withBorder
     *          弹幕是否有边框
     * @param  color
     *          弹幕颜色，-1则为默认
     */
    private void addDanmaku(String content, boolean withBorder, int color) {
        if (!showDanmaku){
            return;
        }
        if (color <1){
            color = Color.GRAY;
        }
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        int padding = new Random().nextInt(160);
        danmaku.padding =  sp2px(padding);
        danmaku.textSize = sp2px(20);
        danmaku.textColor = color;
        danmaku.setTime(danmakuView.getCurrentTime() + 5200);
        danmaku.setDuration(new Duration(8000));
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = danmuList.get(i++);
                    addDanmaku(content, false , -1);

                    if (i >= danmuList.size()){
                        i = 0;
                    }
                    try {
                        Thread.sleep(time * 30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
        showDanmaku = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
        showDanmaku = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(),R.layout.authentication_dialog,null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        view.findViewById(R.id.authentication_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.authentication_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: authentication_btn");
                dialog.dismiss();
                goOtherActivity.goOhter(new RobActivity());
            }
        });

        dialog.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity != null){
            goOtherActivity = (GoOtherActivity) activity;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        int left = beginX-(package_btn.getWidth()/2);
        setViewPosition(package_btn,left, beginY - package_btn.getHeight(),left+package_btn.getWidth(),beginY);
    }
}
