package com.daiqu.cm.daiqu.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by CM on 2017/7/26.
 *
 * 短信验证码的接入与处理
 */

public class MessageVerification {

    //发送验证码的请求路径URL
    private static final String
            SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号
    private static final String
            APP_KEY="57c02f85e56a4ed386a68572183ab842";
    //网易云信分配的密钥
    private static final String APP_SECRET="ccd56a161558";
    //随机数
    private static final String NONCE="123456";
    //短信模板ID
    private static final String TEMPLATEID="3053658";
    //手机号
    private static final String MOBILE="18829085916";
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN="4";
    public static void doPost() {
        try {
            URL url = new URL(SERVER_URL);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(100);
            connection.setRequestMethod("POST");

            String curTime = String.valueOf((new Date()).getTime() / 1000L);

            String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

            //设置http访问头部
            connection.addRequestProperty("AppKey", APP_KEY);
            connection.addRequestProperty("Nonce", NONCE);
            connection.addRequestProperty("CurTime", curTime);
            connection.addRequestProperty("CheckSum", checkSum);
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            String templateid = TEMPLATEID;
            String mobile = MOBILE;
            String codeLen = CODELEN;

            connection.connect();

            String parmas = "templateid = " +templateid +"&mobile="+mobile+"&codelen="+codeLen;

            //设置http访问参数
            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            writer.write(parmas);
            writer.flush();
            writer.close();


            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream is = connection.getInputStream();
                String res = IOUtils.toString(is);
                System.out.println("duanxin res:"+res);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
