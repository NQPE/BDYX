package com.custom.bdyx.widget;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.custom.bdyx.R;


/**
 * Created by ${Rhino} on 2015/8/4 10:17
 * 标题栏
 */
public class TitleBar {

    public Activity activity;

    public ImageView iv_back;
    
    public ImageView iv_right;

    public TextView tv_title;

    public TextView tv_right;

    public View v_division;

    public RelativeLayout rl_titlebar;

    public TitleBar(Activity activity) {
        this.activity = activity;
        iv_back = (ImageView) activity.findViewById(R.id.iv_back);
        tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_right = (TextView) activity.findViewById(R.id.tv_right);
        rl_titlebar = (RelativeLayout) activity.findViewById(R.id.rl_titlebar);
        iv_right = (ImageView) activity.findViewById(R.id.iv_right);
        v_division=activity.findViewById(R.id.v_division);
    }

    public TitleBar setTitle(String title) {
        tv_title.setText(title);
        return this;
    }

    public TitleBar back() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
        return this;
    }

    public TitleBar back(View.OnClickListener listener) {
        iv_back.setOnClickListener(listener);
        return this;
    }

}
