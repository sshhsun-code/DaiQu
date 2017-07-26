package com.daiqu.cm.daiqu.bean;

/**
 * Created by CM on 2017/7/26.
 * 短信验证码bean
 */

public class VerificationBean {

    int code; //返回码
    String msg; //发送ID
    String obj; //验证码内容

    public VerificationBean() {

    }

    public VerificationBean(int code, String msg, String obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "VerificationBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj='" + obj + '\'' +
                '}';
    }
}
