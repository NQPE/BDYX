package com.custom.bdyx.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.custom.bdyx.R;
import com.custom.bdyx.adapter.Tab1FragAdapter;
import com.custom.bdyx.model.bean.SubstationVo;
import com.custom.bdyx.presenter.Tab1FragPresenter;
import com.custom.bdyx.ui.activity.DetailActivity;
import com.custom.bdyx.utils.LogUtil;
import com.custom.bdyx.view.ITab1FragView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by levi on 2016/7/22.
 */
public class Tab1Fragment extends SimpleListFragment<SubstationVo> implements ITab1FragView<List<SubstationVo>>{

    int page=1;
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initAdapter() {
        adapter=new Tab1FragAdapter(activity,new ArrayList<SubstationVo>());
    }

    @Override
    protected void initPresenterFirstLoad() {
        presenter=new Tab1FragPresenter(activity);
        presenter.attachView(this);
        ((Tab1FragPresenter)presenter).loadData(page);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    protected void onRefreshHandle() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        page = 1;
                        ((Tab1FragPresenter) presenter).loadData(page);
                    }
                });
    }

    @Override
    protected void onLoadMoreHandle() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        page++;
                        ((Tab1FragPresenter) presenter).loadData(page);
                    }
                });

    }


    @Override
    protected void onClickItem(int position, SubstationVo data) {
        activity.skip(DetailActivity.class,data.getId());
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragmen_tab1;
    }


    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadEnd() {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setIsLoading(false);
    }

    @Override
    public void onLoadError(String message) {

    }

    @Override
    public void onLoadNull() {

    }

    @Override
    public void onLoadData(List<SubstationVo> data) {

//        adapter.setNoMoreData();
    }

    @Override
    public void onLoadListData(List<SubstationVo> data, int page) {
        if (page==1){
            adapter.removeAll();
        }
        adapter.addItems(data);
        adapter.setCanLoadMore(true);
        if (page==3){
            adapter.setNoMoreData();
        }
    }
}
