package com.example.interfaces;

import com.example.view.MyScrollView;

/**
 * @author :   郗琛
 * @date :   2017/1/6
 */

public interface ScrollViewListener {
    void onScrollChanged(MyScrollView myScrollView, int x, int y, int oldx, int oldy);
}
