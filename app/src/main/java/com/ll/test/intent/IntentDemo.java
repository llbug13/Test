package com.ll.test.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LL on 2016/8/24.
 */
public class IntentDemo {
    private Context context;
    private Activity activity;

    private void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345"));
        intent.putExtra("", new Bundle());
        context.startActivity(intent);
    }

    private void linkify() {
//        context.startActivity(new Intent(Intent.ACTION_VIEW,uri));
        TextView textView = null;
//        位掩码
        Linkify.addLinks(textView, Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        int flags = Pattern.CASE_INSENSITIVE;
        Pattern p = Pattern.compile("\\bquake[\\s]?[0-9]+\\b", flags);
        String baseuri = "content://com.paad.earthquake/earthquakes";
        Linkify.addLinks(textView, p, baseuri,new MyMatchFilter(),new MyTransformFilter());
//        Android:aurtoLink
    }

    class MyMatchFilter implements Linkify.MatchFilter {

        @Override
        public boolean acceptMatch(CharSequence s, int start, int end) {
//            取消前面那些“！”
            return (start == 0 || s.charAt(start - 1) != '1');
        }
    }

    class MyTransformFilter implements Linkify.TransformFilter {
        @Override
        public String transformUrl(Matcher match, String url) {
            return url.toLowerCase().replace(" ", "");
        }
    }

    private void activityInfo() {
//        activity
//        Intent.ACTION_ALL_APPS所有已安装app
//        Intent.ACTION_ANSWER来电处理
//        Intent.ACTION_BUG_REPORT显示bug界面
//        Intent.ACTION_CALL打开一个电话拨号程序（替换本地的拨号应用）
//        Intent.ACTION_CALL_BUTTON按下硬件的“拨号键”时调用
//        Intent.ACTION_DELETE允许删除uri中的数据
//        Intent.ACTION_DIAL拨号程序，一般是本地程序，uri的格式“tel：123（ ）（_）13”
//        Intent.ACTION_INSERT
//        Intent.ACTION_PICK打开一个子activity，"content://contacts/people"打开本地联系人
//        Intent.ACTION_SEARCH特定搜索
//        Intent.ACTION_SEARCH_LONG_PRESS截取长按搜索键（硬件）
//        Intent.ACTION_SENDTO 指定的uri的联系人发送一条消息
//        Intent.ACTION_SEND发送指定数据
//        Intent.ACTION_VIEW通用动作http:打开浏览器 tel:拨打电话 geo:Google地图
//        Intent.ACTION_WEB_SEARCH打开浏览器进行web搜索
    }

    private void people() {
//        选择联系人
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        activity.startActivityForResult(intent, 1);
//        activity.setResult();
//        子activity非正常关闭或者没有setResult();都会返回Activity.RESULT_CANCELED
    }

    private void isOpen() {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345"));
        ComponentName componentName = intent.resolveActivity(packageManager);
//        查找是否有相关activity
        if (componentName == null) {
//            在谷歌商店上找
            Uri markeyUri = Uri.parse("market://search?q=pname:com.myapp.packagename");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(markeyUri);
            if (marketIntent.resolveActivity(packageManager) != null) {
                context.startActivity(marketIntent);
            } else {
                Log.e(getClass().getName(), "not open");
            }
        } else {
            context.startActivity(intent);
        }

    }
}
