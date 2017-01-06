package com.example.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.BaseHolder;
import com.example.smalllemon.R;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/4.
 */

public class BoardTopHolder extends BaseHolder {

    public RecyclerView top_recycleView;

    public BoardTopHolder(View itemView) {
        super(itemView);
        top_recycleView = (RecyclerView) itemView.findViewById(R.id.top_recycleView);
    }
}
