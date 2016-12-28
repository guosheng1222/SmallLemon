package com.example.utils;

import android.view.View;

/**
 * @author :   郗琛
 * @date :   2016/12/8
 */

public class MeasureUtils {
    public static int getMeasureHeight(View view) {

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(width, height);

        return view.getMeasuredHeight();

    }
}
