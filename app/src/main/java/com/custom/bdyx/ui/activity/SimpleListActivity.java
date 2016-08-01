package com.custom.bdyx.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.custom.bdyx.R;
import com.custom.bdyx.ui.fragment.BaseFragment;
import com.huaxi100.hxcommonlib.adapter.BaseRecyclerAdapter;
import com.huaxi100.hxcommonlib.presenter.BasePresenter;

import java.util.List;

/**
 * Created by levi on 2016/7/22.
 */
public abstract class SimpleListActivity<T> extends BaseActivity{
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;

    protected BaseRecyclerAdapter adapter = null;
    protected int currentPage = 1;
    public static int DATA_SIZE_LIMIT = 10; //每页list数量限制
    protected BasePresenter presenter = null;

    @Override
    public void doBusiness() {
        swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.srl_refresh);
        recyclerView = (RecyclerView) activity.findViewById(R.id.rl_list);
        init();
        initView();
    }

    private void initView() {
        initAdapter();
        swipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                        getResources().getDisplayMetrics()));
        swipeRefreshLayout.setColorSchemeResources(R.color.detail_liked);
        if (getLayoutManager() == null) {
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(mLinearLayoutManager);
        } else {
            recyclerView.setLayoutManager(getLayoutManager());
        }
        if (getItemDecoration() != null) {
            recyclerView.addItemDecoration(getItemDecoration());
        }
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            adapter.setLoadMoreListener(recyclerView, new BaseRecyclerAdapter.OnLoadMoreListener(adapter) {
                @Override
                public void onLoadMore() {
                    onLoadMoreHandle();
                }
            });
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<T>() {
                @Override
                public void onItemClick(int position, T data) {
                    onClickItem(position, data);
                }
            });
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                onRefreshHandle();
            }
        });
        initPresenterFirstLoad();
    }


    /**
     * 更多的初始化操作
     */
    protected abstract void init();

    /**
     * 初始化Adapter
     */
    protected abstract void initAdapter();

    /**
     * 初始化presenter并且进行第一次加载
     */
    protected abstract void initPresenterFirstLoad();

    /**
     * 添加ItemDecoration
     *
     * @return
     */
    protected abstract RecyclerView.ItemDecoration getItemDecoration();


    /**
     * 刷新操作
     */
    protected abstract void onRefreshHandle();

    /**
     * 加载更多操作
     */
    protected abstract void onLoadMoreHandle();

    /**
     * 点击事件
     * @param position
     * @param data
     */
    protected abstract void onClickItem(int position, T data);


    /**
     * 设置LayoutManager
     *
     * @return
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager();


    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    protected RecyclerView getRecyclerView() {
        return recyclerView;
    }

    protected BaseRecyclerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    //---处理通用事务----

    protected void doLoadStart() {
        if (currentPage != 1 && adapter.isCanLoadMore()) {
            adapter.showFooter();
        } else {
            adapter.hideFooter();
        }
    }

    protected void doLoadEnd() {
        swipeRefreshLayout.setRefreshing(false);
//        activity.dismissDialog();
        adapter.setIsLoading(false);
        if (currentPage != 1 && adapter.isCanLoadMore()) {
            adapter.loadFinish();
        }
    }

    protected void doLoadError() {
        if (currentPage > 1) {
            currentPage = --currentPage; //处理失败，页数-1
        } else {
            currentPage = 1;
        }
        adapter.loadFinish();
    }

    protected void doRefresh(int page, List<T> dataList) {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        currentPage = page;
        adapter.removeAll();
        adapter.addItems(dataList);
        if (dataList.size() < DATA_SIZE_LIMIT) {
            adapter.setNoMoreData();
        }
    }

    protected void doLoadMore(int page, List<T> dataList) {
        currentPage = page;
        adapter.addItems(dataList);
    }
}
