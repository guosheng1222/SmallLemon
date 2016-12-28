package com.example.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by PC on 2016/12/28.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }


}
