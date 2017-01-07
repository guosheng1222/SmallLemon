package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.app.MyApplication;
import com.example.interfaces.OnChangeSeekBarListener;
import com.example.interfaces.OnMediaPlayerListener;

import java.io.IOException;

/**
 * Created by lenovo on 2017/1/4.
 */

public class RadioStationService extends Service {


    private MediaPlayer mediaPlayer;
    private OnChangeSeekBarListener onChangeSeekBarListener;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();

    }

    /**
     * 开始播放
     */
    private void play(final String path) {
        if (!MyApplication.radioName.equals(path)) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        if (!MyApplication.isFirst) {
                            mediaPlayer.reset();
                        } else {
                            MyApplication.isFirst = false;
                        }
                        MyApplication.isPlaying = true;
                        MyApplication.radioName = path;
                        mediaPlayer.setDataSource(path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


        int duration = mediaPlayer.getDuration();
        onChangeSeekBarListener.setDuration(duration);
        new Thread() {

            private int currentPosition;

            @Override
            public void run() {
                super.run();

                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentPosition = mediaPlayer.getCurrentPosition();
                    onChangeSeekBarListener.setCurrentPosition(currentPosition);
                }
            }
        }.start();

    }

    /**
     * 暂停
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            MyApplication.isPlaying = false;
            mediaPlayer.pause();
        }
    }

    private void continuePlay() {
        if (!mediaPlayer.isPlaying() && mediaPlayer != null) {
            mediaPlayer.start();
            MyApplication.isPlaying = true;
        }
    }


    /**
     * 改变MediaPlayer的播放进度
     */
    private void changeMpIndex(int scale) {
        mediaPlayer.seekTo(scale);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class MyBind extends Binder implements OnMediaPlayerListener {

        @Override
        public void helpPlay(String path) {
            play(path);
        }

        @Override
        public void helpPause() {
            pause();
        }

        @Override
        public void helpChangeMpIndex(int scale) {
            changeMpIndex(scale);
        }

        @Override
        public void helpGetMdTime(OnChangeSeekBarListener onChangeSeekBarListener) {
            RadioStationService.this.onChangeSeekBarListener = onChangeSeekBarListener;
        }

        @Override
        public void helpContinue() {
            continuePlay();
        }


    }


}
