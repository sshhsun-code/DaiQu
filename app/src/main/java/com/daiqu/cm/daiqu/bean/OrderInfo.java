package com.daiqu.cm.daiqu.bean;

import java.util.Date;

/**
 * Created by CM on 2017/7/27.
 */

public class OrderInfo {
    String order_name; //订单中姓名
    String order_phone; //订单中的电话
    String order_pick_num; //取货码
    String order_home_address; //送达地址
    String order_pick_address; //取货地址
    int order_price; //价格
    String order_pick_time; //订单送达时间
    Date order_date; //订单提交时间，当前时间
    String order_company_type; //快递公司种类
    String userphone; //用户手机号，即账号
    String order_serial_num; //订单编号，取订单提交时间精确到毫秒的String


    public OrderInfo(String order_name, String order_phone,
                     String order_pick_num, String order_home_address,
                     String order_pick_address, int order_price, String order_pick_time,
                     Date order_date, String order_company_type, String userphone,String order_serial_num) {
        this.order_name = order_name;
        this.order_phone = order_phone;
        this.order_pick_num = order_pick_num;
        this.order_home_address = order_home_address;
        this.order_pick_address = order_pick_address;
        this.order_price = order_price;
        this.order_pick_time = order_pick_time;
        this.order_date = order_date;
        this.order_company_type = order_company_type;
        this.userphone = userphone;
        this.order_serial_num = order_serial_num;
    }


    public OrderInfo() {
    }


    public String getOrder_name() {
        return order_name;
    }
    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }
    public String getOrder_phone() {
        return order_phone;
    }
    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }
    public String getOrder_pick_num() {
        return order_pick_num;
    }
    public void setOrder_pick_num(String order_pick_num) {
        this.order_pick_num = order_pick_num;
    }
    public String getOrder_home_address() {
        return order_home_address;
    }
    public void setOrder_home_address(String order_home_address) {
        this.order_home_address = order_home_address;
    }
    public String getOrder_pick_address() {
        return order_pick_address;
    }
    public void setOrder_pick_address(String order_pick_address) {
        this.order_pick_address = order_pick_address;
    }
    public int getOrder_price() {
        return order_price;
    }
    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }
    public String getOrder_pick_time() {
        return order_pick_time;
    }
    public void setOrder_pick_time(String order_pick_time) {
        this.order_pick_time = order_pick_time;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public String getOrder_company_type() {
        return order_company_type;
    }
    public void setOrder_company_type(String order_company_type) {
        this.order_company_type = order_company_type;
    }
    public String getUserphone() {
        return userphone;
    }
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
    public String getOrder_serial_num() {
        return order_serial_num;
    }
    public void setOrder_serial_num(String order_serial_num) {
        this.order_serial_num = order_serial_num;
    }
    @Override
    public String toString() {
        return "OrderInfo [order_name=" + order_name + ", order_phone="
                + order_phone + ", order_pick_num=" + order_pick_num
                + ", order_home_address=" + order_home_address
                + ", order_pick_address=" + order_pick_address
                + ", order_price=" + order_price + ", order_pick_time="
                + order_pick_time + ", order_date=" + order_date
                + ", order_company_type=" + order_company_type + ", userphone="
                + userphone + ", order_serial_num=" + order_serial_num + "]";
    }


}
