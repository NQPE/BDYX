package com.custom.bdyx.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.custom.bdyx.R;
import com.custom.bdyx.model.bean.SubstationVo;
import com.custom.bdyx.presenter.DetailActPresenter;
import com.custom.bdyx.utils.SimpleUtils;
import com.custom.bdyx.view.IDetailActView;
import com.custom.bdyx.widget.TitleBar;
import com.huaxi100.hxcommonlib.utils.EventBusCenter;
import com.huaxi100.hxcommonlib.utils.Utils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by levi on 2016/7/26.
 */
public class DetailActivity extends BaseActivity implements IDetailActView<SubstationVo> {
    String id;
    DetailActPresenter presenter;
    @Bind(R.id.iv_header)
    ImageView iv_header;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_status)
    TextView tv_status;
    @Bind(R.id.tv_tour_feedback)
    TextView tv_tour_feedback;
    @Bind(R.id.ll_record_container)
    LinearLayout ll_record_container;
    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    @Bind(R.id.sv_content)
    ScrollView sv_content;

    @Override
    public void doBusiness() {
        id = (String) getVo("0");
        new TitleBar(activity).setTitle("第" + id + "个变电站").back();
        activity.showDialog();
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        activity.dismissDialog();
                    }
                });
        init();
    }

    private void init() {
        presenter = new DetailActPresenter(activity);
        presenter.attachView(this);
        presenter.loadData(id);
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventBusMainThread(EventBusCenter eventBusCenter) {
        if ("tourfeedback".equals(eventBusCenter.getEventFlag())){
            tv_status.setText((String)eventBusCenter.getData());
        }
    }
//    public static class RefreshEvent implements Serializable{
//        private String statusName;
//
//        public String getStatusName() {
//            return statusName;
//        }
//
//        public void setStatusName(String statusName) {
//            this.statusName = statusName;
//        }
//    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_detail;
    }

    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadEnd() {

    }

    @Override
    public void onLoadError(String message) {

    }

    @Override
    public void onLoadNull() {

    }

    @Override
    public void onLoadData(SubstationVo data) {
        loadInfo(data);
    }

    private void loadInfo(SubstationVo data) {
        tv_name.setText(data.getName());
        tv_status.setText(data.getStatusName());
        SimpleUtils.showGlideView(data.getImg(), iv_header, 0);
        loadRecords(data.getRecords());
    }

    private void loadRecords(List<SubstationVo.Record> records) {
        if (Utils.isEmpty(records)) {
            ll_record_container.setVisibility(View.GONE);
            return;
        }
        for (SubstationVo.Record record : records) {
            View item = makeView(R.layout.item_tour_record);
            TextView tv_tour_status = (TextView) item.findViewById(R.id.tv_tour_status);
            TextView tv_tour_name = (TextView) item.findViewById(R.id.tv_tour_name);
            TextView tv_tour_time = (TextView) item.findViewById(R.id.tv_tour_time);
            tv_tour_status.setText(record.getTourStatusName());
            tv_tour_name.setText(record.getTourName());
            tv_tour_time.setText(record.getTourTime());
            ll_record_container.addView(item);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @OnClick(R.id.tv_tour_feedback)
    public void onClick() {
        skip(TourFeedbackActivity.class,id);
    }
}
