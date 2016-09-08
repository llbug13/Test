package com.ll.test.tts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by LL on 2016/9/8.
 */
public class TTSTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        检查安装tts语音包
        Intent intent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, 1);
    }

    private TextToSpeech tts;
    private boolean ttsis;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        已安装返回
//        TextToSpeech.Engine.CHECK_VOICE_DATA_PASS
        if (requestCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        ttsis = true;
                        // TODO: 2016/9/8 说话
                        s();
                    }
                }
            });
        } else {
//            安装
            Intent intent = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(intent);
        }
    }

    private void s() {
        if (tts != null) {
            if (tts.isLanguageAvailable(Locale.CHINA) >= 0) {
                //            HashMap map = null;
//            tts.setPitch(0.8f);音高
//            tts.setSpeechRate(1.1f)音速
//            tts.setLanguage(Locale.CHINA),设置发音，制定语言和国家
            }

            tts.speak("lsakdl", TextToSpeech.QUEUE_ADD, null);
        }
    }

    private void clear() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    @Override
    protected void onDestroy() {
        clear();
        super.onDestroy();
    }
}
