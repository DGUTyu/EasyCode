package com.wxdgut.framework.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 * FileName: SystemUI
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:沉浸式实现
 */
public class SystemUIUtils {
    public static void fixSystemUI(Activity mActivity) {
        //沉浸式状态栏一般是针对android 5.0以上进行适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //首先获得最底层的view
            /**
             * View.SYSTEM_UI_FLAG_FULLSCREEN 全屏
             *
             * View.SYSTEM_UI_FLAG_LAYOUT_STABLE 显示状态栏
             *
             * View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 隐藏状态栏
             */
            //沉浸式状态栏
            mActivity.getWindow().getDecorView().
                    setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    );
            mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            //隐藏ActionBar,但一般不在这里设置，而是在清单文件的activity标签中设置主题
            //getSupportActionBar().hide();
        }

    }
}
