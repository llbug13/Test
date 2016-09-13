package com.ll.test.cope;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;

/**
 * Created by LL on 2016/9/13.
 * 复制，粘贴和剪贴板
 * 剪贴板支持字符串，uri(一般指向content provider) intent (应用的快捷方式)
 */
public class ClipboardTest {
    private void clipboardManager(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData newClip = ClipData.newPlainText("copied text", "Hell,android!");
        clipboardManager.setPrimaryClip(newClip);
//        content provider
        ClipData newClip1 = ClipData.newUri(context.getContentResolver(), "copied url", Uri.parse(""));

        if (!clipboardManager.hasPrimaryClip()) {
//           没有禁止ui选项


        }
        if (!clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

        } else {

        }
        ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
        item.getText();
        item.getIntent();
        item.getUri();
//      对象内容转换化为一个字符串
        CharSequence pasteText = item.coerceToText(context);
    }
}
