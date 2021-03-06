package com.example.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.example.bean.LoginBean;
import com.example.utils.CommonUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * Created by PC on 2016/12/28.
 */

public class MyApplication extends Application {
    public static boolean isPlaying = false;
    public static boolean isFirst = true;
    public static String radioName = "没有URL";
    private static Context context;
    private static Handler handler;
    public static LoginBean.DataBean CURRENT_USER;
    public static String TOKEN;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
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

        TOKEN = CommonUtils.getStringSP("token");

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
