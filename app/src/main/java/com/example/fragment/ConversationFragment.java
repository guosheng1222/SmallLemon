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
 * Created by lenovo on 2017/1/4.
 */

public class ConversationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = CommonUtils.inflate(R.layout.fragment_conversation);

        return view;
    }
}
