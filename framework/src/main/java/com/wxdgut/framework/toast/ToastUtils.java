package com.wxdgut.framework.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wxdgut.framework.R;

/**
 * FileName: ToastUtils
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:Toast工具类
 */
public class ToastUtils {
    private static Toast toast;
    private static Context mContext;

    public static void init(Context context) {
        toast = new Toast(context);
        mContext = context;
    }

    /**
     * showToast 基类方法
     *
     * @param msg
     * @param hasImg
     * @param isLong
     */
    private static void baseShow(String msg, boolean hasImg, boolean isLong) {
        //
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastRoot = mInflater.inflate(R.layout.view_toast, null);
        //初始化控件
        ImageView iv_status = toastRoot.findViewById(R.id.iv_status);
        TextView tv_content = toastRoot.findViewById(R.id.tv_content);
        //
        iv_status.setVisibility(hasImg ? View.VISIBLE : View.GONE);
        tv_content.setText(msg);
        //
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.getView();
        toast.show();
    }

    public static void show(String msg) {
        baseShow(msg, false, false);
    }

    public static void show(String msg, boolean isLong) {
        baseShow(msg, false, isLong);
    }

    public static void showWithImg(String msg) {
        baseShow(msg, true, false);
    }

    public static void showWithImg(String msg, boolean isLong) {
        baseShow(msg, true, isLong);
    }
}
