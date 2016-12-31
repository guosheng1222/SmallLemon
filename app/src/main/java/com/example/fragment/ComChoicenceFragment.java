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
 * @author :   郗琛
 * @date :   2016/12/30
 */

public class ComChoicenceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.inflate(R.layout.fragment_com_choicence);
        return view;
    }
}
