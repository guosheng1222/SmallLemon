package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smalllemon.R;
import com.example.utils.CommonUtils;

/**
 * Created by PC on 2017/1/3.
 */

public class HomeCommFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= CommonUtils.inflate(R.layout.home_community_fragment);
        return view;
    }
}
