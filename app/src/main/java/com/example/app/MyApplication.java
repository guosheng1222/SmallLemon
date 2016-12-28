package com.example.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * Created by PC on 2016/12/28.
 */

public class MyApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;



    @Override
    public void onCreate() {
        super.onCreate();

        //autoLayout
        AutoLayoutConifg.getInstance().useDeviceSize();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //获取主线程的线程号
        mainThreadId = Process.myTid();
        //xutils3初始化配置
        x.Ext.init(this);
        //设置是debug模式
        x.Ext.setDebug(false);
    }


    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return Thread.currentThread();
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
