package com.example.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.smalllemon.R;
import com.example.utils.CommonUtils;

import java.util.ArrayList;


/**
 * Created by asus on 2016/12/3.
 */
public class InfoView extends ViewPager {

    private ArrayList<String> imageList;
    private OnSingleItemListener onSingleItemListener;
    private OnInfoViewPageChangeListener onInfoViewPageChangeListener;
    private OnPageChangeListener listener;


    public void setInfoViewData(final ArrayList<String> imageList) {
        this.imageList = imageList;
        this.setAdapter(new MyPagerAdapter());
        super.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (listener != null)
                    listener.onPageScrolled(position % imageList.size(), positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (listener != null)
                    listener.onPageSelected(position % imageList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        this.listener = listener;
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view= CommonUtils.inflate(R.layout.home_vp_item);
            ImageView imageView = (ImageView) view.findViewById(R.id.home_vp_item_image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(imageList.get(position % imageList.size())).into(imageView);
            container.addView(view);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSingleItemListener != null)
                        onSingleItemListener.onSingleItemListener(position % imageList.size());
                }
            });
            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public InfoView(Context context) {
        super(context);
    }

    public InfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnInfoViewPageChangeListener(OnInfoViewPageChangeListener myInfoViewPageChangeListener) {
        this.onInfoViewPageChangeListener = myInfoViewPageChangeListener;
    }

    public void setOnSingleItemListener(OnSingleItemListener onSingleItemListener) {
        this.onSingleItemListener = onSingleItemListener;
    }

    public interface OnInfoViewPageChangeListener {
        void onInfoViewPageChangeListener(int position);
    }

    public interface OnSingleItemListener {
        void onSingleItemListener(int position);
    }
}
