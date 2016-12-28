package com.example.util;

import com.example.app.MyApplication;

/**
 * Created by lenovo on 2016/12/28.
 */

public class CommonUtils {

    /**
     * 获取Color中的属性
     *
     * @param colorId
     * @return
     */

    public static int getResourseColor(int colorId) {
        int color = MyApplication.getContext().getResources().getColor(colorId);
        return color;
    }


}
