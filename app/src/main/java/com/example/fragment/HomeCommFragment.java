package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.base.BaseData;
import com.example.bean.BeanEmotionState;
import com.example.smalllemon.R;
import com.example.utils.CommonUtils;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * Created by PC on 2017/1/3.
 */

public class HomeCommFragment extends Fragment {

    private RecyclerView myrecycler;
    ArrayList<BeanEmotionState.DataBean> list = new ArrayList<>();
    public static CommonAdapter<BeanEmotionState.DataBean> commonAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.inflate(R.layout.home_community_fragment);
        myrecycler = (RecyclerView) view.findViewById(R.id.home_community_recyclerView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int page = getArguments().getInt("page");
        if (MyApplication.CURRENT_USER.getEmotionStage() == 0) {
            onload("http://" + UrlUtils.single + "page=" + page);
        } else {
            onload("http://" + UrlUtils.loving + "page=" + page);
        }
    }

    public static Fragment getFragment(int page) {
        Fragment f1 = new HomeCommFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page + 1);
        f1.setArguments(bundle);
        return f1;
    }

    public void onload(String url) {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                Gson gson = new Gson();
                BeanEmotionState beanEmotionState = gson.fromJson(data, BeanEmotionState.class);
                list = (ArrayList<BeanEmotionState.DataBean>) beanEmotionState.getData();
                myrecycler.setLayoutManager(new LinearLayoutManager(getActivity()) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                myrecycler.setItemAnimator(new DefaultItemAnimator());
                commonAdapter = new CommonAdapter<BeanEmotionState.DataBean>(getActivity(), R.layout.home_community_item, list) {
                    @Override
                    protected void convert(ViewHolder holder, BeanEmotionState.DataBean dataBean, int position) {
                        holder.setText(R.id.home_community_item_text, list.get(position).getTitle());
                        ImageView imageView = holder.getView(R.id.home_community_item_image);
                        Glide.with(getActivity()).load(list.get(position).getImg()).into(imageView);
                    }
                };
                myrecycler.setAdapter(commonAdapter);
            }

            @Override
            public void onErrorData(String data) {

            }
        }.getDataForGet(getActivity(), url);
    }
}
