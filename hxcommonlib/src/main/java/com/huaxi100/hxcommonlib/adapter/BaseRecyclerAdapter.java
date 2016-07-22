package com.huaxi100.hxcommonlib.adapter;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huaxi100.hxcommonlib.utils.Utils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by hx100 on 2016/5/12.
 */
public abstract class BaseRecyclerAdapter<E> extends RecyclerView.Adapter {
    public static final int TYPE_HEADER = -1;

    public static final int TYPE_FOOTER = -2;

    private Class<?> holderClass[];

    protected List<E> data;

    protected Activity activity;

    private int resId[];

    private Class<?> rCls;

    private View headerView;
    private View footerView;

    private OnItemClickListener listener;

    private boolean isLoading = false;

    private int headerCount = 0;
    private int bottomCount = 0;
    private boolean canLoadMore = true;

    private TextView footerTips;
    private ProgressBar progressBar;
    private boolean isCustomLoadMore;
    private IShowLoadMoreView iShowLoadMoreView;
    private IShowLoadMoreView iInitShowLoadMoreView;

    public BaseRecyclerAdapter(Activity activity, Class<?> rCls, List<E> data, Class<?>[] holderClass, int[] resId) {
        this.activity = activity;
        this.data = data;
        this.resId = resId;
        this.rCls = rCls;
        this.holderClass = holderClass;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        iInitShowLoadMoreView=initFootView(footerView);
//        this.footerView = inflater.inflate(R.layout.recycleview_footer, null, false);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        this.footerView.setLayoutParams(lp);
//        footerTips = (TextView) footerView.findViewById(R.id.tv_footer);
//        progressBar = (GoogleProgressBar) footerView.findViewById(R.id.google_progress);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public abstract IShowLoadMoreView initFootView(View footerView);

    public void addHeader(View header) {
        this.headerView = header;
        headerCount = 1;
        notifyItemInserted(0);
    }

    public void removeHeader() {
        if (headerCount==1){
            this.headerView=null;
            headerCount = 0;
            notifyItemRemoved(0);
        }
    }

    public void addFooter(View footerView, IShowLoadMoreView iShowLoadMoreView) {
        isCustomLoadMore = true;
        this.footerView = footerView;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.footerView.setLayoutParams(lp);
        this.iShowLoadMoreView = iShowLoadMoreView;
    }


    /**
     */
    public void setNoMoreData() {
        bottomCount = 1;
        setCanLoadMore(false);
        setIsLoading(false);
        if (isCustomLoadMore) {
            iShowLoadMoreView.showNotMoreData(footerView);
        } else {
            iInitShowLoadMoreView.showNotMoreData(footerView);
        }
        notifyDataSetChanged();
    }

    /**
     */
    public void setNoMoreDataHideFooter() {
        bottomCount = 0;
        setIsLoading(false);
        setCanLoadMore(false);
        notifyDataSetChanged();
    }

    public void hideFooter() {
        bottomCount = 0;
        setIsLoading(false);
        setCanLoadMore(true);
        notifyDataSetChanged();
    }

    public void loadFinish() {
        hideFooter();
    }

    public void showFooter() {
        bottomCount = 1;
        setCanLoadMore(true);
        if (isCustomLoadMore) {
            iShowLoadMoreView.showLoadMoreData(footerView);
        } else {
            iInitShowLoadMoreView.showLoadMoreData(footerView);
        }
        notifyDataSetChanged();
    }

    public View getHeaderView() {
        return headerView;
    }

    public View getFooterView() {
        return footerView;
    }

    /**
     *
     * @param recyclerView
     * @param onLoadMoreListener
     */
    public void setLoadMoreListener(RecyclerView recyclerView, OnLoadMoreListener onLoadMoreListener) {
        recyclerView.addOnScrollListener(onLoadMoreListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(headerView);
        }
        if (viewType == TYPE_FOOTER) {
            return new FooterHolder(footerView);
        }
        int resLength = resId.length;
        if (viewType > 4 || resLength > 5) {
            throw new RuntimeException("holder number is outlength");
        }

        if (viewType == 0) {
            return buildHolder(makeView(resId[0], parent), 0);
        } else if (viewType == 1) {
            return buildHolder(makeView(resId[1], parent), 1);
        } else if (viewType == 2) {
            return buildHolder(makeView(resId[2], parent), 2);
        } else if (viewType == 3) {
            return buildHolder(makeView(resId[3], parent), 3);
        } else if (viewType == 4) {
            return buildHolder(makeView(resId[4], parent), 4);
        }
        return buildHolder(makeView(resId[0], parent), 0);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderHolder) {
//            bindData(holder, null, TYPE_HEADER);
            return;
        } else if (holder instanceof FooterHolder) {
//            bindData(holder, null, TYPE_FOOTER);
            return;
        } else {
            bindData(holder, data.get(position - headerCount), position - headerCount);
            if (listener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(position - headerCount, data.get(position - headerCount));
                    }
                });
            }
        }
    }

    public abstract void bindData(RecyclerView.ViewHolder holder, E item, int position);

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    private RecyclerView.ViewHolder buildHolder(View convertView, int position) {
        Object holder = null;
        try {
            holder = holderClass[position].getConstructors()[0].newInstance(convertView);

            for (Field f : holderClass[position].getDeclaredFields()) {
                String name = f.getName();
                f.setAccessible(true);
                f.set(holder, convertView.findViewById(getId(name, rCls)));
            }
        } catch (Exception e) {
            throw new RuntimeException("holder is not illeage" + e);
        }
        return (RecyclerView.ViewHolder) holder;
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = itemSize();
        if (headerCount != 0 && position < headerCount) {
            return TYPE_HEADER;
        } else if (bottomCount != 0 && position >= (headerCount + dataItemCount)) {
            return TYPE_FOOTER;
        } else {
//            E e = getItem(position - headerCount);
//            return e.getViewType();
            return getListItemViewType(position - headerCount);
        }
    }

    public abstract int getListItemViewType(int position);

    @Override
    public int getItemCount() {
        return data.size() + bottomCount + headerCount;
    }

    public int itemSize() {
        return data.size();
    }

    public boolean isHeaderView(int position) {
        return headerCount != 0 && position < headerCount;
    }

    public boolean isBottomView(int position) {
        return bottomCount != 0 && position >= (headerCount + itemSize());
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            if (getItemViewType(holder.getLayoutPosition()) == TYPE_HEADER || getItemViewType(holder.getLayoutPosition()) == TYPE_FOOTER) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }


    public void addItems(List<E> extras) {
        if (Utils.isEmpty(extras)) {
            return;
        }
        data.addAll(data.size(), extras);
        notifyDataSetChanged();
    }

    public void addItems(List<E> extras, int local) {
        if (Utils.isEmpty(extras)) {
            return;
        }
        data.addAll(local, extras);
        notifyDataSetChanged();
    }

    public void addItem(E extras, int local) {
        data.add(local, extras);
        notifyDataSetChanged();
    }

    public void reload(List<E> newData) {
        data.clear();
        addItems(newData);
    }

    public void refresh() {
        if (!Utils.isEmpty(data)) {
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public E getItem(int position) {
        if (!Utils.isEmpty(data)) {
            return data.get(position);
        }
        return null;
    }

    public void removeAll() {
        data.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener<E> {
        /**
         *
         * @param position
         * @param data
         */
        void onItemClick(int position, E data);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public boolean isCanLoadMore() {
        return canLoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    public interface IShowLoadMoreView {
        void showLoadMoreData(View footview);

        void showNotMoreData(View footview);
    }


    public static abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {


        private BaseRecyclerAdapter adapter;

        private boolean mFirstEnter = true;

        public OnLoadMoreListener(BaseRecyclerAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (!canScrollDown(recyclerView)) {
                if (!adapter.isLoading() && !mFirstEnter && adapter.isCanLoadMore()) {
                    adapter.showFooter();
                    adapter.setIsLoading(true);
                    onLoadMore();
                }
            }
            if (mFirstEnter) {
                mFirstEnter = false;
            }
        }

        private boolean canScrollDown(RecyclerView recyclerView) {
            return ViewCompat.canScrollVertically(recyclerView, 1);
        }

        public abstract void onLoadMore();
    }

    private View makeView(int resId, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    private int getId(String name, Class<?> cls) {
        try {
//			Class<R.id> cls = R.id.class;
            Field f = cls.getDeclaredField(name);
            f.setAccessible(true);
            return f.getInt(cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
