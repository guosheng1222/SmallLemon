package com.example.interfaces;

/**
 * Created by lenovo on 2017/1/4.
 */

public interface OnMediaPlayerListener {
    void helpPlay(String path);

    void helpPause();

    void helpChangeMpIndex(int scale);

    void helpGetMdTime(OnChangeSeekBarListener onChangeSeekBarListener);

    void helpContinue();

}
