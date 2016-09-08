package com.ll.test.anim;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by LL on 2016/9/8.
 */
public class AnimTest {
    private void anim(View view) {
        AlphaAnimation animation = new AlphaAnimation();
//        Animation.RESTART循环Animation.REVERSEf方向运行
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }

    private void setl(Animation animation) {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                开始
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//结束
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//重复
            }
        });
    }
}
