package com.custom.bdyx.ui.activity;

import android.app.ProgressDialog;
import android.view.View;

import com.custom.bdyx.R;
import com.huaxi100.hxcommonlib.base.HxBaseActivity;
import com.huaxi100.hxcommonlib.utils.EventBusCenter;

/**
 * Created by levi on 2016/7/22.
 */
public abstract class BaseActivity extends HxBaseActivity{
    @Override
    protected void onEventBusMainThread(EventBusCenter eventBusCenter) {

    }

    @Override
    public void showDialog() {
//        super.showDialog();
        if (activity == null) {
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(activity, com.huaxi100.hxcommonlib.R.style.LoadingDialog);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show();
        View dialogView = makeView(R.layout.view_custom_loading);
//        ImageView imageView = (ImageView) dialogView.findViewById(R.id.rotate_image);
//        imageView.setImageResource(R.drawable.loading_ani);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();
        loadingDialog.setContentView(dialogView);
    }
}
