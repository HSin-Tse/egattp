package com.example.egghttp.EggLog;

import android.os.Looper;
import android.util.Log;

public class L {

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
//    public static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化

//    private static final String MITAKE_NETWORK = "MitakeNetwork";

    private static final String TAG = "hcia";


    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {


        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        int index = 3;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();

        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(").append(className).append(":").append(lineNumber).append(") ").append(methodName).append(" :");

        if (isDebug)
            Log.d(TAG, className + " " + msg + " " + isUIthread() + " " + stringBuilder);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        int index = 3;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();

        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(").append(className).append(":").append(lineNumber).append(") ");

        if (isDebug)
            Log.d(tag, className + "|" + methodName + " " + msg + "  |" + isUIthread() + stringBuilder);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }


    public static String isUIthread() {
        return Looper.myLooper() == Looper.getMainLooper() ? "主線成" : "副線成";
    }

}