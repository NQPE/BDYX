package com.custom.bdyx.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.custom.bdyx.R;
import com.custom.bdyx.ui.activity.BaseActivity;
import com.custom.bdyx.ui.activity.DetailActivity;
import com.custom.bdyx.widget.basepopup.BasePopupWindow;
import com.huaxi100.hxcommonlib.base.HxBaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * 各种popupwindow的管理类
 */
public class PopupWindowsManager {

    public static class ShowChangeStatusPopupWindow extends BasePopupWindow {
        public TextView tv_status1;
        public TextView tv_status2;
        public TextView tv_status3;
        public TextView tv_status4;
        public LinearLayout ll_content;
        public RelativeLayout rl_window;
        public TextView[] tv_status;

        public ShowChangeStatusPopupWindow(Activity context) {
            super(context);
            init(this);
        }

        private void init(final ShowChangeStatusPopupWindow showPushContentPopupWindow) {
            tv_status1= (TextView) getPopupView().findViewById(R.id.tv_status1);
            tv_status2= (TextView) getPopupView().findViewById(R.id.tv_status2);
            tv_status3= (TextView) getPopupView().findViewById(R.id.tv_status3);
            tv_status4= (TextView) getPopupView().findViewById(R.id.tv_status4);
            ll_content= (LinearLayout) getPopupView().findViewById(R.id.ll_content);
            rl_window= (RelativeLayout) getPopupView().findViewById(R.id.rl_window);
            tv_status=new TextView[]{tv_status1,tv_status2,tv_status3,tv_status4};
        }

        @Override
        protected Animation getShowAnimation() {
            return null;
        }

        @Override
        protected View getClickToDismissView() {
            return getPopupView().findViewById(R.id.rl_window);
        }

        @Override
        protected void setPopupWindowCustomLocation(PopupWindow mPopupWindow, View view) {

        }

        @Override
        public View setPopupView() {
            return ((BaseActivity)mContext).makeView(R.layout.window_popup_changestatus);
        }

        @Override
        public View setAnimaView() {
            return null;
        }
    }


    
}
