package com.wxdgut.framework.utils;

import android.text.TextUtils;
import android.util.Log;

import com.wxdgut.framework.BuildConfig;

import java.text.SimpleDateFormat;

/**
 * FileName: LogUtils
 * Founder: yu
 * User: Administrator
 * Date: 2022年10月
 * Copyright (c) 2022 http://wxdgut.com
 * Email: hello_luyao@163.com
 * Profile:日志工具类(暂不支持写入文件)
 */
public class LogUtils {
    /**
     * Log.i
     *
     * @param text
     */
    public static void i(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.i(BuildConfig.LOG_TAG, text);
            }
        }
    }

    /**
     * Log.i
     *
     * @param Tag
     * @param text
     */
    public static void i(String Tag, String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.i(Tag, text);
            }
        }
    }

    /**
     * Log.e
     *
     * @param text
     */
    public static void e(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.e(BuildConfig.LOG_TAG, text);
            }
        }
    }

    /**
     * Log.e
     *
     * @param Tag
     * @param text
     */
    public static void e(String Tag, String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.e(Tag, text);
            }
        }
    }
}
