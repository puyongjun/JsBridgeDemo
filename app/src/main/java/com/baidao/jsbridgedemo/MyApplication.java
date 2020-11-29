package com.baidao.jsbridgedemo;

import android.app.Application;
import android.view.View;

import com.github.lzyzsd.jsbridge.BridgeWebView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by pyj on 2020/10/14.
 * Desc:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        List<Class<? extends View>> classList = new ArrayList<>();
        classList.add(BridgeWebView.class);
        BGASwipeBackHelper.init(this,classList);
    }
}
