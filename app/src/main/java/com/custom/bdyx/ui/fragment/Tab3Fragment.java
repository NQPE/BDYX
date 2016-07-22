package com.custom.bdyx.ui.fragment;

import android.view.View;

import com.custom.bdyx.R;

/**
 * Created by levi on 2016/7/22.
 */
public class Tab3Fragment extends BaseFragment{
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void doBusiness(View view) {

    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragmen_tab3;
    }
}
