package com.example.gddcs.gddcsdemo.util;

import android.util.Log;

/**
* @author GDDCS
* create at 2019/3/26 11:20
* description: log工具类
*/
public class LogUtil {
    private static boolean isShow = true;

    public static void d(String tag, String message){
        if (isShow){
            Log.d(tag,message);
        }
    }

    public static void e(String tag, String message){
        if (isShow){
            Log.e(tag,message);
        }
    }

}
