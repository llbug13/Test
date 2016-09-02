package com.ll.test.service;

import android.os.AsyncTask;

/**
 * Created by LL on 2016/9/2.
 * internet下载service是更好的选择，当设备的放向改变时会activity会重建，AsyncTaskTest会消失
 */
//输入，更新，结果

public class AsyncTaskTest extends AsyncTask<String, Integer, String> {
    private void executeAsync() {
        /**
         * Listing 9-12: Executing an asynchronous task
         */
//        执行
        String input = "redrum ... redrum";
        new AsyncTaskTest().execute(input);
    }

    @Override
    protected String doInBackground(String... params) {
        // Moved to a background thread.
        String result = "";
        int myProgress = 0;

        int inputLength = params[0].length();

        // Perform background processing task, update myProgress]
        for (int i = 1; i <= inputLength; i++) {
            myProgress = i;
            result = result + params[0].charAt(inputLength - i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
//            更新进度
            publishProgress(myProgress);
        }

        // Return the value to be passed to onPostExecute
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // Synchronized to UI thread.
        // Update progress bar, Notification, or other UI elements
//        更新进度ui
//        asyncProgress.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        // Synchronized to UI thread.
        // Report results via UI update, Dialog, or notifications
    }
//    长时间的线程
    private void backgroundExecution() {
        // This moves the time consuming operation to a child thread.
        Thread thread = new Thread(null, doBackgroundThreadProcessing,
                "Background");
        thread.start();
    }

    //Runnable that executes the background processing method.
    private Runnable doBackgroundThreadProcessing = new Runnable() {
        public void run() {
            backgroundThreadProcessing();
        }
    };
    private void backgroundThreadProcessing() {
        // [ ... Time consuming operations ... ]
    }
}
