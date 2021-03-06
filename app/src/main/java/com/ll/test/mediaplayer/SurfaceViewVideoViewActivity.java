package com.ll.test.mediaplayer;

/**
 * Listing 15-4: Initializing and assigning a Surface View to a Media Player
 */

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

import com.ll.test.R;

import java.io.IOException;

public class SurfaceViewVideoViewActivity extends Activity
        implements SurfaceHolder.Callback {

    static final String TAG = "SurfaceViewVideoViewActivity";

    private MediaPlayer mediaPlayer;

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // When the surface is created, assign it as the
            // display surface and assign and prepare a data
            // source.
            mediaPlayer.setDisplay(holder);
//      mediaPlayer.setDataSource("/sdcard/test2.3gp");
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ll));
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Illegal Argument Exception", e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Illegal State Exception", e);
        } catch (SecurityException e) {
            Log.e(TAG, "Security Exception", e);
        } catch (IOException e) {
            Log.e(TAG, "IO Exception", e);
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.release();
    }

    public void surfaceChanged(SurfaceHolder holder,
                               int format, int width, int height) {
    }

    private MediaController mediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.surfaceviewvideoviewer);

        // Create a new Media Player.
        mediaPlayer = new MediaPlayer();

        // Get a reference to the Surface View.
        final SurfaceView surfaceView =
                (SurfaceView) findViewById(R.id.surfaceView);

        // Configure the Surface View.
        surfaceView.setKeepScreenOn(true);

        // Configure the Surface Holder and register the callback.
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.setFixedSize(400, 300);

        // Connect a play button.
        Button playButton = (Button) findViewById(R.id.buttonPlay);
        playButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        // Connect a pause button.
        Button pauseButton = (Button) findViewById(R.id.buttonPause);
        pauseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        // Add a skip button.
        Button skipButton = (Button) findViewById(R.id.buttonSkip);
        skipButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getDuration() / 2);
//                左右声道
                mediaPlayer.setVolume(0.1f, 0.1f);
//                播放期间不变暗
                mediaPlayer.setScreenOnWhilePlaying(true);
                if (!mediaPlayer.isLooping()) {
//                    是否是循环状态
                    mediaPlayer.setLooping(true);
                }
                mediaController.show();
            }
        });

        /**
         * Listing 15-5: Controlling playback using the Media Controller
         */
        mediaController = new MediaController(this);
//        关联player
        mediaController.setMediaPlayer(new MediaPlayerControl() {
            public boolean canPause() {
                return true;
            }

            public boolean canSeekBackward() {
                return true;
            }

            public boolean canSeekForward() {
                return true;
            }

            @Override
            public int getAudioSessionId() {
                return 0;
            }

            public int getBufferPercentage() {
                return 0;
            }

            public int getCurrentPosition() {
                return mediaPlayer.getCurrentPosition();
            }

            public int getDuration() {
                return mediaPlayer.getDuration();
            }

            public boolean isPlaying() {
                return mediaPlayer.isPlaying();
            }

            public void pause() {
                mediaPlayer.pause();
            }

            public void seekTo(int pos) {
                mediaPlayer.seekTo(pos);
            }

            public void start() {
                mediaPlayer.start();
            }
        });
        mediaController.setAnchorView(playButton);
//AudioManager.STREAM_MUSIC  /音乐回放即媒体音量/
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }
}