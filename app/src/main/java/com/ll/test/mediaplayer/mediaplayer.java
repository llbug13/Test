package com.ll.test.mediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/14.
 */
public class mediaplayer extends Activity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player);
//        videoView = (VideoView) findViewById(R.id.videoView);
        configureVideoView();
    }

    private void configureVideoView() {
        /**
         * Listing 15-2: Video playback using a Video View
         */
        // Get a reference to the Video View.
        final VideoView videoView = (VideoView) findViewById(R.id.videoView);

        // Configure the video view and assign a source video.
//        防止屏幕变暗
        videoView.setKeepScreenOn(true);
//        远程
//        videoView.setVideoURI();
//        Wake Lock Permission - If your player application needs to keep the screen from dimming or the processor from sleeping, or uses the MediaPlayer.setScreenOnWhilePlaying() or MediaPlayer.setWakeMode() methods, you must request this permission.
//                <uses-permission android:name="android.permission.WAKE_LOCK" />
//        videoView.setVideoPath("/sdcard/mycatvideo.3gp");
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.ll));


        // Attach a Media Controller
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();
    }

    public void ll(View view) {
        playraw();
    }


    //    音频播放
    //本地raw
    private void playraw() {
//        1.prepare()和prepareAsync() 提供了同步和异步两种方式设置播放器进入prepare状态，需要注意的是，如果MediaPlayer实例是由create方法创建的，那么第一次启动播放前不需要再调用prepare（）了，因为create方法里已经调用过了
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.track);
//        try {
//        设置数据源
//            mediaPlayer.setDataSource("/");
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//            prepare就是让mediaplayer准备，准备好就播放
//            2. start()是真正启动文件播放的方法。
//            3.pause()和stop()比较简单，起到暂停和停止播放的作用。
//            4.seekTo()是定位方法，可以让播放器从指定的位置开始播放，需要注意的是该方法是个异步方法，
// 也就是说该方法返回时并不意味着定位完成，尤其是播放的网络文件，真正定位完成时会触发OnSeekComplete.onSeekComplete()，
// 如果需要是可以调用setOnSeekCompleteListener(OnSeekCompleteListener)设置监听器来处理的。
//            5.release()可以释放播放器占用的资源，一旦确定不再使用播放器时应当尽早调用它释放资源。
//            6.reset()可以使播放器从Error状态中恢复过来，重新会到Idle状态。
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                mediaPlayer.release();
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
        mediaPlayer.start();

    }

    private void playuri() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse("file:///sdcard/ll.mp3"));

    }

    private void playurl() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse("http://"));

    }

    //从一个content provider
    private void playContent() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
    }
}
