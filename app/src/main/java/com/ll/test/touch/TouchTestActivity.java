package com.ll.test.touch;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by LL on 2016/9/13.
 * 主页键除外不能触发onkey
 */
public class TouchTestActivity extends Activity {


    private void init() {
        View view = new View(this);
//        设置对按键做出反应
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                keyCode确定按钮
//                按键按下，处理后true
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    按下
                }
//                KeyEvent.ACTION_UP释放
                return false;
            }
        });
    }

    //    触摸屏幕
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        MotionEvent包含每个指针的位置
//        MotionEvent包含压力（0（没压力）-1（正常压力））
//        event.getSize()(0(非常精准)-1(误触))
//        位置，压力，区域大小改变都触发onTouchEvent，MotionEvent.ACTION_MOVE
//        可以得到上次值event.getHistorySize()获取历史的大小，返回当前事件可用的移动位置数量

//        第一个点或者单点
        int action = event.getAction();
        if (event.getPointerCount() > 1) {
//            指针id
            int actionPointrId = action & MotionEvent.ACTION_POINTER_ID_MASK;
//            事件
            int actionEvent = action & MotionEvent.ACTION_MASK;
            int pointerIndex = event.findPointerIndex(actionPointrId);
            float x = event.getX(pointerIndex);
            float y = event.getY(pointerIndex);
        } else {
//            单点触摸事件
            event.getX();
            event.getY();
        }
        return super.onTouchEvent(event);
    }

    //   按下任何硬件按键时调用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    //释放任何硬件时调用
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        event.isAltPressed();
        event.isShiftPressed();
        event.isSymPressed();
        event.isCtrlPressed();
        event.isFunctionPressed();
//        是否是用户按下的
        event.isModifierKey(keyCode);
        return super.onKeyUp(keyCode, event);
    }

    //    轨迹球移动时触发
    @Override
    public boolean onTrackballEvent(MotionEvent event) {
//        相对值
        return super.onTrackballEvent(event);
    }
}
