package com.example.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.utils.LogUtils;
import com.example.utils.MiUIUtls;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by PC on 2016/12/28.
 */

public class BaseActivity extends AutoLayoutActivity {

    private Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initMIUIStatusTextColor();

    }


    private void initData() {
        mContext = this;
    }

    /**
     * 存放请求码以及运行代码
     */
    private HashMap<Integer, Runnable> permissionMap = new HashMap<>();

    /**
     * * 请求权限
     *
     * @param permissionCode 请求码
     * @param permissions    请求的权限
     * @param runnable       运行的动作
     */
    public void requestPermission(Integer permissionCode, String[] permissions, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 23) {      //如果为6.0以上进行权限申请
            //未被用户同意的权限组
            ArrayList<String> prepareRequestPermission = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                //查询是否授予
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, permissions[i]);
                if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    //未授予权限，准备申请权限
                    prepareRequestPermission.add(permissions[i]);
                }
            }
            //判断是否应该申请
            if (prepareRequestPermission.size() == 0) {
                //全部授权，执行动作
                runnable.run();
            } else {
                //有未申请的权限，进行申请
                //数组转化
                String[] strings = prepareRequestPermission.toArray(new String[prepareRequestPermission.size()]);
                //申请权限
                ActivityCompat.requestPermissions(mContext, strings, permissionCode);
            }
        } else {
            //无需授权，执行动作
            runnable.run();
        }
    }

    /**
     * 运行请求的事件
     *
     * @param permissionCode
     */
    private void runRequest(Integer permissionCode) {
        permissionMap.get(permissionCode).run();
        permissionMap.remove(permissionCode);
    }

    /**
     * 拒绝请求
     */
    private void deniedRequest(Integer permissionCode) {
        permissionMap.remove(permissionCode);
        Toast.makeText(BaseActivity.this, "你必须同意该权限才可以使用该功能", Toast.LENGTH_SHORT)
                .show();
    }

    private static final String TAG = "BaseActivity";

    /**
     * 请求权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //map转化set
        Set<Integer> entries = permissionMap.keySet();
        for (Integer permissionCode : entries) {
            if (permissionCode == requestCode) {        //有对应的请求
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        // Permission Denied
                        deniedRequest(requestCode);
                        return;
                    }
                }
                // Permission Success
                runRequest(permissionCode);
                return;
            }
        }
        //没有对应的请求
        LogUtils.i(TAG, "onRequestPermissionsResult: 没有对应的请求");
    }

    /**
     * 适配MIUI的statusBar颜色
     */
    private void initMIUIStatusTextColor() {
        //字体
        if (MiUIUtls.isMIUI()) {
            MiUIUtls.setStatusBarDarkMode(this, true);
        }
    }
}
