package com.daiqu.cm.daiqu.utils;

/**
 * Created by CM on 2017/7/29.
 */

public class TimeUtils {

    public static float getSeconds(long past, long now) {
        return (float)(now - past)/1000;
    }

}
