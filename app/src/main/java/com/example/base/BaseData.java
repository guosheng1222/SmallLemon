package com.example.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bean.NetData;
import com.example.utils.CommonUtils;
import com.example.utils.DBUtils;
import com.example.utils.MD5Utils;
import com.example.utils.NetUtils;

import org.xutils.ex.DbException;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.utils.NetUtils.NET_WORK_TYPE_INVALID;

/**
 * @author :   郗琛
 * @date :   2016/11/30
 */

public abstract class BaseData {
    private static final String TAG = "BaseData";
    public static final int NO_TIME = 0;
    public static final int NORMAL_TIME = 3 * 24 * 60 * 60 * 1000;
    public static final int MONTH_TIME = 3 * 24 * 60 * 60 * 1000;
    public static final int MAX_TIME = Integer.MAX_VALUE;
    private Context mContext;
    private String mUrl;
    private int mValidTime;
    /**
     * 网络状态改变监听
     */
    private BroadcastReceiver netReceiver;
    private AlertDialog.Builder builder;

    public void getDataForGet(Context context, String url) {
        getDataForGet(context, url, 0);
    }


    /**
     * @param url       请求地址
     * @param validTime 有效时间
     */
    public void getDataForGet(Context context, String url, int validTime) {
        this.mContext = context;
        this.mUrl = url;
        this.mValidTime = validTime;
//        startReceiver();
        if (getIsNoNet()) {         //有网络
            //先判断有效时间
            if (validTime == NO_TIME) {
                //直接请求网络，要最新数据
                getDataFromNet();
            } else {
                //从本地获取
                final String data = getDataFromLocal(url);
                if (TextUtils.isEmpty(data)) {
                    //如果为空，请求网络
                    getDataFromNet();
                } else {
                    //拿到了数据，返回数据
                    CommonUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            onSuccessData(data);
                        }
                    });
                }
            }
        } else {            //没有网络
//            jumpSettingNet();
            afreshGetData();
        }

    }

    /**
     * 开启网络广播监听
     */
    private void startReceiver() {
        //如果无网络连接activeInfo为null
        //Toast.makeText(context, intent.getAction(), 1).show();
        netReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getDataFromNet();
            }
        };
        IntentFilter filter = new IntentFilter();
        //网络改变过滤
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.setPriority(Integer.MAX_VALUE);
        mContext.registerReceiver(netReceiver, filter);
//        mContext.unregisterReceiver(netReceiver);
    }


    /**
     * 成功的操作
     *
     * @param data
     */
    public abstract void onSuccessData(String data);

    /**
     * 失败的操作
     */
    public void onFailData(Exception data) {
//        if (getIsNoNet()) {
        afreshGetData();
//        } else {
//            jumpSettingNet();
//        }
    }


    /**
     * 是否有网
     */
    private boolean getIsNoNet() {
        //判断当前的网络状态
        if (NET_WORK_TYPE_INVALID != NetUtils.getNetWorkType(mContext))
            return true;
        return false;
    }

    /**
     * 无网络，提示设置网络
     */
    protected void jumpSettingNet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("当前无网络，是否去进行设置").setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转设置界面
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                mContext.startActivity(wifiSettingsIntent);
            }
        }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) mContext).finish();
            }
        }).show();
    }

    /**
     * 再次获取
     */
    private void afreshGetData() {
        if (builder == null) {
            builder = new AlertDialog.Builder(mContext);
            builder.setCancelable(false);
            builder.setTitle("网络出错，请刷新重试").setPositiveButton("刷新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //重新获取
                    builder = null;
                    getDataFromNet();
                }
            }).show();
        }
    }

    /**
     * 从本地获取
     *
     * @param url
     * @return
     */
    private String getDataFromLocal(String url) {
        //读取文件信息
        try {
            NetData first = DBUtils.getDb().selector(NetData.class).where("URL", "=", url).and("EXPIRATION_TIME", "<=", System.currentTimeMillis()).findFirst();
            return first.getContent();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从网络获取数据
     */
    int i;

    private void getDataFromNet() {
        //创建volley对象
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        //创建请求
        StringRequest stringRequest = new StringRequest(mUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        //拿到了数据，返回数据
                        CommonUtils.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                onSuccessData(response);
                            }
                        });
                        if (mValidTime != NO_TIME)
                            writeDataToLocal(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null == error || null == error.networkResponse || error.networkResponse.statusCode >= 400) {
                    onFailData(error);
                }
            }
        });
        //加入请求队列
        mQueue.add(stringRequest);
    }


    /**
     * 将数据写到本地
     *
     * @param data
     * @param
     */
    private void writeDataToLocal(String data) {
        //每一条请求，都是生成一个文件
        //保存到本地
        try {
            DBUtils.getDb().saveOrUpdate(new NetData(mUrl, data, mValidTime));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    /**
     * 登陆post请求
     */
    public void getNetForPost(Context context, final String phone, final String password) {
        mContext = context;
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.yulin520.com/a2a/home/login/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onSuccessData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error || null == error.networkResponse || error.networkResponse.statusCode >= 400) {
                    onFailData(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<>();
                long l = System.currentTimeMillis();
                map.put("staffCode", phone);
                map.put("password", MD5Utils.MD5(password));
                map.put("ts", l + "");
                map.put("sign", MD5Utils.MD5(phone + password + l + "GOyV3qmT)CR5!Gee'zAj@7W"));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
