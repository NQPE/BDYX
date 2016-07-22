package com.custom.bdyx.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.custom.bdyx.R;
import com.custom.bdyx.ui.fragment.Tab1Fragment;
import com.custom.bdyx.ui.fragment.Tab2Fragment;
import com.custom.bdyx.ui.fragment.Tab3Fragment;
import com.custom.bdyx.widget.CustomViewPager;
import com.huaxi100.hxcommonlib.app.AppManager;
import com.huaxi100.hxcommonlib.utils.AppUtils;
import com.huaxi100.hxcommonlib.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.vp_main)
    CustomViewPager vp_main;

    @Bind(R.id.ll_tab1)
    LinearLayout ll_tab1;

    @Bind(R.id.iv_tab1)
    ImageView iv_tab1;

    @Bind(R.id.tv_tab1)
    TextView tv_tab1;

    @Bind(R.id.ll_tab2)
    LinearLayout ll_tab2;

    @Bind(R.id.iv_tab2)
    ImageView iv_tab2;

    @Bind(R.id.tv_tab2)
    TextView tv_tab2;

    @Bind(R.id.ll_tab3)
    LinearLayout ll_tab3;

    @Bind(R.id.iv_tab3)
    ImageView iv_tab3;

    @Bind(R.id.tv_tab3)
    TextView tv_tab3;

    private List<Fragment> fragLsit;

    private LinearLayout[] ll_tabs;
    private TextView[] tv_tabs;
    private ImageView[] iv_tabs;

    private int[] iv_resid_normal;
    private int[] iv_resid_select;

    private int mSelectIndex = -1;

    @Override
    public void doBusiness() {
        initViews();
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    /**
     * 初始化页面装载器 viewpager 的fragment 对象
     * <p/>
     * 以及点击选择改变颜色的逻辑处理
     */
    private void initViews() {

        fragLsit = new ArrayList<Fragment>();
        fragLsit.add(new Tab1Fragment());
        fragLsit.add(new Tab2Fragment());
        fragLsit.add(new Tab3Fragment());

        ll_tabs = new LinearLayout[]{ll_tab1, ll_tab2, ll_tab3};
        tv_tabs = new TextView[]{tv_tab1, tv_tab2, tv_tab3};
        iv_tabs = new ImageView[]{iv_tab1, iv_tab2, iv_tab3};
        iv_resid_normal = new int[]{R.drawable.ic_tab_food_n, R.drawable.ic_tab_find_n, R.drawable.ic_tab_me_n};
        iv_resid_select = new int[]{R.drawable.ic_tab_food_p, R.drawable.ic_tab_find_p, R.drawable.ic_tab_me_p};
        for (LinearLayout ll : ll_tabs) {
            ll.setOnClickListener(this);
        }
//        vp_main.setOffscreenPageLimit(fragLsit.size());
        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return fragLsit.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return fragLsit.get(arg0);
            }

        });
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectMainBar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //初始化第一个
        selectMainBar(0);
    }

    /**
     * 改变选择mainBar的颜色状态
     *
     * @param position
     */
    private void selectMainBar(int position) {
        if (position == mSelectIndex) {
            return;
        }
        //将所有的tab都设置为正常状态
        setTabsNormal();
        //设置选择状态的TAB
        setSelectTab(position);
    }

    /**
     * 设置选择状态的TAB
     *
     * @param position
     */
    private void setSelectTab(int position) {
        mSelectIndex = position;
        tv_tabs[position].setTextColor(Color.parseColor("#f06240"));
        iv_tabs[position].setImageResource(iv_resid_select[position]);
        vp_main.setCurrentItem(position);
    }

    /**
     * 将所有的tab都设置为正常状态
     */
    private void setTabsNormal() {
        for (int i = 0; i < 3; i++) {
            tv_tabs[i].setTextColor(Color.parseColor("#333333"));
            iv_tabs[i].setImageResource(iv_resid_normal[i]);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_tab1:
                selectMainBar(0);
                break;
            case R.id.ll_tab2:
                selectMainBar(1);
                break;
            case R.id.ll_tab3:
                selectMainBar(2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!AppUtils.isClickAgain()) {
            activity.toast("再按一次退出应用");
            return;
        }
        AppManager.getAppManager().finishAllActivity();
        super.onBackPressed();
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }
}
