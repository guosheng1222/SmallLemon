package com.example.loadanim;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/5.
 */

public class LoadAnim {
    /**
     * 给我view，我加载动画
     *
     * @param view
     */
    public void loadingAnim(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 500, 200, 50, 0);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }
}
