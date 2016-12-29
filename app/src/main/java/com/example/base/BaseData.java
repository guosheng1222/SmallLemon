package com.example.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bean.NetData;
import com.example.utils.DBUtils;
import com.example.utils.LogUtils;
import com.example.utils.NetUtils;

import org.xutils.ex.DbException;

import static com.example.utils.NetUtils.NET_WORK_TYPE_2G;
import static com.example.utils.NetUtils.NET_WORK_TYPE_3G;
import static com.example.utils.NetUtils.NET_WORK_TYPE_INVALID;
import static com.example.utils.NetUtils.NET_WORK_TYPE_WAP;
import static com.example.utils.NetUtils.NET_WORK_TYPE_WIFI;

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
     * @param url       请求地址
     * @param validTime 有效时间
     */
    public void getDataForGet(Context context, String url, int validTime) {
        this.mContext = context;
        this.mUrl = url;
        this.mValidTime = validTime;
        //判断当前的网络状态
        switch (NetUtils.getNetWorkType(mContext)) {
            case NET_WORK_TYPE_INVALID:             //没有网络
                jumpSettingNet();
                break;
            case NET_WORK_TYPE_WAP:
            case NET_WORK_TYPE_2G:
            case NET_WORK_TYPE_3G:
            case NET_WORK_TYPE_WIFI:
                //先判断有效时间
                if (validTime == NO_TIME) {
                    //直接请求网络，要最新数据
                    getDataFromNet();
                } else {
                    //从本地获取
                    String data = getDataFromLocal(url);
                    if (TextUtils.isEmpty(data)) {
                        //如果为空，请求网络
                        getDataFromNet();
                    } else {
                        //拿到了数据，返回数据
                        onSuccessData(data);
                    }
                }
                break;
            default:
                break;
        }

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
        LogUtils.i(TAG, "onFailData: " + data);
        //判断当前的网络状态
        switch (NetUtils.getNetWorkType(mContext)) {
            case NET_WORK_TYPE_INVALID:             //没有网络
                jumpSettingNet();
                break;
            case NET_WORK_TYPE_WAP:
            case NET_WORK_TYPE_2G:
            case NET_WORK_TYPE_3G:
            case NET_WORK_TYPE_WIFI:
                afreshGetData();
                break;
            default:
                break;
        }

    }

    /**
     * 无网络，提示设置网络
     */
    protected void jumpSettingNet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("刷新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //重新获取
                getDataFromNet();
            }
        }).show();
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
    private void getDataFromNet() {
        //创建volley对象
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        //创建请求
        StringRequest stringRequest = new StringRequest(mUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        onSuccessData(response);
                        if (mValidTime != NO_TIME)
                            writeDataToLocal(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onFailData(error);
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
}
