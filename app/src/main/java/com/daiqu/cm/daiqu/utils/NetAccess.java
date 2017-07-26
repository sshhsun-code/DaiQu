package com.daiqu.cm.daiqu.utils;

import android.os.Handler;

import com.daiqu.cm.daiqu.global.Constast;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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


}
