package com.example.smalllemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bean.HomeRadioStation;

public class RadioStationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mediaPlayer_img;
    private ImageView iv_pause;
    private ImageView iv_play;
    private TextView tv_current_position;
    private TextView tv_dutation;
    private SeekBar seekBar;
    private HomeRadioStation.DataBean radioInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_station);

        radioInfo = (HomeRadioStation.DataBean) getIntent().getExtras().getSerializable("radioInfo");
        Log.i("TAG", "radioInfo--: "+radioInfo.getTitle());
        //初始化控件
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {

        mediaPlayer_img = (ImageView) findViewById(R.id.mediaplay_img);
        iv_pause = (ImageView) findViewById(R.id.iv_pause);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        tv_current_position = (TextView) findViewById(R.id.tv_current_position);
        tv_dutation = (TextView) findViewById(R.id.tv_duration);
        seekBar = (SeekBar) findViewById(R.id.mediaPlay_seekBar);
        mediaPlayer_img.setOnClickListener(this);
        iv_pause.setOnClickListener(this);
        iv_play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mediaplay_img:

                break;

            case R.id.iv_pause:

                break;

            case R.id.iv_play:


                break;
        }
    }
}
