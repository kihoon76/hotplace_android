package com.hotplace25.mobile.utils;

import com.hotplace25.mobile.BuildConfig;

/**
 * Created by khnam on 2018-01-10.
 */

public class Log {
    public static final void d(String tag, String msg) {
        if(BuildConfig.DEBUG) android.util.Log.d(tag, msg);
    }


    public static final void i(String tag, String msg) {
        if(BuildConfig.DEBUG) android.util.Log.i(tag, msg);
    }


    public static final void e(String tag, String msg) {
        if(BuildConfig.DEBUG) android.util.Log.e(tag, msg);
    }


    public static final void w(String tag, String msg) {
        if(BuildConfig.DEBUG) android.util.Log.w(tag, msg);
    }
}
