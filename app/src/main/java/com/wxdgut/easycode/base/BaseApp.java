package com.wxdgut.easycode.base;

import android.app.Application;
import android.content.Context;

import com.wxdgut.framework.toast.ToastUtils;

/**
 * FileName: BaseApp
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    //初始化
    private void init(Context context) {
        ToastUtils.init(context);
    }
}
