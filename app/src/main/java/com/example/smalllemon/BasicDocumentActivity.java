package com.example.smalllemon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.base.BaseActivity;
import com.example.view.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import org.feezu.liuli.timeselector.TimeSelector;


public class BasicDocumentActivity extends BaseActivity implements View.OnClickListener {

    private TextView title_name;
    private CircleImageView head;
    private AutoLinearLayout head_click;
    private TextView nickname;
    private AutoLinearLayout nickname_click;
    private TextView birthday;
    private AutoLinearLayout date_click;
    private TextView constellation;
    private TextView emotion;
    private AutoLinearLayout emotion_click;
    private TextView job;
    private AutoLinearLayout job_click;
    private TimeSelector timeSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_document);
        //设置标题
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("基本信息");
        //设置头像
        head = (CircleImageView) findViewById(R.id.head);
        Glide.with(this).load(MyApplication.CURRENT_USER.getImg()).into(head);
        head_click = (AutoLinearLayout) findViewById(R.id.head_click);
        //修改昵称
        nickname = (TextView) findViewById(R.id.nickname);
        nickname_click = (AutoLinearLayout) findViewById(R.id.nickname_click);
        nickname.setText(MyApplication.CURRENT_USER.getUserName());
        //生日
        birthday = (TextView) findViewById(R.id.birthday);
        date_click = (AutoLinearLayout) findViewById(R.id.date_click);
        birthday.setText(MyApplication.CURRENT_USER.getBirthday());
        //星座
        constellation = (TextView) findViewById(R.id.constellation);
        constellation.setText(MyApplication.CURRENT_USER.getConstellation());
        //职业
        job = (TextView) findViewById(R.id.job);
        job_click = (AutoLinearLayout) findViewById(R.id.job_click);
        if (!TextUtils.isEmpty(MyApplication.CURRENT_USER.getOccupation())) {
            job.setText(MyApplication.CURRENT_USER.getOccupation());
        }
        //情感
        emotion = (TextView) findViewById(R.id.emotion);
        emotion_click = (AutoLinearLayout) findViewById(R.id.emotion_click);
        emotion.setText(MyApplication.CURRENT_USER.getEmotionStage() == 1 ? "恋爱期" : "单身期");

        nickname_click.setOnClickListener(this);
        head_click.setOnClickListener(this);
        date_click.setOnClickListener(this);
        job_click.setOnClickListener(this);
        emotion_click.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_click:
                HeadPopupWindow(head);
                break;
            case R.id.nickname_click:
                jump(UpdateNickNameActivity.class);
                break;
            case R.id.date_click:

                timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                    }
                }, "1989-01-30 00:00", "2018-12-31 00:00");
//                有bug，不使用
//                timeSelector.setIsLoop(true);//不设置时为true，即循环显示
                timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
                timeSelector.show();

                break;
            case R.id.job_click:
                jump(JobActivity.class);
                break;
            case R.id.emotion_click:
                jump(EmotionActivity.class);
                break;

        }
    }

    private void jump(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    //基本资料的PopUpWindow
    private void HeadPopupWindow(CircleImageView head) {
        View view = View.inflate(this, R.layout.head_pop_layout, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.anim_popup_centerbar);
        popupWindow.showAtLocation(head, Gravity.CENTER, 0, 0);
        popupWindow.setOutsideTouchable(true);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


}
