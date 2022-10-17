package com.wxdgut.framework.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wxdgut.framework.utils.SystemUIUtils;

/**
 * FileName: BaseUIActivity
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:UI 基类
 * 配合 android:theme="@style/AppThemeFull"使用即可达到沉浸式状态栏
 */
public class BaseUIActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUIUtils.fixSystemUI(this);
    }
}
