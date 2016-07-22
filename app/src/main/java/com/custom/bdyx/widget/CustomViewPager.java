package com.custom.bdyx.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Levi on 2015/11/26.
 */
public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = true;//true 可以滑动  false 不可以滑动

    public CustomViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isCanScroll) {
            return super.onTouchEvent(arg0);
        }else{
            return false;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isCanScroll) {
            return super.onInterceptTouchEvent(arg0);
        }else {                                           //不允许滑动
            return false;
        }
    }
}
