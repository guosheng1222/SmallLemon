package com.example.smalllemon;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.bean.HomeRadioStation;
import com.example.interfaces.OnChangeSeekBarListener;
import com.example.mservice.RadioStationService;
import com.example.utils.CommonUtils;

import java.text.SimpleDateFormat;

public class RadioStationActivity extends AppCompatActivity implements View.OnClickListener, OnChangeSeekBarListener {

    private ImageView mediaPlayer_img;
    private ImageView iv_pause;
    private ImageView iv_play;
    private TextView tv_current_position;
    private TextView tv_dutation;
    private SeekBar seekBar;
    private HomeRadioStation.DataBean radioInfo;
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBind = (RadioStationService.MyBind) iBinder;
            myBind.helpGetMdTime(RadioStationActivity.this);
            myBind.helpPlay(radioInfo.getUrl());
            Log.i("TAG", "isPlayer1" + MyApplication.isPlaying);
            if (MyApplication.isPlaying) {
                animation.start();
                iv_animation.setVisibility(View.VISIBLE);
                video.setVisibility(View.GONE);
                iv_play.setVisibility(View.VISIBLE);
                iv_pause.setVisibility(View.GONE);
            } else {
                animation.stop();
                iv_animation.setVisibility(View.GONE);
                video.setVisibility(View.VISIBLE);
                iv_play.setVisibility(View.GONE);
                iv_pause.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private RadioStationService.MyBind myBind;
    private SimpleDateFormat formatter;
    private ImageView video;
    private ImageView iv_animation;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_station);

        radioInfo = (HomeRadioStation.DataBean) getIntent().getExtras().getSerializable("radioInfo");
        //控制时间格式
        //Date currentTime = new Date();
        formatter = new SimpleDateFormat("mm:ss");

        //初始化控件
        initView();

//        开启服务
        initService();

        //拖动seekBar设置音频进度
        setMediaPlayerProgress();

    }

    private void setMediaPlayerProgress() {

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    myBind.helpChangeMpIndex(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 开启服务
     */
    private void initService() {
        Intent intent = new Intent(this, RadioStationService.class);
        startService(intent);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);

    }

    /**
     * 初始化控件
     */
    private void initView() {

        mediaPlayer_img = (ImageView) findViewById(R.id.mediaplay_img);
        iv_pause = (ImageView) findViewById(R.id.iv_pause);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        video = (ImageView) findViewById(R.id.video);
        tv_current_position = (TextView) findViewById(R.id.tv_current_position);
        tv_dutation = (TextView) findViewById(R.id.tv_duration);
        seekBar = (SeekBar) findViewById(R.id.mediaPlay_seekBar);
        iv_animation = (ImageView) findViewById(R.id.iv_animation);
        mediaPlayer_img.setOnClickListener(this);
        iv_pause.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        Glide.with(this).load(radioInfo.getImg()).into(mediaPlayer_img);
        mediaPlayer_img.setScaleType(ImageView.ScaleType.FIT_XY);


        animation = (AnimationDrawable) iv_animation.getDrawable();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mediaplay_img:
                if (MyApplication.isPlaying) {
                    myBind.helpPause();
                    iv_pause.setVisibility(View.VISIBLE);
                    iv_play.setVisibility(View.GONE);
                    video.setVisibility(View.VISIBLE);
                    animation.stop();
                    iv_animation.setVisibility(View.GONE);
                } else {
                    myBind.helpContinue();
                    iv_pause.setVisibility(View.GONE);
                    iv_play.setVisibility(View.VISIBLE);
                    video.setVisibility(View.GONE);
                    animation.start();
                    iv_animation.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.iv_pause:
                iv_pause.setVisibility(View.GONE);
                iv_play.setVisibility(View.VISIBLE);
                video.setVisibility(View.GONE);
                myBind.helpContinue();
                animation.start();
                iv_animation.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_play:
                iv_pause.setVisibility(View.VISIBLE);
                iv_play.setVisibility(View.GONE);
                video.setVisibility(View.VISIBLE);
                myBind.helpPause();
                animation.stop();
                iv_animation.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //接触绑定
        unbindService(conn);
    }

    @Override
    public void setDuration(final int duration) {
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                tv_dutation.setText(formatter.format(duration));
            }
        });
        seekBar.setMax(duration);
    }

    @Override
    public void setCurrentPosition(final int currentPosition) {
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                tv_current_position.setText(formatter.format(currentPosition));
            }
        });
        seekBar.setProgress(currentPosition);
    }
}
