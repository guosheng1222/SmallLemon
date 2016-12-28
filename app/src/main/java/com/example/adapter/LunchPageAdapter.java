package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.smalllemon.LunchActivity;
import com.example.smalllemon.MainActivity;
import com.example.smalllemon.R;

/**
 * @author : 张鸿鹏
 * @date : 2016/12/28.
 */
public class LunchPageAdapter extends PagerAdapter {
    private int[] pages;
    private Context context;

    public LunchPageAdapter(int[] pages, Context context) {
        this.pages = pages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = View.inflate(context, R.layout.launch_item, null);
        ImageView launch_iv = (ImageView) inflate.findViewById(R.id.launch_iv);
        ImageView launch_iv2 = (ImageView) inflate.findViewById(R.id.launch_iv2);
        Glide.with(context).load(pages[position]).into(launch_iv);
        for (int i = 0; i < pages.length; i++) {
            if (position == pages.length - 1) {
                launch_iv2.setVisibility(View.VISIBLE);
                launch_iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        LunchActivity activity = (LunchActivity) context;
                        activity.finish();
                    }
                });
            }
        }
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
