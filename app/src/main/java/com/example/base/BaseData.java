package com.example.base;

import android.text.TextUtils;

/**
 * @author :   郗琛
 * @date :   2016/11/30
 */

public abstract class BaseData {
    public static final int NO_EXPIRATION = -1;
    public static final int NO_TIME = 0;
    public static final int NORMAL_TIME = 3 * 24 * 60 * 60 * 1000;


    //缓存数据存到哪里？
    public BaseData() {
    }

    /**
     * @param path      请求地址
     * @param args      请求参数
     * @param index     页码索引
     * @param validTime 有效时间
     */
    public void getDataForGet(String path, String args, int index, int validTime) {
        //先判断有效时间
        if (validTime == 0) {
            //直接请求网络，要最新数据
            getDataFromNet(path, args, index, validTime);
        } else {
            //从本地获取
            String data = getDataFromLocal(path + args, index, validTime);
            if (TextUtils.isEmpty(data)) {
                //如果为空，请求网络
                getDataFromNet(path, args, index, validTime);
            } else {
                //拿到了数据，返回数据
                onSuccessData(data);
            }
        }
    }

    public abstract void onSuccessData(String data);

    public abstract void onFailData(String data);

    /**
     * 从本地获取
     *
     * @param path
     * @param index
     * @param validTime
     * @return
     */
    private String getDataFromLocal(String path, int index, int validTime) {
        //读取文件信息

        return null;
    }

    /**
     * 从网络获取数据
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     */
    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {
        //创建okHttpClient对象

    }


    /**
     * 将数据写到本地
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     * @param
     */
    private void writeDataToLocal(String path, String args, int index, int validTime, String data) {
        //每一条请求，都是生成一个文件

    }
}
