package com.example.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.interfaces.OnItemClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * recycler的万能适配器
 *
 * @author :   郗琛
 * @date :   2016/12/4
 * Created by 橘子桑 on 2016/1/2.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
    }


    public RecyclerAdapter(Context mContext, T[] mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mDatas = new ArrayList<>();
        for (int i = 0; i < mDatas.length; i++) {
            this.mDatas.add(mDatas[i]);
        }
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    public RecyclerAdapter() {

    }

    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里是创建ViewHolder的地方，RecyclerAdapter内部已经实现了ViewHolder的重用
        //这里我们直接new就好了
        return new RecyclerHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.RecyclerHolder holder, final int position) {
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(holder.getItemView(), position);
            }
        });
        convert(holder, mDatas.get(position), position);
    }

    public abstract void convert(RecyclerHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private OnItemClickListener onItemClickListener;

    /**
     * 添加item点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        /**
         * 用于存储当前item当中的View
         */
        private SparseArray<View> mViews;
        private View itemView;

        public View getItemView() {
            return itemView;
        }

        public RecyclerHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            AutoUtils.autoSize(itemView);
            mViews = new SparseArray<View>();
        }

        /**
         * 查找摸个控件
         *
         * @param ViewId
         * @param <T>
         * @return
         */
        public <T extends View> T findView(int ViewId) {
            View view = mViews.get(ViewId);
            //集合中没有，则从item当中获取，并存入集合当中
            if (view == null) {
                view = itemView.findViewById(ViewId);
                mViews.put(ViewId, view);
            }
            return (T) view;
        }

        /**
         * 设置文本
         *
         * @param viewId
         * @param text
         * @return
         */
        public RecyclerHolder setText(int viewId, String text) {
            TextView tv = findView(viewId);
            tv.setText(text);
            return this;
        }

        /**
         * 设置textView删除线
         *
         * @param viewId
         * @return
         */
        public RecyclerHolder setFlags(int viewId) {
            TextView tv = findView(viewId);
            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            return this;
        }


        /**
         * 设置image资源路径
         *
         * @param viewId
         * @param ImageId
         * @return
         */
        public RecyclerHolder setImageResource(int viewId, int ImageId) {
            ImageView image = findView(viewId);
            image.setImageResource(ImageId);
            return this;
        }


        /**
         * 设置imageBitmap图像
         *
         * @param viewId
         * @param bitmap
         * @return
         */
        public RecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView image = findView(viewId);
            image.setImageBitmap(bitmap);
            return this;
        }

        /**
         * 直接设置image图片
         *
         * @param viewId
         * @param url
         * @return
         */
        public RecyclerHolder setImageNet(int viewId, String url) {
            ImageView image = findView(viewId);
            //使用你所用的网络框架等
            Glide.with(mContext).load(url).into(image);
            return this;
        }

        /**
         * 设置textView背景
         *
         * @param viewId
         * @param color
         * @return
         */
        public RecyclerHolder setTextBackground(int viewId, int color) {
            TextView tv = findView(viewId);
            tv.setBackgroundResource(color);
            return this;
        }

        /**
         * 设置view背景颜色
         *
         * @param viewId
         * @param color
         * @return
         */
        public RecyclerHolder setViewBackgroundColor(int viewId, int color) {
            View tv = findView(viewId);
            tv.setBackgroundColor(color);
            return this;
        }
    }
}
