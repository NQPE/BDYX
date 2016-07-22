package com.custom.bdyx.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.custom.bdyx.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 图片轮播
 *
 */
public class AutoScrollViewPager extends FrameLayout{
    //初始化图片轮播所需控件
    private LinearLayout lpointGroup;// 小圆点
    private ViewPager lADViewPager;// 顶部自动横滑广告viewpager
    private List<ImageView> ladImageView;
    private int lcurrentItem;// 当前顶部 广告 指针
    private Context context;
    private MyPagerAdapter adapter;
    private int default_img_resid;
    private Activity activity;
    private boolean is_auto_switch;
    private boolean is_touch_switch;//手势滑动时记录 方便停止刷新自动轮播
    private AdSwitchTask adSwitchTask ;
    private long autoSwitchTime;
    private boolean turning;
    //外部回调接口
    public OnViewPagerClick onViewPagerClick;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;
    //Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            lADViewPager.setCurrentItem(lcurrentItem);
        }

    };

    public AutoScrollViewPager(Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init() {
        View view = (View) LayoutInflater.from(context).inflate(R.layout.widget_autoscrollviewpager, this);
        lpointGroup = (LinearLayout) view.findViewById(R.id.index_viewgroup);
        lADViewPager = (ViewPager) view.findViewById(R.id.index_viewpager);
    }

    public int getDefault_img_resid() {
        return default_img_resid;
    }

    /**
     * 设置是否显示提示的小圆点
     *
     * @param visible
     */
    public void setVisibilityPointGroup(boolean visible){
        if (visible){
            lpointGroup.setVisibility(VISIBLE);
        }else {
            lpointGroup.setVisibility(GONE);
        }
    }
    /**
     * 设置默认加载图片的resid
     * @param default_img_resid
     */
    public void setDefault_img_resid(int default_img_resid) {
        this.default_img_resid = default_img_resid;
    }

    public ViewPager getViewPager() {
        return lADViewPager;
    }

    /**
     * 加载图片轮播
     *
     *
     */
    public void loadAutoScrollViewPager(Activity activity, List<String> urls) {
        this.activity=activity;
//        int width = AppUtils.getWidth(activity);
//        int height = 400 * width / 720;
        lpointGroup.removeAllViews();
//        lADViewPager.setOffscreenPageLimit(urls.size());
        LayoutParams lp = (LayoutParams) lADViewPager.getLayoutParams();
//        lp.width=width;
//        lp.height=height;
        lADViewPager.setLayoutParams(lp);
        lADViewPager.removeAllViews();
        ladImageView = new ArrayList<ImageView>();
        // 加载图片资源
        for (int i = 0, len = urls.size(); i < len; i++) {
            String url= urls.get(i);
            ImageView imageView = new ImageView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            
//            params.height = height;
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(activity.getApplicationContext()).load(url).centerCrop().placeholder(default_img_resid).into(imageView);
            ladImageView.add(imageView);
            // 添加指示小圆点
            ImageView point = new ImageView(activity);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params2.rightMargin = 15;
//            params2.height = 24;
//            params2.width = 24;
            point.setLayoutParams(params2);
            // 如果显示的是第一页，则将第一个指示点点亮，其他的指示点为默认状态
            if (i == 0) {
                point.setImageResource(R.drawable.ic_page_indicator_focused);
            } else {
                point.setImageResource(R.drawable.ic_page_indicator1);
            }
            lpointGroup.addView(point);
        }
//        if (ladImageView.size()>2){
//            lADViewPager.setOffscreenPageLimit(ladImageView.size());
//        }
        adapter = new MyPagerAdapter(ladImageView);
        lADViewPager
                .setAdapter(adapter);
        lADViewPager
                .addOnPageChangeListener(new MyOnPageChangeListener());
        //设置一开始viewpage就出现在最大页数的一半
        int n = Integer.MAX_VALUE / 2 % ladImageView.size();
        lcurrentItem = Integer.MAX_VALUE/2-n;
        lADViewPager.setCurrentItem(lcurrentItem);
    }

    public LinearLayout getLpointGroup() {
        return lpointGroup;
    }

    //图片轮播的动作逻辑
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    turning=true;
                    is_touch_switch=true;
                    cancelAutoSwitchViewPager();
//                    LogUtil.i("手势滑动");
                    break;
                case 2:// 界面切换中
                    turning=true;
//                    LogUtil.i("界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
//                    LogUtil.i("滑动结束");
                    turning=false;
                    restartAutoSwitchViewPager();
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            // 改变指示点的状态
            for (int i = 0, len = ladImageView.size(); i < len; i++) {
                ImageView imgPoint = (ImageView) lpointGroup.getChildAt(i);
                if (i == (arg0%ladImageView.size())) {// 当前小圆点
                    imgPoint.setImageResource(R.drawable.ic_page_indicator_focused);
                    lcurrentItem = arg0;
                } else {// 设置没有在当前页面的指示点
                    imgPoint.setImageResource(R.drawable.ic_page_indicator1);
                }
            }
        }

    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (lADViewPager) {
                lcurrentItem++;
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    /**
     * 当手势滑动ViewPager时取消自动加载
     */
    private void cancelAutoSwitchViewPager(){
        if (is_auto_switch&&adSwitchTask!=null){
            removeCallbacks(adSwitchTask);
        }
    }

    /**
     * 当手势滑动结束时重新加载
     */
    private void restartAutoSwitchViewPager(){
        if (is_auto_switch&&is_touch_switch&&adSwitchTask!=null){
            postDelayed(adSwitchTask, autoSwitchTime);
            is_touch_switch=false;
        }
    }
    /**
     * 开始自动轮播
     *
     * @param seconds 多少毫秒切换一次 单位：毫秒
     */
    public void startAutoSwitchViewPager(long seconds){
        adSwitchTask = new AdSwitchTask(this);
        if (turning){
            removeCallbacks(adSwitchTask);
        }
        this.autoSwitchTime=seconds;
        this.is_auto_switch=true;
        postDelayed(adSwitchTask, autoSwitchTime);
    }

    /**
     * 停止自动轮播
     *
     */
    public void stopAutoSwitchViewPager(){
        this.is_auto_switch=false;
        if (adSwitchTask!=null){
            removeCallbacks(adSwitchTask);
        }
    }

    static class AdSwitchTask implements Runnable {

        private final WeakReference<AutoScrollViewPager> reference;

        AdSwitchTask(AutoScrollViewPager autoScrollViewPager) {
            this.reference = new WeakReference<AutoScrollViewPager>(autoScrollViewPager);
        }

        @Override
        public void run() {
            AutoScrollViewPager autoScrollViewPager = reference.get();

            if(autoScrollViewPager != null){
                if (autoScrollViewPager.lADViewPager != null && autoScrollViewPager.is_auto_switch) {
                    autoScrollViewPager.lcurrentItem++;
                    autoScrollViewPager.lADViewPager.setCurrentItem(autoScrollViewPager.lcurrentItem);
                    autoScrollViewPager.postDelayed(autoScrollViewPager.adSwitchTask, autoScrollViewPager.autoSwitchTime);
                }
            }
        }
    }


//    /**
//     * 开始自动轮播
//     *
//     * @param seconds 多少秒切换一次 单位：秒
//     */
//    public void startAutoScrollViewPager(long seconds) {
//        if (scheduledExecutorService == null) {
//            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//            scheduledExecutorService.scheduleWithFixedDelay(new SlideShowTask(), 0, seconds, TimeUnit.SECONDS);
//            return;
//        } else {
//            return;
//        }
//    }

    /**
     * 填充ViewPager的页面适配器
     * Created by Levi on 2015/9/21.
     */
    public class MyPagerAdapter extends PagerAdapter {
        private List<ImageView> limgList;

        public MyPagerAdapter(List<ImageView> limgList) {
            this.limgList = limgList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
//            return limgList.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        public Object instantiateItem(ViewGroup container, final int index) {
            final int position=index%limgList.size();
            limgList.get(position).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onViewPagerClick != null) {
                        onViewPagerClick.onViewPagerClick(v, position);
                    }
                }
            });

            ImageView iv=limgList.get(position);
            ViewParent vp =iv.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(iv);
            }
            container.addView(iv);
            return iv;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//            object = null;
        }
    }

    //接口注册函数
    public void setOnViewPagerClick(OnViewPagerClick onViewPagerClick) {
        this.onViewPagerClick = onViewPagerClick;
    }

    //外部回调接口
    public interface OnViewPagerClick {
        void onViewPagerClick(View view, int position);
    }
}
