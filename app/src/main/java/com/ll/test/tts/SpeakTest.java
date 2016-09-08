package com.ll.test.tts;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;

import com.ll.test.R;
import com.ll.test.log.L;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by LL on 2016/9/8.
 * 使用语音识别
 */
public class SpeakTest extends Activity {
    private static final int VOICE_RECOGNITION = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
    }

    public void onL(View view) {
        speechWebSearch();
    }

    private void speechInput() {
        try {
            /**
             * Listing 11-2: Initiating a speech recognition request
             */
//        听过本地程序进行网络搜索或语音操作
//        RecognizerIntent.ACTION_WEB_SEARCH
//        接受输入语音
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // Specify free form input
//        RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        提示
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                    "or forever hold your peace");
//        返回潜在识别结果的数量
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
//      指定设备默认值以外的输入语言
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
            startActivityForResult(intent, VOICE_RECOGNITION);
        } catch (ActivityNotFoundException e) {
//            没有语音识别
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=APP_PACKAGE_NAME"));
            startActivity(browserIntent);

        }

    }

    private void speechWebSearch() {
        /**
         * Listing 11-4: Finding the results of a speech recognition request
         */
        Intent intent = new Intent(RecognizerIntent.ACTION_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (requestCode == VOICE_RECOGNITION && resultCode == RESULT_OK) {
            ArrayList<String> results;
            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (String s : results) {
                L.i("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + s);
            }
            float[] confidence;
//            confidence 0（无信任） 1（高信任）
            String confidenceExtra = RecognizerIntent.EXTRA_CONFIDENCE_SCORES;
            confidence = data.getFloatArrayExtra(confidenceExtra);
//使用字符串
            // TODO Do something with the recognized voice strings
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
