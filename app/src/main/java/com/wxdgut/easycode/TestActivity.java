package com.wxdgut.easycode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wxdgut.framework.base.BaseBackActivity;

public class TestActivity extends BaseBackActivity implements View.OnClickListener {
    //视图控件
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initEvent();
    }

    //初始化视图控件
    private void initView() {
        btnTest = findViewById(com.wxdgut.easycode.R.id.btnTest);
    }

    //初始化事件
    private void initEvent() {
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTest:
                Toast.makeText(TestActivity.this, "测试2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}