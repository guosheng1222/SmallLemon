package com.example.base;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bean.NetData;
import com.example.utils.DBUtils;

import org.xutils.ex.DbException;

/**
 * @author :   郗琛
 * @date :   2016/11/30
 */

public abstract class BaseData {
    public static final int NO_TIME = 0;
    public static final int NORMAL_TIME = 3 * 24 * 60 * 60 * 1000;
    public static final int MONTH_TIME = 3 * 24 * 60 * 60 * 1000;
    public static final int MAX_TIME = Integer.MAX_VALUE;


    /**
     * @param url       请求地址
     * @param validTime 有效时间
     */
    public void getDataForGet(Context context, String url, int validTime) {
        //先判断有效时间
        if (validTime == NO_TIME) {
            //直接请求网络，要最新数据
            getDataFromNet(context, url, validTime);
        } else {
            //从本地获取
            String data = getDataFromLocal(url);
            if (TextUtils.isEmpty(data)) {
                //如果为空，请求网络
                getDataFromNet(context, url, validTime);
            } else {
                //拿到了数据，返回数据
                onSuccessData(data);
            }
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
     *
     * @param data
     */
    public abstract void onFailData(Exception data);

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
     *
     * @param context
     * @param url
     * @param validTime 保存时间
     */
    private void getDataFromNet(Context context, final String url, final long validTime) {
        //创建volley对象
        RequestQueue mQueue = Volley.newRequestQueue(context);
        //创建请求
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        onSuccessData(response);
                        if (validTime != NO_TIME)
                            writeDataToLocal(url, response, validTime);
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
     * @param url
     * @param validTime
     * @param data
     * @param
     */
    private void writeDataToLocal(String url, String data, long validTime) {
        //每一条请求，都是生成一个文件
        //保存到本地
        try {
            DBUtils.getDb().saveOrUpdate(new NetData(url, data, validTime));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
