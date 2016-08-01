package com.custom.bdyx.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.custom.bdyx.R;
import com.custom.bdyx.model.bean.SubstationVo;
import com.custom.bdyx.utils.SimpleUtils;

import java.util.List;

/**
 * Created by levi on 2016/7/26.
 */
public class Tab1FragAdapter extends SimpleRecycleAdapter<SubstationVo>{
    public Tab1FragAdapter(Activity activity,  List data) {
        super(activity,  R.id.class, data,  new Class[]{ViewHolder1.class}, new int[]{R.layout.item_tab1});
    }


    @Override
    public void bindData(RecyclerView.ViewHolder h, SubstationVo item, int position) {
        if (h instanceof ViewHolder1){
            ViewHolder1 holder= (ViewHolder1) h;
            SimpleUtils.showGlideView(item.getThumb(),holder.iv_item,0);
            holder.tv_item.setText(item.getName());
        }
    }

    @Override
    public int getListItemViewType(int position) {
        return 0;
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        RelativeLayout rl_item;
        ImageView iv_item;
        TextView tv_item;

        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }
}
