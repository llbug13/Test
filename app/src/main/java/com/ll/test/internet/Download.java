package com.ll.test.internet;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
//        取消个下载
        downloadManager.remove(reference);
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
//                    状态过滤
//                    myDownloadQuery.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL)

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

    //    查询失败原因
    private void listing607() {
        /**
         * Listing 6-7: Finding details of paused downloads
         */
        // Obtain the Download Manager Service.
        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager;
        downloadManager = (DownloadManager) context.getSystemService(serviceString);

        // Create a query for paused downloads.
        DownloadManager.Query pausedDownloadQuery = new DownloadManager.Query();
        pausedDownloadQuery.setFilterByStatus(DownloadManager.STATUS_PAUSED);
//        pausedDownloadQuery.setFilterByStatus(DownloadManager.STATUS_FAILED);
//暂停
        // Query the Download Manager for paused downloads.
        Cursor pausedDownloads = downloadManager.query(pausedDownloadQuery);

        // Find the column indexes for the data we require.
        int reasonIdx = pausedDownloads.getColumnIndex(DownloadManager.COLUMN_REASON);
        int titleIdx = pausedDownloads.getColumnIndex(DownloadManager.COLUMN_TITLE);
        int fileSizeIdx =
                pausedDownloads.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
        int bytesDLIdx =
                pausedDownloads.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
//遍历
        // Iterate over the result Cursor.
        while (pausedDownloads.moveToNext()) {
            // Extract the data we require from the Cursor.
            String title = pausedDownloads.getString(titleIdx);
            int fileSize = pausedDownloads.getInt(fileSizeIdx);
            int bytesDL = pausedDownloads.getInt(bytesDLIdx);

            // Translate the pause reason to friendly text.
            int reason = pausedDownloads.getInt(reasonIdx);
            String reasonString = "Unknown";
            switch (reason) {
//                DownloadManager.ERROR_CANNOT_RESUME
                case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                    reasonString = "Waiting for WiFi";
                    break;
                case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                    reasonString = "Waiting for connectivity";
                    break;
                case DownloadManager.PAUSED_WAITING_TO_RETRY:
                    reasonString = "Waiting to retry";
                    break;
                default:
                    break;
            }

            // Construct a status summary
            StringBuilder sb = new StringBuilder();
            sb.append(title).append("\n");
            sb.append(reasonString).append("\n");
            sb.append("Downloaded ").append(bytesDL).append(" / ").append(fileSize);

            // Display the status
            Log.d("DOWNLOAD", sb.toString());
        }

        // Close the result Cursor.
        pausedDownloads.close();
    }

    private GetAuthTokenCB myAccountManagerCallback = new GetAuthTokenCB();

    private void listing608() {
        /**
         */
        String acctSvc = Context.ACCOUNT_SERVICE;
        AccountManager accountManager = (AccountManager) context.getSystemService(acctSvc);
//        <uses-permission android:name="android.permission.GET_ACCOUNTS" />
        Account[] accounts = accountManager.getAccountsByType("com.google");

        if (accounts.length > 0)
//            身份令牌,身份验证通过执行call
            accountManager.getAuthToken(accounts[0], "ah", false,
                    myAccountManagerCallback, null);

    }

    private Activity activity;
    private static int ASK_PERMISSION = 1;

    private class GetAuthTokenCB implements AccountManagerCallback<Bundle> {
        public void run(AccountManagerFuture<Bundle> result) {
            try {
                Bundle bundle = result.getResult();
                Intent launch = (Intent) bundle.get(AccountManager.KEY_INTENT);
                if (launch != null)
//                    请求请求用户权限
                    activity.startActivityForResult(launch, ASK_PERMISSION);
                else {
//                    用户批准了用户的请求
//                    提取身份令牌并请求一个身份cooike
                    String auth_token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
//                    executeHttp(auth_token);
                }
            } catch (Exception ex) {
            }
        }
    }


//    private boolean executeHttp(String auth_token) throws ClientProtocolException, IOException {
//        DefaultHttpClient http_client = new DefaultHttpClient();
//        http_client.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);
//
//        String getString = "https://[yourappsubdomain].appspot.com/_ah/login?" +
//                "continue=http://localhost/&auth=" +
//                auth_token;
//        HttpGet get = new HttpGet(getString);
//
//        HttpResponse response = http_client.execute(get);
//
//        if (response.getStatusLine().getStatusCode() != 302)
//            return false;
//        else {
//            for (Cookie cookie : http_client.getCookieStore().getCookies())
//                if (cookie.getName().equals("ACSID")) {
//                    // Make authenticated requests to your Google App Engine server.
//                    return true;
//                }
//        }
//        return false;
//    }

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
