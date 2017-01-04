package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.bean.LoginBean;
import com.example.smalllemon.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by lenovo on 2016/12/28.
 */

public class MineFragment extends Fragment {
    private LoginBean.DataBean current_user;
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

        /**
         * 初始化界面
         */
        if (MyApplication.CURRENT_USER != null) {
            current_user = MyApplication.CURRENT_USER;
            Glide.with(getActivity()).load(current_user.getImg()).into(iv_user_head);
            tv_user_name.setText(current_user.getUserName());
            tv_guanzhu_count.setText(current_user.getFansCount()+"");
            if (current_user.getGender() == 1) {
                //设置男
            } else {
                //设置女
            }
        }


    }
}
