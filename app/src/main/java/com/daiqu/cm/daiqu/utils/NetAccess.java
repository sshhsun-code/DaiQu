package com.daiqu.cm.daiqu.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.daiqu.cm.daiqu.bean.VerificationBean;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;
import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by CM on 2017/7/25.
 * 网络访问操作
 */

public class NetAccess {

    private static final String BASEURL = "http://192.168.43.90:8080/DaiquServer/";

    /**
     * 进行登录操作
     * @param name
     * @param password
     * @param handler
     */
    public static void AccessLogin(String name, String password, final Handler handler) {
        final String accessUrl = BASEURL + "login.do?username="+name+"&password="+password;

        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(accessUrl,handler);
            }
        }).start();
    }

    /**
     * 进行注册操作
     * @param name
     * @param password
     * @param handler
     */
    public static void AccessSignUp(String name, String password, final Handler handler) {
        final String accessUrl = BASEURL + "signup.do?username="+name+"&password="+password;

        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(accessUrl,handler);
            }
        }).start();
    }

    /**
     * 提交订单信息操作
     * @param str
     * @param handler
     */
    public static void upOrderInfo(final String str, final Handler handler) {
        final String orderInfoUrl = BASEURL + "addexpressorder.do?params=" + str;

        new Thread(new Runnable() {
            @Override
            public void run() {
//                doGet(orderInfoUrl,handler);
                doPost(str,handler);
            }
        }).start();
    }

    /**
     * 查询订单信息
     * @param phone
     * @param num
     * @param handler
     */
    public static void getOrderStatus(String phone, String num, final Handler handler){
        final String getStatusUrl = BASEURL + "getorder.do?phone="+phone+"&order_serial_num="+num;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(getStatusUrl,handler);
            }
        }).start();
    }
    /**
     * 确认订单已收货，完成
     * @param phone
     * @param num
     * @param handler
     */
    public static void confirmOrderStatusDone(String phone, String num, final Handler handler){
        final String getStatusUrl = BASEURL + "updateorder.do?phone="+phone+"&order_serial_num="+num+"&status="+23;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(getStatusUrl,handler);
            }
        }).start();
    }

    /**
     * get请求访问
     * @param path
     * @param handler
     */
    private static void doGet(String path,Handler handler) {

        try {
            URL url = new URL(path);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(100);
            connection.setRequestMethod("GET");
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream is = connection.getInputStream();
                String res = IOUtils.toString(is);
                if ("6".equals(res)) {
                    handler.sendEmptyMessage(Constast.NET_SIGNUP_SUCCESS);
                } else if ("0".equals(res)){
                    handler.sendEmptyMessage(Constast.NET_LOGIN_SUCCESS);
                } else if ("1".equals(res)){
                    handler.sendEmptyMessage(Constast.NET_LOGIN_FAIL);
                } else if ("20".equals(res) || "21".equals(res) || "22".equals(res) || "23".equals(res)){
                    handler.sendEmptyMessage(Integer.parseInt(res));
                }
            } else {
                handler.sendEmptyMessage(Constast.NET_LOGIN_FAIL);
            }
        } catch (IOException e) {
            handler.sendEmptyMessage(Constast.NET_LOGIN_FAIL);
            e.printStackTrace();
        }

    }



    private static void doPost() {

    }


    public static void doPost(final String params, Handler handler) {

        try {
            URL url = new URL(BASEURL + "addexpressorder.do");
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("POST");
            connection.connect();

            //设置http访问参数
            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            writer.write(params);
            writer.flush();
            writer.close();


            //获得结果码
            int responseCode = connection.getResponseCode();
            Message message = handler.obtainMessage(Constast.GET_VERIFICVATION_NUM);
            message.arg1 = responseCode;
            if (responseCode == 200) {

            }
            handler.sendMessage(message);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }




}
