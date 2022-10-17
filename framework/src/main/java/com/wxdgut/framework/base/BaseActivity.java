package com.wxdgut.framework.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.wxdgut.framework.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: BaseActivity
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:页面基类 封装动态权限申请
 */
public class BaseActivity extends AppCompatActivity {
    //避免后期写ExampleActivity.this等内容（查看该类的使用情况时能减少干扰）
    protected BaseActivity baseContext;
    //日志标志,不能直接写死TAG = BaseActivity.class.getCanonicalName();
    protected String TAG;
    //申请运行时权限的Code
    private static final int PERMISSION_REQUEST_CODE = 1000;
    //申请窗口权限的Code
    public static final int PERMISSION_WINDOW_REQUEST_CODE = 1001;
    //声明所需权限
    private String[] mStrPermission = {
            //同意读权限则只有读权限，同意写权限则读写权限都会同意
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            //Manifest.permission.INTERNET, //会自动授权，可不写
            //Manifest.permission.ACCESS_NETWORK_STATE, //会自动授权，可不写
            //Manifest.permission.ACCESS_WIFI_STATE, //会自动授权，可不写
            //Manifest.permission.CHANGE_NETWORK_STATE, //会自动授权，可不写
            //Manifest.permission.WAKE_LOCK, //会自动授权，可不写
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.RECORD_AUDIO,
            //Manifest.permission.VIBRATE, //会自动授权，可不写
            Manifest.permission.CALL_PHONE
    };

    //保存请求前没有允许的权限
    private List<String> notAllowPerList = new ArrayList<>();

    //保存请求后没有同意的失败权限
    private List<String> failedPerList = new ArrayList<>();

    //请求权限结果接口
    private OnPermissionsResult permissionsResult;

    protected interface OnPermissionsResult {
        void OnSuccess();

        void OnFail(List<String> noPermissions); //哪些失败
    }

    /**
     * 判断单个权限是否开启
     *
     * @param permission
     * @return 该权限是否已开启
     */
    protected boolean checkSinglePermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int check = checkSelfPermission(permission);
            LogUtils.e(permission + "  check:" + check);
            //PackageManager.PERMISSION_DENIED -1 失败
            //PackageManager.PERMISSION_GRANTED 0 成功
            return check == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    /**
     * 判断是否需要申请权限(判断所有权限是否开启)
     *
     * @return 是否所有权限都已开启
     */
    protected boolean checkAllPermissions() {
        notAllowPerList.clear(); //清除历史数据
        for (int i = 0; i < mStrPermission.length; i++) {
            boolean check = checkSinglePermission(mStrPermission[i]);
            if (!check) { //如果有不同意的权限则记录下来
                notAllowPerList.add(mStrPermission[i]);
            }
        }
        return notAllowPerList.size() > 0 ? false : true;
    }


    /**
     * 请求权限(请求权限的系统方法)
     *
     * @param permissions 权限数组
     */
    private void askPermissions(String[] permissions) {
        //增加版本判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 请求单一的权限
     *
     * @param permission
     * @param permissionsResult 处理结果接口
     */
    protected void askSinglePermissions(String permission, OnPermissionsResult permissionsResult) {
        this.permissionsResult = permissionsResult;
        askPermissions(new String[]{permission});
    }

    /**
     * 请求所有未允许的权限
     *
     * @param permissionsResult 处理结果接口
     */
    protected void askAllPermissions(OnPermissionsResult permissionsResult) {
        this.permissionsResult = permissionsResult;
        askPermissions(notAllowPerList.toArray(new String[notAllowPerList.size()]));
    }

    /**
     * 检查并请求单一权限
     *
     * @param permissionsResult 处理结果接口
     */
    public void checkAndAskSinglePermission(String permission, OnPermissionsResult permissionsResult) {
        if (!checkSinglePermission(permission)) {
            askSinglePermissions(permission, permissionsResult);
        }
    }

    /**
     * 检查并请求所有未允许的权限
     *
     * @param permissionsResult 处理结果接口
     */
    public void checkAndAskAllPermissions(OnPermissionsResult permissionsResult) {
        if (!checkAllPermissions()) {
            askAllPermissions(permissionsResult);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        failedPerList.clear(); //清除历史数据
        if (PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0) { //遍历结果
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        //你有失败的权限 failedPerList
                        failedPerList.add(permissions[i]);
                    }
                }
                if (permissionsResult != null) {
                    if (failedPerList.size() == 0) { //全部成功，对外宣告
                        LogUtils.i("permissionsResult.OnSuccess");
                        permissionsResult.OnSuccess();
                    } else {
                        LogUtils.e("permissionsResult.OnFail"); //不一定执行此句
                        //有些权限会自动授权，如
                        //Manifest.permission.INTERNET,
                        //Manifest.permission.ACCESS_NETWORK_STATE,
                        //Manifest.permission.ACCESS_WIFI_STATE,
                        //Manifest.permission.CHANGE_NETWORK_STATE,
                        //Manifest.permission.WAKE_LOCK,
                        //Manifest.permission.VIBRATE,
                        permissionsResult.OnFail(failedPerList);
                    }
                }
            }
        } else {
            LogUtils.i("权限请求码错误！");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 判断窗口权限
     *
     * @return
     */
    protected boolean checkWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this);
        }
        return true;
    }

    /**
     * 请求窗口权限
     */
    protected void askWindowPermission() {
        LogUtils.e("申请窗口权限，暂时没做UI交互");
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 检查并请求窗口权限
     */
    protected void checkAndAskWindowPermission() {
        if (checkWindowPermission()) {
            LogUtils.e(TAG, "已开启窗口权限");
        } else {
            LogUtils.e(TAG, "窗口权限未开启");
            askWindowPermission();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseContext = this;
        TAG = this.getClass().getCanonicalName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
