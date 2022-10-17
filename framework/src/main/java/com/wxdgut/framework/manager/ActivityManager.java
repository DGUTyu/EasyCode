package com.wxdgut.framework.manager;

import android.app.Activity;

import com.wxdgut.framework.utils.LogUtils;

import java.util.HashSet;

/**
 * FileName: ActivityManager
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:页面管理器
 * 统计Activity
 */
public class ActivityManager {
    //存储Activity
    private static HashSet<Activity> hashSet = new HashSet<>();

    public static ActivityManager getInstance() {
        //LogUtils.e("ActivityManager getInstance");
        return Container.instance;
    }

    //使用内部类实现单例,延迟加载,线程安全,
    private static class Container {
        public static ActivityManager instance = new ActivityManager();
    }

    public ActivityManager() {
        //LogUtils.e("ActivityManager 实例化");
    }

    /**
     * 填充
     */
    public void addActivity(Activity activity) {
        try {
            hashSet.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出
     */
    public void exit() {
        try {
            for (Activity activity : hashSet) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
