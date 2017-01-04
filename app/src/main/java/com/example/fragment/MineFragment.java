package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smalllemon.BasicDocumentActivity;
import com.example.smalllemon.FeedBackActivity;
import com.example.smalllemon.R;
import com.example.smalllemon.SettingActivity;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by lenovo on 2016/12/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView iv_user_head;
    private TextView tv_user_name;
    private TextView tv_guanzhu;
    private TextView tv_guanzhu_count;
    private AutoLinearLayout base_info;
    private AutoLinearLayout mine_card;
    private AutoLinearLayout suggest_back;
    private AutoLinearLayout setting;
    private RecyclerView mine_recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        //动态设置状态栏
        if (21 > android.os.Build.VERSION.SDK_INT) {
            view.setPadding(0, 0, 0, 0);
        }
        //初始化控件
        initView();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        iv_user_head = (ImageView) view.findViewById(R.id.iv_user_head);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_guanzhu = (TextView) view.findViewById(R.id.tv_guanzhu);
        tv_guanzhu_count = (TextView) view.findViewById(R.id.tv_guanzhu_count);
        base_info = (AutoLinearLayout) view.findViewById(R.id.base_info);
        mine_card = (AutoLinearLayout) view.findViewById(R.id.mine_card);
        suggest_back = (AutoLinearLayout) view.findViewById(R.id.suggest_back);
        setting = (AutoLinearLayout) view.findViewById(R.id.setting);
        mine_recyclerView = (RecyclerView) view.findViewById(R.id.mine_recyclerView);

        tv_guanzhu.setOnClickListener(this);
        base_info.setOnClickListener(this);
        mine_card.setOnClickListener(this);
        suggest_back.setOnClickListener(this);
        setting.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guanzhu:

                break;
            case R.id.base_info:
                enterActivity(BasicDocumentActivity.class);
                break;
            case R.id.mine_card:
                break;
            case R.id.suggest_back:
                enterActivity(FeedBackActivity.class);
                break;
            case R.id.setting:
                enterActivity(SettingActivity.class);
                break;
        }

    }

    /**
     * 跳转界面
     */
    private void enterActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        getActivity().startActivity(intent);
    }

}
