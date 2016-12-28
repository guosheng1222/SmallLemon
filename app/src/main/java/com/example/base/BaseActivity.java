package com.example.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by PC on 2016/12/28.
 */

public class BaseActivity extends AutoLayoutActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initSystemBar(this);
    }
    public static void initSystemBar(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
    }
    @TargetApi(19)

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);

    }
}
