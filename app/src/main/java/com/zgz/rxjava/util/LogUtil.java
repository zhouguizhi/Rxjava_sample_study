package com.zgz.rxjava.util;
import android.util.Log;

public class LogUtil {
    private static final String TAG = LogUtil.class.getSimpleName();
    public static void e(String msg){
        Log.e(TAG,msg);
    }
}
