package com.custom.bdyx.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.custom.bdyx.R;

/**
 * Created by levi on 2016/7/22.
 */
public class Tab1Fragment extends SimpleListFragment{
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initPresenterFirstLoad() {

    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    protected void onRefreshHandle() {

    }

    @Override
    protected void onLoadMoreHandle() {

    }

    @Override
    protected void onClickItem(int position, Object data) {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragmen_tab1;
    }
}
