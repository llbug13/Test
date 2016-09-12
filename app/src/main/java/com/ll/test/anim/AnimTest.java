package com.ll.test.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/8.
 */
public class AnimTest {
    private void anim(View view) {
        AlphaAnimation animation = null;//new AlphaAnimation();
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

    private void setview(Context context) {
        LinearLayout linearLayout = null;
        LayoutAnimationController layoutAnimationController = null;
        linearLayout.setLayoutAnimation(layoutAnimationController);
//        可以设置 监听
//       在加载的时候执行一次
//        代码再次执行
        linearLayout.scheduleLayoutAnimation();
    }

    //   逐帧动画
    private void setD(Context context) {
        View view = new View(context);
        view.setBackgroundResource(R.drawable.animation_list);
//        或者使用
//        view.setBackgroundDrawable();
        AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();
        animationDrawable.start();

    }

    //    属性动画3.0加入的，不一定使用动画，可以设置其他监听，属性
    private void sets(Context context) {
        View view = new View(context);
//       Object targe必须包含view.setAlpha();view.getAlpha()
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 0.4f);
//        从当前值转换到终值
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 0.1f);
//        实现ofobject要实现typeEvaluator类的实现，evaluate方法的返回
        TypeEvaluator<MyClass> evaluator = new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                MyClass myClass = new MyClass();
                return myClass;
            }
        };
//        两个实例简的动画
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(evaluator, 0.2);
        valueAnimator.setTarget(view);
        valueAnimator.setDuration(200);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.set_alpha);
        animator.setTarget(view);
        AnimatorSet animatorSet = new AnimatorSet();
//        添加懂画的执行顺序
        animatorSet.play(animator).before(animator);
        animatorSet.play(animator).after(animator);
        animatorSet.play(animator).with(animator);
        animatorSet.start();
        animator.addListener(animatorListener);
        animatorSet.addListener(animatorListener);

    }

    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private class MyClass {

    }

    private void setinterpolator(Context context) {
//        中间加速
//        AccelerateDecelerateInterpolator
//       开始慢中间加速
//        AccelerateInterpolator
//        开始的时候向后，再急冲
//        AnticipateInterpolator
//        开始向后，再急冲，最后回到终点
//        AnticipateOvershootInterpolator
//        动画结束时弹起
//        BounceInterpolator
//        开始时速度变化较快，然后减速
//        DecelerateInterpolator
//        速度变化是个常量
//        LinearInterpolator
//        开始的时候向前冲，超过终点在回来
//        OvershootInterpolator
//        自定义插入器
//        TimeInterpolator

        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.set_alpha);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

}
