package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.BeanCO2;
import com.example.smalllemon.R;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/1/6.
 */

public class HomeCO2Adapter extends RecyclerView.Adapter<HomeCO2Adapter.ViewHolder>{
    ArrayList<BeanCO2.DataBean> list;
    Context context;

    public HomeCO2Adapter(ArrayList<BeanCO2.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_loving_recycler, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.time.setText("—  "+(position==0?list.get(0).getStartTime().substring(6,10):list.get(list.size()-1).getStartTime().substring(6,10))+"  —");
        holder.recycler.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        final List<BeanCO2.DataBean> beanList = position == 0 ? list.subList(0, 3) : list.subList(3, list.size());
        holder.recycler.setAdapter(new CommonAdapter<BeanCO2.DataBean>(context,R.layout.home_loving_item,beanList) {
            @Override
            protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, BeanCO2.DataBean dataBean, int p) {
                ImageView image = holder.getView(R.id.home_loving_item_image);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(beanList.get(p).getImg()).into(image);
                holder.setText(R.id.home_loving_item_title,beanList.get(p).getTitle());
                holder.setText(R.id.home_loving_item_contentIntr,beanList.get(p).getContentIntr().substring(0,23)+"...");
                holder.setText(R.id.home_loving_item_reportName,"作者:"+beanList.get(p).getReporterName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final RecyclerView recycler;

        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.home_loving_time);
            recycler = (RecyclerView) itemView.findViewById(R.id.home_loving_recycle);
        }
    }
}
