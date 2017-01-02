package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.BaseData;
import com.example.bean.HomeRadioStation;
import com.example.smalllemon.R;
import com.example.utils.UrlUtils;
import com.example.view.InfoView;
import com.example.view.RotateDownPageTransformer;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/12/28.
 */

public class HomePageFragment extends Fragment {

    private InfoView infoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home_page, null);
        initInfoView(view);
        return view;
    }

    private void initInfoView(View view) {
        infoView = (InfoView) view.findViewById(R.id.home_vp);
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                HomeRadioStation homeRadioStation = new Gson().fromJson(data, HomeRadioStation.class);
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < homeRadioStation.getData().size(); i++) {
                    String substring = homeRadioStation.getData().get(i).getImg().substring(0, homeRadioStation.getData().get(i).getImg().indexOf("#"));
                    strings.add(substring);
                }
                if (strings.size() == 0)
                    return;
                infoView.setInfoViewData(strings);
                infoView.setOffscreenPageLimit(2);
                infoView.setPageTransformer(false, new RotateDownPageTransformer());
                infoView.setOnSingleItemListener(new InfoView.OnSingleItemListener() {
                    @Override
                    public void onSingleItemListener(int position) {
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                intent.putExtra("url", indexBean.getData().getActivityInfo().getActivityInfoList().get(position).getActivityImg());
//                getActivity().startActivity(intent);
                    }
                });
            }
        }.getDataForGet(getActivity(), UrlUtils.main_viewager, BaseData.NO_TIME);

    }
}
