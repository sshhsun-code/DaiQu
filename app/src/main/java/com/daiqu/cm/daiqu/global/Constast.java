package com.daiqu.cm.daiqu.global;

/**
 * Created by CM on 2017/7/24.
 * 用于存放全局常量
 */

public class Constast {
    public static final String FRAGMENT_NAME = "fragment_name";

    //网络
    public static final int NET_LOGIN_SUCCESS = 0; //网络登录成功
    public static final int NET_LOGIN_FAIL = 1; //网络登录失败

    public static final int NET_SIGNUP_SUCCESS = 2; //网络注册成功
    public static final int NET_SIGNUP_FAIL = 3; //网络注册失败

    public static final int GET_VERIFICVATION_NUM = 4; //得到验证码

    //SP
    public static final String NAME = "true_name";
    public static final String USER_NAME = "user_name";
    public static final String PHONE = "phone_number";
    public static final String SCHOOL = "school_name";
    public static final String COMMON_ADDRESS = "common_address";
    public static final String HAS_ADDED = "has_added";
    public static final String IS_FIRST = "is_first";
    public static final String LOGIN_PHONE_NUMBER = "login_phone_number";
    public static final String NEW_ORDER_NUMBER = "new_order_number";

    //订单状态
//    * 0     waitting       等待接单
//    * 1      picked         已接单
//    * 2      Receiving   请确认收货
//    * 3      done            已完成
    public static final int  WAITTING  = 20;
    public static final int  PICKED  = 21;
    public static final int  RECEIVING  = 22;
    public static final int  DONE  = 23;

}
