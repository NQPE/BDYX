package com.custom.bdyx.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.custom.bdyx.R;
import com.custom.bdyx.model.bean.SubstationVo;
import com.custom.bdyx.presenter.TourFeedbackActPresenter;
import com.custom.bdyx.view.ITourFeedbackActView;
import com.custom.bdyx.widget.PopupWindowsManager;
import com.custom.bdyx.widget.TitleBar;
import com.huaxi100.hxcommonlib.utils.EventBusCenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by levi on 2016/7/26.
 */
public class TourFeedbackActivity extends BaseActivity implements ITourFeedbackActView<SubstationVo> {
    String id;
    TourFeedbackActPresenter presenter;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_status)
    TextView tv_status;
    @Bind(R.id.tv_next)
    TextView tv_next;
    @Bind(R.id.et_desc)
    EditText et_desc;
    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    @Bind(R.id.sv_content)
    ScrollView sv_content;


    @Override
    public void doBusiness() {
        id = (String) getVo("0");
        new TitleBar(activity).setTitle("巡视反馈").back();
//        activity.showDialog();
//        Observable.timer(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        activity.dismissDialog();
//                    }
//                });
        init();
    }

    private void init() {
        presenter = new TourFeedbackActPresenter(activity);
        presenter.attachView(this);
        presenter.loadData(id);
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_tour_feedback;
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
        tv_name.setText(data.getName());
        tv_status.setText(data.getStatusName());
    }

//    @OnClick(R.id.tv_tour_feedback)
//    public void onClick(){
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }


    @OnClick({R.id.tv_change_status, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change_status:
                showChangeStatus();
                break;
            case R.id.tv_next:
                onNext();
                break;
        }
    }

    private void onNext() {
        EventBusCenter<String> event=new EventBusCenter("tourfeedback");
        event.setData(tv_status.getText().toString());
        EventBus.getDefault().post(event);
        finish();
    }

    private void showChangeStatus() {
        final PopupWindowsManager.ShowChangeStatusPopupWindow popupWindow=new PopupWindowsManager.ShowChangeStatusPopupWindow(activity);
        for (final TextView tv:popupWindow.tv_status){
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_status.setText(tv.getText().toString());
                    popupWindow.dismiss();
                }
            });
        }
        popupWindow.showPopupWindow();

    }
}
