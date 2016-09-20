package com.ll.test.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LL on 2016/9/20.
 * heap dump文件内存使用查看
 * hprof-conv xxxxx.hprof yyyyy.hprof
 */
public class DumpFile {

    public static boolean createDumpFile(Context context) {
        String LOG_PATH = "/dump.gc/";
        boolean bool = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ssss");
        String createTime = sdf.format(new Date(System.currentTimeMillis()));
        String state = android.os.Environment.getExternalStorageState();
        // 判断SdCard是否存在并且是可用的
        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + LOG_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            String hprofPath = file.getAbsolutePath();
            if (!hprofPath.endsWith("/")) {
                hprofPath += "/";
            }

            hprofPath += createTime + ".hprof";
            try {
                android.os.Debug.dumpHprofData(hprofPath);
                bool = true;
                Log.d("ANDROID_LAB", "create dumpfile done!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bool = false;
            Log.d("ANDROID_LAB", "nosdcard!");
        }

        return bool;
    }
}
