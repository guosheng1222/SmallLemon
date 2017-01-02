package com.example.smalllemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;

import com.zhy.autolayout.utils.AutoUtils;

import static android.R.attr.id;

public class LoginForgetActivity extends AppCompatActivity {
import com.example.base.BaseData;
import com.example.bean.RegisterMessage;
import com.example.bean.VerificationCode;
import com.example.utils.IsPhoneLegal;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;

import static com.example.base.BaseData.NO_TIME;
import static com.example.smalllemon.R.id.activity_rigister_verification_bt;
import static com.example.smalllemon.R.id.user_commit;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ZERO = 0;
    private AppCompatEditText userPhone;
    private AppCompatEditText userVerification;
    private Button btCode;
    private AppCompatEditText login_et_password;
    private int time = 60;
    private CheckBox look_password;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            time--;
            btCode.setText(time + "秒后重发");
            this.sendEmptyMessageDelayed(0, 1000);
            btCode.setTextColor(Color.WHITE);
            if (time == 0) {
                this.removeCallbacksAndMessages(null);
                btCode.setText("获取验证码");
                time = 60;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        activity_rigster_phone = (AppCompatEditText) findViewById(R.id.activity_rigster_phone);
        activity_rigister_verification = (AppCompatEditText) findViewById(R.id.activity_rigister_verification);

        autoView();
    }

    private void autoView() {
        AutoUtils.auto(findViewById(R.id.auto_1));
        AutoUtils.auto(findViewById(R.id.auto_2));
        userPhone = (AppCompatEditText) findViewById(R.id.activity_rigster_phone);
        userVerification = (AppCompatEditText) findViewById(R.id.et_rigister_verification);
        login_et_password = (AppCompatEditText) findViewById(R.id.login_et_password);
        btCode = (Button) findViewById(R.id.activity_rigister_verification_bt);
        btCode.setOnClickListener(this);
        look_password = (CheckBox) findViewById(R.id.look_password);
        look_password.setOnClickListener(this);
        findViewById(user_commit).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //提交
            case user_commit:
                getCommitMessage();
                break;
            //获取验证码
            case activity_rigister_verification_bt:
                getCode();
                break;
            case R.id.look_password:
                if (look_password.isChecked()) {
                    login_et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    login_et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        if (!TextUtils.isEmpty(getPhone()) && !IsPhoneLegal.isPhoneLegal(getPhone())) {
            toastMessage("手机号码格式不对");
        } else if (TextUtils.isEmpty(getPhone())) {
            toastMessage("请输入手机号码");
        } else {
            if (time == 60) {
                handler.sendEmptyMessageDelayed(ZERO, 1000);
                new BaseData() {
                    @Override
                    public void onSuccessData(String data) {
                        VerificationCode verificationCode = new Gson().fromJson(data, VerificationCode.class);
                        if (verificationCode.getStatus().equals("ok")) {
                            toastMessage("已发送至手机");
                        }
                    }
                }.getDataForGet(LoginForgetActivity.this, UrlUtils.getCode + getPhone(), NO_TIME);
            }

        }
    }

    /**
     * 获得提交信息
     */
    private void getCommitMessage() {
        String verification = userVerification.getText().toString();
        if (TextUtils.isEmpty(getPhone())) {
            toastMessage("手机号码不能为空");
        } else if (TextUtils.isEmpty(verification)) {
            toastMessage("请输入验证码");
        } else if (TextUtils.isEmpty(getPassword())) {
            toastMessage("密码不能为空");
        } else if (!TextUtils.isEmpty(getPhone()) && !IsPhoneLegal.isPhoneLegal(getPhone())) {
            toastMessage("手机格式不对");
        } else {
                /*验证用户短信是否正确*/
            new BaseData() {
                @Override
                public void onSuccessData(String data) {
                    VerificationCode verificationCode = new Gson().fromJson(data, VerificationCode.class);
                    switch (verificationCode.getStatus()) {
                        case "ok":
                            updateUserMessage(getPhone(), getPassword());
                            break;
                        case "error":
                            toastMessage(verificationCode.getData().getMessage());
                            break;
                    }
                }
            }.getDataForGet(LoginForgetActivity.this, "http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.do?telNum=" + getPhone() + "&verCode=" + verification, BaseData.NO_TIME);

        }

    }

    /**
     * 保存用户信息到服务器
     */

    private void updateUserMessage(final String phone, String password) {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                RegisterMessage registerMessage = new Gson().fromJson(data, RegisterMessage.class);
                if (registerMessage.getStatus().equals("ok")) {
                    toastMessage("修改成功");
                    finish();
                }
            }
        }.getDataForGet(LoginForgetActivity.this, "http://114.112.104.151:8203/LvScore_Service/visit/setUserLoginPassword.do?telNum=" + phone + "&password=" + password, NO_TIME);
    }

    /**
     * 获取用户的手机号
     */
    public String getPhone() {
        return userPhone.getText().toString().trim();
    }

    public String getPassword() {
        return login_et_password.getText().toString().trim();
    }

    /**
     * 吐司信息
     */
    public void toastMessage(String data) {
        Toast.makeText(LoginForgetActivity.this, data, Toast.LENGTH_SHORT).show();
    }
}
