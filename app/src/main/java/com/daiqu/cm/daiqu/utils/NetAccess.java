package com.daiqu.cm.daiqu.utils;

import android.os.Handler;
import android.os.Message;

import com.daiqu.cm.daiqu.global.Constast;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.path;

/**
 * Created by CM on 2017/7/25.
 * 网络访问操作
 */

public class NetAccess {

    private static final String BASEURL = "http://192.168.43.90:8080/DaiquServer/";

    public static void AccessLogin(String name, String password, final Handler handler) {
        final String accessUrl = BASEURL + "login.do?username="+name+"&password="+password;

        new Thread(new Runnable() {
            @Override
            public void run() {
                doGet(accessUrl,handler);
            }
        }).start();
    }

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
                IOUtils.toString(is);
                handler.sendEmptyMessage(Constast.NET_SUCCESS);
            } else {
                handler.sendEmptyMessage(Constast.NET_FAIL);
            }
        } catch (IOException e) {
            handler.sendEmptyMessage(Constast.NET_FAIL);
//            e.printStackTrace();
        }

    }

    private static void doPost() {

    }


}
