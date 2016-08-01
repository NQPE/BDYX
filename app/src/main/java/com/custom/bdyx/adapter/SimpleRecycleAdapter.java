package com.custom.bdyx.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.custom.bdyx.R;
import com.huaxi100.hxcommonlib.adapter.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by levi on 2016/7/25.
 */
public abstract class SimpleRecycleAdapter<E> extends BaseRecyclerAdapter<E>{
    public SimpleRecycleAdapter(Activity activity, Class rCls, List data, Class[] holderClass, int[] resId) {
        super(activity, rCls, data, holderClass, resId);
    }

    @Override
    public IShowLoadMoreView initFootView() {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        View footerView = inflater.inflate(R.layout.view_recycleadapter_footer, null, false);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(lp);
        setInitFooterView(footerView);
        return new IShowLoadMoreView() {
            @Override
            public void showLoadMoreData(View footview) {
                ((TextView)(footview.findViewById(R.id.tv_footer))).setText("正在加载");
                ((ProgressBar)(footview.findViewById(R.id.progress_footer))).setVisibility(View.VISIBLE);
            }

            @Override
            public void showNotMoreData(View footview) {
                ((TextView)(footview.findViewById(R.id.tv_footer))).setText("无更多数据");
                ((ProgressBar)(footview.findViewById(R.id.progress_footer))).setVisibility(View.GONE);
            }
        };
    }
}
