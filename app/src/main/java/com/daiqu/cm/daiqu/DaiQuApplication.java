package com.daiqu.cm.daiqu;

import android.app.Application;

/**
 * Created by CM on 2017/7/24.
 */

public class DaiQuApplication extends Application{

    private static DaiQuApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static DaiQuApplication getInstance() {
        if (application == null) {
            application = new DaiQuApplication();
        }

        return application;
    }
}
