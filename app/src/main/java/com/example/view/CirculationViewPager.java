package com.example.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :   郗琛
 * @date :   2016/12/2
 */

public class CirculationViewPager extends ViewPager {
    private final int Circulation_ZERO = 0;
    private final int CHANGE_TIME = 3000;
    private ArrayList<String> imageList;
    private int currentPosition;
    private OnMyPageChangeListener myPageChangeListener;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Circulation_ZERO) {
                CirculationViewPager.this.setCurrentItemToNext();
                handler.sendEmptyMessageDelayed(Circulation_ZERO, CHANGE_TIME);            //下一个
            }
        }
    };
    private OnSingleItemListener onSingleItemListener;
    private double lastTime;


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            handler.sendEmptyMessageDelayed(Circulation_ZERO, CHANGE_TIME);     //显示发送
        } else {
            handler.removeCallbacksAndMessages(null);          //隐藏不发送
        }
    }

    /**
     * 设置下一个图片
     */
    private void setCurrentItemToNext() {
        int currentItem = this.getCurrentItem();
        currentItem++;
        if (currentItem == 0)
            currentPosition = imageList.size() - 2;
        else if (currentItem == imageList.size() - 1)
            currentPosition = 1;
        else
            currentPosition = currentItem;
        this.setCurrentItem(currentItem);
    }


    public void setData(ArrayList<String> imageList) {
        handler.sendEmptyMessageDelayed(Circulation_ZERO, CHANGE_TIME);         //初始化开启
        this.imageList = imageList;
        initView();
    }


    private void initView() {
        imageList.add(imageList.get(0));
        imageList.add(0, imageList.get(imageList.size() - 2));
        this.setAdapter(new MyPagerAdapter());
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (myPageChangeListener != null)
                    myPageChangeListener.onMyPageChangeListener((currentPosition - 1) % (imageList.size() - 1));
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    currentPosition = imageList.size() - 2;
                else if (position == imageList.size() - 1)
                    currentPosition = 1;
                else
                    currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    CirculationViewPager.this.setCurrentItem(currentPosition, false);
                }

            }
        });
        CirculationViewPager.this.setCurrentItem(1);

    }


    public class MyPagerAdapter extends PagerAdapter {
        private Map<Integer, ImageView> imageViewMap;

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (imageViewMap == null) {
                initImageViewMap();
            }
            ImageView circulationImage = imageViewMap.get(position);
            circulationImage.setOnTouchListener(new OnTouchListener() {
                private float currentY;
                private float currentX;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            currentX = event.getX();
                            currentY = event.getY();
                            lastTime = System.currentTimeMillis();
                            handler.removeCallbacksAndMessages(null);       //按下不发送
                            break;
                        case MotionEvent.ACTION_UP:
                            if (currentX == event.getX() && currentY == event.getY() && System.currentTimeMillis() < lastTime + 1000) {
                                if (onSingleItemListener != null) {
                                    onSingleItemListener.onSingleItemListener((currentPosition - 1) % (imageList.size() - 1));
                                }
                            }
                        case MotionEvent.ACTION_CANCEL:
                            handler.sendEmptyMessageDelayed(Circulation_ZERO, CHANGE_TIME);         //抬起发送
                            break;
                    }
                    return true;
                }
            });
            container.addView(circulationImage);
            return circulationImage;
        }

        private void initImageViewMap() {
            imageViewMap = new HashMap<>();
            for (int i = 0; i < imageList.size(); i++) {
                ImageView circulationImage = new ImageView(getContext());
                Glide.with(getContext()).load(imageList.get(i)).into(circulationImage);
                imageViewMap.put(i, circulationImage);
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }


    public CirculationViewPager(Context context) {
        super(context);
    }

    public CirculationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置点击监听
     */
    public void setOnMyPageChangeListener(OnMyPageChangeListener myPageChangeListener) {
        this.myPageChangeListener = myPageChangeListener;
    }

    /**
     * 设置viewPager滑动监听
     */

    public void setOnSingleItemListener(OnSingleItemListener onSingleItemListener) {
        this.onSingleItemListener = onSingleItemListener;
    }

    public interface OnMyPageChangeListener {
        void onMyPageChangeListener(int position);
    }

    public interface OnSingleItemListener {
        void onSingleItemListener(int position);
    }

}