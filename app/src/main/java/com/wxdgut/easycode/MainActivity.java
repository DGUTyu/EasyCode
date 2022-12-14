package com.wxdgut.easycode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wxdgut.framework.base.BaseUIActivity;

public class MainActivity extends BaseUIActivity implements View.OnClickListener {
    //视图控件
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    //初始化视图控件
    private void initView() {
        btnTest = findViewById(R.id.btnTest);
    }

    //初始化事件
    private void initEvent() {
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTest:
                startActivity(new Intent(baseContext, TestActivity.class));
                break;
        }
    }
}