package com.ll.test.log;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.ll.test.java.Java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.*;
import java.util.logging.Logger;

/**
 * Created by LL on 2016/8/22.
 */

public class L {

    private final static boolean IS_BUG = true;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void log() {
        java.util.logging.Logger.getGlobal().info("");
        Logger logger1 = Logger.getLogger("sd.a.4");
        Logger logger = Logger.getLogger("sd.a");
        logger.setLevel(Level.FINE);
        logger.fine("");
        logger.warning("");
        logger.log(Level.FINE, "");
        logger.logp(Level.ALL,"","","");
        LogManager logManager=null;
//        logger.entering();
//        logger.exiting();
        ConsoleHandler consoleHandler=null;
//        consoleHandler.setLevel();
        Thread.dumpStack();
    }


    public static void out(Object... o) {
        if (IS_BUG) {
            if (o != null) {
                if (o.length == 1) {
                    System.out.println(o[0].getClass());
                } else {
                    System.out.println(o[0].getClass() + "::" + o[1]);
                }

            } else {
                System.out.println("null");
            }

        }
    }

    public static void i(String msg) {
        if (IS_BUG) {
            Log.e("----------<>----------", msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_BUG) {
            Log.e("----------<" + tag + ">----------", msg);
        }
    }

    //    我怎么输出具体的日志调用行呢？
//    这个秘密就在：
//    Thread.currentThread().getStackTrace();我们可以通过当前的线程，拿到当前调用的栈帧集合（称呼不一定准备）。
//    你可以理解为当我们调用方法的时候，每进入一个方法，
//    会将该方法的相关信息（例如：类名，方法名，方法调用行数等）存储下来，
//    压入到一个栈中，当方法返回的时候再将其出栈。
    public static void b() {
        StringBuffer err = new StringBuffer();
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stack.length; i++) {
            err.append("\tat ");
            err.append(stack[i].toString());
            err.append("\n");
        }
//        getClassName
//        getMethodName
//        getFileName
//        getLineNumber
//        底部最开始
//        可以看到我们整个方法的调用过程，底部的最先开始调用，顺序为onCreate->a->b->Thread.getStackTrace->VMStack.getThreadStackTrace.
//        最后两个是因为我们的stacks是在VMStack.getThreadStackTrace方法中获取，然后返回的，所以包含了这两个的内部调用信息。
        Log.e("TAG", err.toString());
    }

    //    因为我们的入口是L类的方法，所以，我们直接遍历，L类相关的下一个非L类的栈帧信息就是具体调用的方法。
    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(L.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    public static void e(String tag, String msg, Object... params) {
//        if (!sDebug) return;
//        String finalTag = getFinalTag(tag);
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.e("finalTag", "(" + targetStackTraceElement.getFileName() + ":"
                + targetStackTraceElement.getLineNumber() + ")");
        Log.e("finalTag", String.format(msg, params));
    }

    static int JSON_INDENT = 1;

    private static String getPrettyJson(String jsonStr) {
        try {
            jsonStr = jsonStr.trim();
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                return jsonObject.toString(JSON_INDENT);
            }
            if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                return jsonArray.toString(JSON_INDENT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Invalid Json, Please Check: " + jsonStr;
    }


    //    还有就是一些统计PV相关的SDK，会强制要求在某些方法中执行某个方法，例如，必须在Activity.onResume中执行，PVSdk.onResume，如果你之前遇到过某个SDK给你抛了类似的异常，那么它的原理就是这么实现的。
    public static class PVSdk {

        public static void onResume() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            boolean result = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                String methodName = stackTraceElement.getMethodName();
                String className = stackTraceElement.getClassName();
                try {
                    boolean assignableFromClass = Class.forName(className).isAssignableFrom(Activity.class);
                    if (assignableFromClass && "onResume".equals(methodName)) {
                        result = true;
                        break;
                    }
                } catch (ClassNotFoundException e) {
                    // ignored
                }
            }
            if (!result)
                throw new RuntimeException("PVSdk.onResume must in Activity.onResume");
            //do other things
        }
    }

}
