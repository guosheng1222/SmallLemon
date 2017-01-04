package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.BaseData;
import com.example.bean.BoardBean;
import com.example.smalllemon.R;
import com.example.utils.CommonUtils;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * @author :   郗琛
 * @date :   2016/12/30
 */

public class ComBoardFragment extends Fragment implements View.OnClickListener {

    private View first_card;
    private View second_card;
    private View third_card;
    private View four_card;
    private View five_card;
    private TextView first_number;
    private TextView second_number;
    private TextView third_number;
    private TextView four_number;
    private TextView five_number;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.inflate(R.layout.fragment_com_board);
        initView(view);
        initData();
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        first_card = view.findViewById(R.id.first_card);
        second_card = view.findViewById(R.id.second_card);
        third_card = view.findViewById(R.id.third_card);
        four_card = view.findViewById(R.id.four_card);
        five_card = view.findViewById(R.id.five_card);
        first_number = (TextView) view.findViewById(R.id.first_number);
        second_number = (TextView) view.findViewById(R.id.second_number);
        third_number = (TextView) view.findViewById(R.id.third_number);
        four_number = (TextView) view.findViewById(R.id.four_number);
        five_number = (TextView) view.findViewById(R.id.five_number);

        first_card.setOnClickListener(this);
        second_card.setOnClickListener(this);
        third_card.setOnClickListener(this);
        four_card.setOnClickListener(this);
        five_card.setOnClickListener(this);
        autoView();
    }

    /**
     * 动态适配
     */
    private void autoView() {
        AutoUtils.auto(first_card);
        AutoUtils.auto(second_card);
        AutoUtils.auto(third_card);
        AutoUtils.auto(four_card);
        AutoUtils.auto(five_card);
    }

    /**
     * 请求数据
     */
    private void initData() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                BoardBean boardBean = new Gson().fromJson(data, BoardBean.class);
                first_number.setText(boardBean.getData().getType10Counts() + "条帖子");
                second_number.setText(boardBean.getData().getType11Counts() + "条帖子");
                third_number.setText(boardBean.getData().getType12Counts() + "条帖子");
                four_number.setText(boardBean.getData().getType13Counts() + "条帖子");
                five_number.setText(boardBean.getData().getType14Counts() + "条帖子");

            }
        }.getDataForGet(getActivity(), UrlUtils.BOARD);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_card:
                break;
            case R.id.second_card:
                break;
            case R.id.third_card:
                break;
            case R.id.four_card:
                break;
            case R.id.five_card:
                break;
        }
    }
}
