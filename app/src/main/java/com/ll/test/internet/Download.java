package com.ll.test.internet;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * Created by LL on 2016/8/29.
 */
public class Download {
    DownloadManager downloadManager;
    long reference;

    public void getDownloadManager(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("https://www.baidu.com/");
        DownloadManager.Request request = new DownloadManager.Request(uri);
//        添加http报头
//        request.addRequestHeader()
//        返回数据类型
//        request.setMimeType()
//        设置网络类型
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
//        设置漫游时是否下载
//        request.setAllowedOverMetered()
//        得到推荐传送的最大数据来判断是否在WIFI下下载
//        downloadManager.getRecommendedMaxBytesOverMobile(context);
//        开始下载了
        reference = downloadManager.enqueue(request);
//        设置通知栏
        request.setTitle("ll");
        request.setDescription("描述");
//        request.setNotificationVisibility()
//        默认选项表示持续时间，下载完成消失
//        DownloadManager.Request.VISIBILITY_VISIBLE
//        一直显示
//        DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
//        下载完成才显示
//        DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION
//        不显示，需要权限download_without_notification
//        DownloadManager.Request.VISIBILITY_HIDDEN
//        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
//        request.setDestinationUri(Uri.fromFile())
//        外部
//        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "a.png");
//        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_MUSIC, "a.mp4");
//       允许被扫描
        request.allowScanningByMediaScanner();
//对系统的doenloads是可见的和可管理的
        request.setVisibleInDownloadsUi(true);
//        注册receiver(下载完成通知)
//        DownloadManager.ACTION_DOWNLOAD_COMPLETE
        Intent intent = null;
        long reference1 = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        try {
//            得到文件的位置，如果制定的位置可以直接操作
            downloadManager.openDownloadedFile(reference1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (reference == reference1) {
//            对文件操作
        }
    }

    private Context context = null;

    private void listing604() {
        final DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        /**
         * Listing 6-4: Monitoring downloads for completion
         */

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (Download.this.reference == reference) {

                    /**
                     * Listing 6-6: Finding details of completed downloads
                     */
                    DownloadManager.Query myDownloadQuery = new DownloadManager.Query();
                    myDownloadQuery.setFilterById(reference);

                    Cursor myDownload = downloadManager.query(myDownloadQuery);
                    if (myDownload.moveToFirst()) {
                        int fileNameIdx =
                                myDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                        int fileUriIdx =
                                myDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);

                        String fileName = myDownload.getString(fileNameIdx);
                        String fileUri = myDownload.getString(fileUriIdx);

                        // TODO Do something with the file.
                        Log.d("tag", fileName + " : " + fileUri);
                    }
                    myDownload.close();

                }
            }
        };

        context.registerReceiver(receiver, filter);
    }

    //通知栏
    private void listing605() {
        /**
         * Listing 6-5: Responding to download notification clicks
         */
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraID = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraID);
                for (long reference : references)
                    if (reference == Download.this.reference) {
                        break;
                        // Do something with downloading file.
                    }
            }
        };
        context.registerReceiver(receiver, filter);
    }
}
