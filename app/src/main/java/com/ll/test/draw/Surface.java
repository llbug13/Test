package com.ll.test.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by LL on 2016/9/12.
 * 3d，动画，游戏，浏览摄像头
 * 会增加内存消耗
 * Surface支持OpenGL se
 */
public class Surface extends SurfaceView implements
        SurfaceHolder.Callback {
    public Surface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private SurfaceHolder holder;
    private MySurfaceViewThread mySurfaceViewThread;
    private boolean hasSurface;

    private void init() {
        // Create a new SurfaceHolder and assign this
        // class as its callback.
        holder = getHolder();
        holder.addCallback(this);
        hasSurface = false;
    }

    public void resume() {
//        开启线程
        // Create and start the graphics update thread.
        if (mySurfaceViewThread == null) {
            mySurfaceViewThread = new MySurfaceViewThread();

            if (hasSurface == true)
                mySurfaceViewThread.start();
        }
    }

    //结束线程
    public void pause() {
        // Kill the graphics update thread
        if (mySurfaceViewThread != null) {
            mySurfaceViewThread.requestExitAndWait();
            mySurfaceViewThread = null;
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        hasSurface = true;
        if (mySurfaceViewThread != null)
            mySurfaceViewThread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        pause();
    }


    public void surfaceChanged(SurfaceHolder holder, int format,
                               int w, int h) {
        if (mySurfaceViewThread != null)
            mySurfaceViewThread.onWindowResize(w, h);
    }

    class MySurfaceViewThread extends Thread {
        private boolean done;

        MySurfaceViewThread() {
            super();
            done = false;
        }

        @Override
        public void run() {
            SurfaceHolder surfaceHolder = holder;

            // Repeat the drawing loop until the thread is stopped.
            while (!done) {
                // Lock the surface and return the canvas to draw onto.
//                锁定surface
                Canvas canvas = surfaceHolder.lockCanvas();
                // TODO: Draw on the canvas!
                // Unlock the canvas and render the current image.
//                解锁并渲染
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void requestExitAndWait() {
            // Mark this thread as complete and combine into
            // the main application thread.
            done = true;
            try {
                join();
            } catch (InterruptedException ex) {
            }
        }

        public void onWindowResize(int w, int h) {
            // Deal with a change in the available surface size.
        }
    }
}
