package com.ll.test.web;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ll.test.R;
import com.ll.test.base.BActivity;

/**
 * Created by LL on 2016/9/21.
 *
 * Cordova插件
 *
 */
public class WebTest extends BActivity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);
        webView = (WebView) findViewById(R.id.web_web);
        webView.loadUrl("http://www.baidu.com");
//        webview.loadUrl(file:///android_asset/index.html);

//         WebViewClient 主要帮助WebView处理各种通知、请求事件的
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                i(url);
                return true;
//                if (Uri.parse(url).getHost().equals("www.example.com")) {
//                    return false;
//                }
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }

    @SuppressLint("JavascriptInterface")
    private void set() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
//        js调用Java
        //把本类的一个实例添加到js的全局对象window中，
//这样就可以使用windows.injs来调用它的方法
        webView.addJavascriptInterface(new JavascriptInterfaceImpl(this,
                webView), "Android");
        webView.addJavascriptInterface(new InJavaScript(), "injs");
//        Js代码
//        function sendToAndroid(){
//            var str = "Cookie call the Android method from js";
//            windows.injs.runOnAndroidJavaScript(str);//调用android的函数
//        }
        //调用绑定的Java对象的方法，同步显示android返回的内容

//        function showAndroidSyncMsg ()
//        {
//            console.log("showAndroiHello method");
//            var str = Android.syncExec();
//            alert(str);
//        }

//        Java调用js
//        function getFromAndroid(str){
//                document.getElementByIdx_x_x_x("android").innerHTML=str;
//        }
        //异步方法
//             function asyncFun() {
//                   console.log("asyncFun method");　　　　
//                   setTimeout(function() {
//                         console.log("异步执行");　　　　　　
//                          Android.callBack("来自JavaScript的异步消息");　　　　
//                        },
//                    5000);　　
//                 }
        webView.loadUrl("javascript:getFromAndroid('Cookie call the js function from Android')");
//        webView.evaluateJavascript("");

//        Android中处理JS的警告，对话框等 在Android中处理JS的警告，对话框等需要对WebView设置WebChromeClient对象
        //设置WebChromeClient
//         WebChromeClient主要用来辅助WebView处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
        webView.setWebChromeClient(new WebChromeClient() {
                                       //处理javascript中的alert
                                       public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                                           //构建一个Builder来显示网页中的对话框
                                           AlertDialog.Builder builder = new AlertDialog.Builder(WebTest.this);
                                           builder.setTitle("Alert");
                                           builder.setMessage(message);
                                           builder.setPositiveButton(android.R.string.ok,
                                                   new AlertDialog.OnClickListener() {
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           result.confirm();
                                                       }
                                                   });
                                           builder.setCancelable(false);
                                           builder.create();
                                           builder.show();
                                           return true;
                                       }

                                       //处理javascript中的confirm
                                       public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                                           AlertDialog.Builder builder = new AlertDialog.Builder(WebTest.this);
                                           builder.setTitle("confirm");
                                           builder.setMessage(message);
                                           builder.setPositiveButton(android.R.string.ok,
                                                   new AlertDialog.OnClickListener() {
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           result.confirm();
                                                       }
                                                   });
                                           builder.setNegativeButton(android.R.string.cancel,
                                                   new DialogInterface.OnClickListener() {
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           result.cancel();
                                                       }
                                                   });
                                           builder.setCancelable(false);
                                           builder.create();
                                           builder.show();
                                           return true;
                                       }

                                       @Override
                                       //设置网页加载的进度条
                                       public void onProgressChanged(WebView view, int newProgress) {
                                           WebTest.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
                                           super.onProgressChanged(view, newProgress);
                                       }

                                       //设置应用程序的标题title
                                       public void onReceivedTitle(WebView view, String title) {
                                           WebTest.this.setTitle(title);
                                           super.onReceivedTitle(view, title);
                                       }

                                       //            让其在LogCat中打印信息
                                       public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                                           i(message + " -- From line "
                                                   + lineNumber + " of "
                                                   + sourceID);
                                       }

                                       public boolean onConsoleMessage(ConsoleMessage cm) {
                                           Log.d("MyApplication", cm.message() + " -- From line "
                                                   + cm.lineNumber() + " of "
                                                   + cm.sourceId());
                                           return true;
                                       }

                                       public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota,
                                                                           long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
                                           quotaUpdater.updateQuota(estimatedSize * 2);
                                       }

                                       public void onGeolocationPermissionsShowPrompt(String origin,
                                                                                      GeolocationPermissions.Callback callback) {
                                           callback.invoke(origin, true, false);
                                           super.onGeolocationPermissionsShowPrompt(origin, callback);
                                       }
                                   }

        );
//        ● HTML5本地存储在Android中的应用 HTML5提供了2种客户端存储数据新方法： localStorage 没有时间限制 sessionStorage 针对一个Session的数据存储
//        <script type="text/javascript">
//                localStorage.lastname="Smith";
//        document.write(localStorage.lastname);
//        </script>
//        <script type="text/javascript">
//                sessionStorage.lastname="Smith";
//        document.write(sessionStorage.lastname);
//        </script>
//        Js代码
////清空storage
//        localStorage.clear();
////设置一个键值
//        localStorage.setItem(“yarin”,“yangfegnsheng”);
////获取一个键值
//        localStorage.getItem(“yarin”);
////获取指定下标的键的名称（如同Array）
//        localStorage.key(0);
////return “fresh” //删除一个键值
//        localStorage.removeItem(“yarin”);
//        注意一定要在设置中开启哦
//        setDomStorageEnabled（true）


//        Java代码
////启用数据库
        webSettings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
//设置数据库路径
        webSettings.setDatabasePath(dir);
//使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
//扩充数据库的容量（在WebChromeClinet中实现）
//        public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota,
//        long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
//            quotaUpdater.updateQuota(estimatedSize * 2);
//        }

//        Java代码
//启用地理定位
        webSettings.setGeolocationEnabled(true);
//设置定位的数据库路径
        webSettings.setGeolocationDatabasePath(dir);

//配置权限（同样在WebChromeClient中实现）
//        public void onGeolocationPermissionsShowPrompt(String origin,
//                GeolocationPermissions.Callback callback) {
//            callback.invoke(origin, true, false);
//            super.onGeolocationPermissionsShowPrompt(origin, callback);
//        }

//        Js代码
////获取当前地理位置
//        navigator.geolocation.getCurrentPosition(success_callback_function, error_callback_function, position_options)
////持续获取地理位置
//        navigator.geolocation.watchPosition(success_callback_function, error_callback_function, position_options)
////清除持续获取地理位置事件
//        navigator.geolocation.clearWatch(watch_position_id)

    }
//    {
//        在JS中按常规进行数据库操作
//                Js代码
//        function initDatabase() {
//        try {
//            if (!window.openDatabase) {
//                alert('Databases are not supported by your browser');
//            } else {
//                var shortName = 'YARINDB';
//                var version = '1.0';
//                var displayName = 'yarin db';
//                var maxSize = 100000; // in bytes
//                YARINDB = openDatabase(shortName, version, displayName, maxSize);
//                createTables();
//                selectAll();
//            }
//        } catch(e) {
//            if (e == 2) {
//                // Version mismatch.
//                console.log("Invalid database version.");
//            } else {
//                console.log("Unknown error "+ e +".");
//            }
//            return;
//        }
//    }
//
//        function createTables(){
//        YARINDB.transaction(
//                function (transaction) {
//            transaction.executeSql('CREATE TABLE IF NOT EXISTS yarin(id INTEGER NOT NULL PRIMARY KEY, name TEXT NOT NULL,desc TEXT NOT NULL);', [], nullDataHandler, errorHandler);
//        }
//        );
//        insertData();
//    }
//
//        function insertData(){
//        YARINDB.transaction(
//                function (transaction) {
//            //Starter data when page is initialized
//            var data = ['1','yarin yang','I am yarin'];
//
//            transaction.executeSql("INSERT INTO yarin(id, name, desc) VALUES (?, ?, ?)", [data[0], data[1], data[2]]);
//        }
//        );
//    }
//
//        function errorHandler(transaction, error){
//        if (error.code==1){
//            // DB Table already exists
//        } else {
//            // Error is a human-readable string.
//            console.log('Oops.  Error was '+error.message+' (Code '+error.code+')');
//        }
//        return false;
//    }
//
//
//        function nullDataHandler(){
//        console.log("SQL Query Succeeded");
//    }
//
//        function selectAll(){
//        YARINDB.transaction(
//                function (transaction) {
//            transaction.executeSql("SELECT * FROM yarin;", [], dataSelectHandler, errorHandler);
//        }
//        );
//    }
//
//        function dataSelectHandler(transaction, results){
//        // Handle the results
//        for (var i=0; i<results.rows.length; i++) {
//            var row = results.rows.item(i);
//            var newFeature = new Object();
//            newFeature.name   = row['name'];
//            newFeature.decs = row['desc'];
//
//            document.getElementByIdx_x_x_x("name").innerHTML="name:"+newFeature.name;
//            document.getElementByIdx_x_x_x("desc").innerHTML="desc:"+newFeature.decs;
//        }
//    }
//
//        function updateData(){
//        YARINDB.transaction(
//                function (transaction) {
//            var data = ['fengsheng yang','I am fengsheng'];
//            transaction.executeSql("UPDATE yarin SET name=?, desc=? WHERE id = 1", [data[0], data[1]]);
//        }
//        );
//        selectAll();
//    }
//
//        function ddeleteTables(){
//        YARINDB.transaction(
//                function (transaction) {
//            transaction.executeSql("DROP TABLE yarin;", [], nullDataHandler, errorHandler);
//        }
//        );
//        console.log("Table 'page_settings' has been dropped.");
//    }
//        注意onLoad中的初始化工作
//        function initLocalStorage(){
//        if (window.localStorage) {
//            textarea.addEventListener("keyup", function() {
//                window.localStorage["value"] = this.value;
//                window.localStorage["time"] = new Date().getTime();
//            }, false);
//        } else {
//            alert("LocalStorage are not supported in this browser.");
//        }
//    }
//
//        window.onload = function() {
//        initDatabase();
//        initLocalStorage();
//    }
//    }

    private Handler handler = new Handler();

    @JavascriptInterface
    public void runOnAndroidJavaScript(final String str) {
        handler.post(new Runnable() {
            public void run() {
                TextView show = (TextView) findViewById(R.id.web_text);
                show.setText(str);
            }
        });
    }

    final class InJavaScript {
        public void runOnAndroidJavaScript(final String str) {
            handler.post(new Runnable() {
                public void run() {
                    TextView show = (TextView) findViewById(R.id.web_text);
                    show.setText(str);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
